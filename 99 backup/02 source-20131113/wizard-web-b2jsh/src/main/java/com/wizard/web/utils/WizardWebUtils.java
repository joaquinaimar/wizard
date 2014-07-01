package com.wizard.web.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.PageContext;

import com.wizard.web.basic.constant.WizardWedConstant;

public class WizardWebUtils {

	public final static String USER_NAME = "userName";

	public final static String ROLE_NAME = "roleName";

	public static String getUserName(HttpSession session) {
		Map<String, Object> userInfo = getUserInfo(session);
		if (null == userInfo) {
			return null;
		}
		return (String) userInfo.get(USER_NAME);
	}

	public static String getRoleName(HttpSession session) {
		Map<String, Object> userInfo = getUserInfo(session);
		if (null == userInfo) {
			return null;
		}
		return (String) userInfo.get(ROLE_NAME);
	}

	public static String getRoleName(JspContext jspContext) {
		Map<String, Object> userInfo = getUserInfo(jspContext);
		if (null == userInfo) {
			return null;
		}
		return (String) userInfo.get(ROLE_NAME);
	}

	public static Map<String, Object> getUserInfo(HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<String, Object> userInfo = (Map<String, Object>) session
				.getAttribute(WizardWedConstant.SESSION_USER_INFO);
		return userInfo;
	}

	public static Map<String, Object> getUserInfo(JspContext jspContext) {
		@SuppressWarnings("unchecked")
		Map<String, Object> userInfo = (Map<String, Object>) jspContext
				.getAttribute(WizardWedConstant.SESSION_USER_INFO,
						PageContext.SESSION_SCOPE);
		return userInfo;
	}

	public static String getRequestParameter(HttpServletRequest request,
			String name) {
		String obj = request.getParameter(name);
		return (null == obj) ? "" : obj;
	}

	public static String[] getRequestParameters(HttpServletRequest request,
			String name) {
		String[] obj = request.getParameterValues(name);
		return obj;
	}

	public static List<String> getRequestParameterList(
			HttpServletRequest request, String name) {
		String[] obj = getRequestParameters(request, name);
		return WizardUtils.toList(obj);
	}

	public static Object getRequestAttribute(HttpServletRequest request,
			String name) {
		Object obj = request.getAttribute(name);
		return (null == obj) ? "" : obj;
	}

	public static Object getSessionAttribute(HttpSession session, String name) {
		Object obj = session.getAttribute(name);
		return (null == obj) ? "" : obj;
	}

	public static Map<String, Object> getRequestParameterMap(
			HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();

		@SuppressWarnings("unchecked")
		Enumeration<String> enumer = request.getParameterNames();

		String key = new String();
		Object value = new Object();

		while (enumer.hasMoreElements()) {

			key = enumer.nextElement();
			value = request.getParameter(key);

			if (null != value && !"".equals(value)) {
				map.put(key, value);
			}

		}

		return map;
	}

}
