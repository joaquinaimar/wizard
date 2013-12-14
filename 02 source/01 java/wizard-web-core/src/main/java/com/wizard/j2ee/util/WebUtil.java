package com.wizard.j2ee.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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

	public static void setAttribute(final HttpServletRequest request,
			final String name, final Object value) {
		request.setAttribute(name, value);
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

	public static void setAttributeMap(final HttpServletRequest request,
			final Map<String, Object> map) {
		for (String key : map.keySet())
			setAttribute(request, key, map.get(key));
	}

	public static HttpSession getSession(final HttpServletRequest request) {
		return request.getSession();
	}

	public static Object getAttribute(final HttpSession session,
			final String name) {
		return session.getAttribute(name);
	}

	public static void setAttribute(final HttpSession session,
			final String name, final Object value) {
		session.setAttribute(name, value);
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

	public static void setAttributeMap(final HttpSession session,
			final Map<String, Object> map) {
		for (String key : map.keySet())
			setAttribute(session, key, map.get(key));
	}

	public static void invalidate(final HttpSession session) {
		session.invalidate();
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

	public static void setAttribute(final ServletContext servletContext,
			final String name, final Object value) {
		servletContext.setAttribute(name, value);
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

	public static void setAttributeMap(final ServletContext servletContext,
			final Map<String, Object> map) {
		for (String key : map.keySet())
			setAttribute(servletContext, key, map.get(key));
	}

	@SuppressWarnings("unchecked")
	public static List<FileItem> getFileItem(final HttpServletRequest request,
			final long sizeMax) {
		ServletFileUpload fileupload = new ServletFileUpload(
				new DiskFileItemFactory());
		fileupload.setSizeMax(sizeMax <= 0 ? -1 : sizeMax);
		try {
			return fileupload.parseRequest(request);
		} catch (FileUploadException e) {
			return null;
		}
	}

	public static List<FileItem> getFileItem(final HttpServletRequest request) {
		return getFileItem(request, 0);
	}

}
