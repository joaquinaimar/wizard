package com.wizard.j2ee.util;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;

public final class SpringWebUtil {

	private static HttpServletRequest request = null;

	@Autowired
	public void setRequest(final HttpServletRequest request) {
		SpringWebUtil.request = request;
	}

	public static ServletInputStream getServletInputStream() {
		return WebUtil.getServletInputStream(request);
	}

	public static String getContextPath() {
		return WebUtil.getContextPath(request);
	}

	public static String getLocalAddr() {
		return WebUtil.getLocalAddr(request);
	}

	public static String getLocalName() {
		return WebUtil.getLocalName(request);
	}

	public static int getLocalPort() {
		return WebUtil.getLocalPort(request);
	}

	public static String getRemoteAddr() {
		return WebUtil.getRemoteAddr(request);
	}

	public static String getRemoteHost() {
		return WebUtil.getRemoteHost(request);
	}

	public static int getRemotePort() {
		return WebUtil.getRemotePort(request);
	}

	public static String getCharacterEncoding() {
		return WebUtil.getCharacterEncoding(request);
	}

	public static String getMethod() {
		return WebUtil.getMethod(request);
	}

	public static Cookie[] getCookies() {
		return WebUtil.getCookies(request);
	}

	public static String getHeader(final String name) {
		return WebUtil.getHeader(request, name);
	}

	public static Map<String, Object> getHeaderMap() {
		return WebUtil.getHeaderMap(request);
	}

	public static String getParameter(final String name) {
		return WebUtil.getParameter(request, name);
	}

	public static String[] getParameterValues(final String name) {
		return WebUtil.getParameterValues(request, name);
	}

	public static Map<String, String> getParameterMap() {
		return WebUtil.getParameterMap(request);
	}

	public static Object getRequestAttribute(final String name) {
		return WebUtil.getAttribute(request, name);
	}

	public static void setRequestAttribute(final String name, final Object value) {
		WebUtil.setAttribute(request, name, value);
	}

	public static Map<String, Object> getRequestAttributeMap() {
		return WebUtil.getAttributeMap(request);
	}

	public static void setAttributeMap(final Map<String, Object> map) {
		WebUtil.setAttributeMap(request, map);
	}

	public static HttpSession getSession() {
		return WebUtil.getSession(request);
	}

	public static Object getSessionAttribute(final String name) {
		return WebUtil.getAttribute(getSession(), name);
	}

	public static void setSessionAttribute(final String name, final Object value) {
		WebUtil.setAttribute(getSession(), name, value);
	}

	public static Map<String, Object> getSessionAttributeMap(
			final HttpSession session) {
		return WebUtil.getAttributeMap(getSession());
	}

	public static void setSessionAttributeMap(final Map<String, Object> map) {
		WebUtil.setAttributeMap(getSession(), map);
	}

	public static void sessionInvalidate() {
		WebUtil.invalidate(getSession());
	}

	public static ServletContext getServletContext() {
		return WebUtil.getServletContext(request);
	}

	public static Object getServletContextAttribute(
			final ServletContext servletContext, final String name) {
		return WebUtil.getAttribute(getServletContext(), name);
	}

	public static void setServletContextAttribute(final String name,
			final Object value) {
		WebUtil.setAttribute(getServletContext(), name, value);
	}

	public static Map<String, Object> getServletContextAttributeMap(
			final ServletContext servletContext) {
		return WebUtil.getAttributeMap(getServletContext());
	}

	public static void setServletContextAttributeMap(
			final Map<String, Object> map) {
		WebUtil.setAttributeMap(getServletContext(), map);
	}

	public static List<FileItem> getFileItem(final long sizeMax) {
		return WebUtil.getFileItem(request, sizeMax);
	}

	public static List<FileItem> getFileItem() {
		return WebUtil.getFileItem(request);
	}

}
