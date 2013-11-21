package com.wizard.util.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class CommonUtil {

	private CommonUtil() {
		throw new RuntimeException("Cannot create util class instance!");
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

}
