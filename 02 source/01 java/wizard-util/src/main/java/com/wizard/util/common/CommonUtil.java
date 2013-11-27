package com.wizard.util.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public final class CommonUtil {

	private CommonUtil() {
		throw new UnsupportedOperationException("Not supported");
	}

	public static void consoleOutPrint(final Object obj) {
		System.out.print(obj);
	}

	public static void consoleOutPrintln(final Object obj) {
		System.out.println(obj);
	}

	public static void consoleErrPrint(final Object obj) {
		System.err.print(obj);
	}

	public static void consoleErrPrintln(final Object obj) {
		System.err.println(obj);
	}

	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	public static Date getCurrentTime() {
		return new Date(getCurrentTimeMillis());
	}

	public static String getClassPath() {
		return ClassLoader.getSystemResource(StringUtil.BLANK).getPath();
	}

	public static boolean isNull(final Object obj) {
		return (null == obj);
	}

	public static <X> boolean isEmpty(final X[] array) {
		return (isNull(array) || 0 == array.length);
	}

	public static boolean isEmpty(final Collection<?> collection) {
		return (isNull(collection) || 0 == collection.size());
	}

	public static <X> Collection<X> changeArrayToList(final X[] array) {
		Collection<X> list = new ArrayList<X>(array.length);
		for (X x : array)
			list.add(x);
		return list;
	}

	@SuppressWarnings("unchecked")
	public static <X> X[] changeListToArray(final Collection<X> list) {
		if (0 == list.size())
			return null;
		for (X x : list) {
			X[] array = (X[]) Array.newInstance(x.getClass(), list.size());
			return list.toArray(array);
		}
		return null;
	}

	public static Object[] changeListToObjects(final Collection<?> list) {
		return list.toArray();
	}

	public static String createUUID() {
		return UUID.randomUUID().toString();
	}

}
