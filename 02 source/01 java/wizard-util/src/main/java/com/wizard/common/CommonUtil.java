package com.wizard.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class CommonUtil {

	private CommonUtil() {
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
	public static <X> X[] changeListToArray(final List<X> list, Class<X> cls) {
		X[] array = (X[]) Array.newInstance(cls, list.size());
		return list.toArray(array);
	}
}
