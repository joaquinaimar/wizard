package com.wizard.j2ee.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class WebUtil {

	private WebUtil() {
		throw new UnsupportedOperationException("Not supported");
	}

	public static ServletInputStream getServletInputStream(
			final HttpServletRequest request) {
		try {
			return request.getInputStream();
		} catch (IOException e) {
			return null;
		}
	}

	public static String getContextPath(final HttpServletRequest request) {
		return request.getContextPath();
	}

	public static String getLocalAddr(final HttpServletRequest request) {
		return request.getLocalAddr();
	}

	public static String getLocalName(final HttpServletRequest request) {
		return request.getLocalName();
	}

	public static int getLocalPort(final HttpServletRequest request) {
		return request.getLocalPort();
	}

	public static String getRemoteAddr(final HttpServletRequest request) {
		return request.getRemoteAddr();
	}

	public static String getRemoteHost(final HttpServletRequest request) {
		return request.getRemoteHost();
	}

	public static int getRemotePort(final HttpServletRequest request) {
		return request.getRemotePort();
	}

	public static String getCharacterEncoding(final HttpServletRequest request) {
		return request.getCharacterEncoding();
	}

	public static String getMethod(final HttpServletRequest request) {
		return request.getMethod();
	}

	public static Cookie[] getCookies(final HttpServletRequest request) {
		return request.getCookies();
	}

	public static String getHeader(final HttpServletRequest request,
			final String name) {
		return request.getHeader(name);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getHeaderMap(
			final HttpServletRequest request) {
		Map<String, Object> map = new TreeMap<String, Object>();
		Enumeration<String> er = request.getHeaderNames();
		String name = null;
		while (er.hasMoreElements()) {
			name = er.nextElement();
			map.put(name, getHeader(request, name));
		}
		return map;
	}

	public static String getParameter(final HttpServletRequest request,
			final String name) {
		return request.getParameter(name);
	}

	public static String[] getParameterValues(final HttpServletRequest request,
			final String name) {
		return request.getParameterValues(name);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> getParameterMap(
			final HttpServletRequest request) {
		return request.getParameterMap();
	}

	public static Object getAttribute(final HttpServletRequest request,
			final String name) {
		return request.getAttribute(name);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getAttributeMap(
			final HttpServletRequest request) {
		Map<String, Object> map = new TreeMap<String, Object>();
		Enumeration<String> er = request.getAttributeNames();
		String name = null;
		while (er.hasMoreElements()) {
			name = er.nextElement();
			map.put(name, getAttribute(request, name));
		}
		return map;
	}

	public static HttpSession getSession(final HttpServletRequest request) {
		return request.getSession();
	}

	public static Object getAttribute(final HttpSession session,
			final String name) {
		return session.getAttribute(name);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getAttributeMap(final HttpSession session) {
		Map<String, Object> map = new TreeMap<String, Object>();
		Enumeration<String> er = session.getAttributeNames();
		String name = null;
		while (er.hasMoreElements()) {
			name = er.nextElement();
			map.put(name, getAttribute(session, name));
		}
		return map;
	}

	public static ServletContext getServletContext(final HttpSession session) {
		return session.getServletContext();
	}

	public static ServletContext getServletContext(
			final HttpServletRequest request) {
		return getServletContext(getSession(request));
	}

	public static Object getAttribute(final ServletContext servletContext,
			final String name) {
		return servletContext.getAttribute(name);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getAttributeMap(
			final ServletContext servletContext) {
		Map<String, Object> map = new TreeMap<String, Object>();
		Enumeration<String> er = servletContext.getAttributeNames();
		String name = null;
		while (er.hasMoreElements()) {
			name = er.nextElement();
			map.put(name, getAttribute(servletContext, name));
		}
		return map;
	}

}
