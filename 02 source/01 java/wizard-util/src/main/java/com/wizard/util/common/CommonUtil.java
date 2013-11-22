package com.wizard.util.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public final class CommonUtil {

	private CommonUtil() {
		throw new RuntimeException("Cannot create util class instance!");
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

	public static <X> List<X> changeArrayToList(final X[] array) {
		List<X> list = new ArrayList<X>(array.length);
		for (X x : array)
			list.add(x);
		return list;
	}

	@SuppressWarnings("unchecked")
	public static <X> X[] changeListToArray(final List<X> list) {
		if (0 == list.size())
			return null;
		X[] array = (X[]) Array
				.newInstance(list.get(0).getClass(), list.size());
		return list.toArray(array);
	}

	public static String createUUID() {
		return UUID.randomUUID().toString();
	}

}
