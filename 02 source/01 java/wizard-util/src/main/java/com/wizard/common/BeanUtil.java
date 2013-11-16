package com.wizard.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BeanUtil {

	private BeanUtil() {
	}

	public static Class<?> getClass(final Object obj) {
		Class<?> cls = null;
		if (!CommonUtil.isNull(obj))
			cls = obj.getClass();
		return cls;
	}

	public static Class<?> getSuperclass(final Class<?> cls) {
		Class<?> superClass = null;
		if (!CommonUtil.isNull(cls)
				&& !("java.lang.Object".equals(getName(cls))))
			superClass = cls.getSuperclass();
		return superClass;
	}

	public static Class<?> getSuperclass(final Object obj) {
		return getSuperclass(obj);
	}

	public static String getName(final Class<?> cls) {
		return cls.getName();
	}

	public static String getName(final Object obj) {
		return getName(getClass(obj));
	}

	public static String getSimpleName(final Class<?> cls) {
		return cls.getSimpleName();
	}

	public static String getSimpleName(final Object obj) {
		return getSimpleName(getClass(obj));
	}

	public static Field[] getFields(final Class<?> cls,
			final boolean IsSuperClass) {
		Field[] fields = cls.getFields();
		if (IsSuperClass) {
			List<Field> list = new ArrayList<Field>();
			list.addAll(CommonUtil.changeArrayToList(fields));
			Class<?> superClass = getSuperclass(cls);
			if (!CommonUtil.isNull(superClass))
				list.addAll(CommonUtil.changeArrayToList(getFields(superClass,
						IsSuperClass)));
			return CommonUtil.changeListToArray(list, Field.class);
		} else {
			return fields;
		}
	}

	public static Field[] getFields(final Object obj, final boolean IsSuperClass) {
		return getFields(getClass(obj), IsSuperClass);
	}

	public static Field[] getDeclaredFields(final Class<?> cls,
			boolean IsSuperClass) {
		Field[] fields = cls.getDeclaredFields();
		if (IsSuperClass) {
			List<Field> list = new ArrayList<Field>();
			list.addAll(CommonUtil.changeArrayToList(fields));
			Class<?> superClass = getSuperclass(cls);
			if (!CommonUtil.isNull(superClass))
				list.addAll(CommonUtil.changeArrayToList(getDeclaredFields(
						superClass, IsSuperClass)));
			return CommonUtil.changeListToArray(list, Field.class);
		} else {
			return fields;
		}
	}

	public static Field[] getDeclaredFields(final Object obj,
			boolean IsSuperClass) {
		return getDeclaredFields(getClass(obj), IsSuperClass);
	}

	public static Field getField(final Class<?> cls, final String fieldName) {
		try {
			return cls.getDeclaredField(fieldName);
		} catch (Exception e) {
			if ("java.lang.Object".equals(getName(cls)))
				return null;
			else
				return getField(getSuperclass(cls), fieldName);
		}
	}

	public static Field getField(final Object obj, final String fieldName) {
		return getField(getClass(obj), fieldName);
	}

	public static Object getFieldValue(final Object obj, final Field field) {
		if (!CommonUtil.isNull(field))
			try {
				field.setAccessible(true);
				return field.get(obj);
			} catch (Exception e) {
			}
		return null;
	}

	public static Object getFieldValue(final Object obj, final String fieldName) {
		return getFieldValue(obj, getField(obj, fieldName));
	}

	public static void setFieldValue(final Object obj, final Field field,
			final Object value) {
		if (!CommonUtil.isNull(field))
			try {
				field.setAccessible(true);
				field.set(obj, value);
			} catch (Exception e) {
			}
	}

	public static void setFieldValue(final Object obj, final String fieldName,
			final Object value) {
		setFieldValue(obj, getField(obj, fieldName), value);
	}

	public static void copy(final Object from, final Object to) {
		Field[] fields = getDeclaredFields(from, true);
		for (Field field : fields)
			setFieldValue(to, field.getName(), getFieldValue(from, field));
	}

	public static Map<String, Object> beanToMap(final Object obj) {
		Field[] fields = getDeclaredFields(obj, true);
		Map<String, Object> map = new HashMap<String, Object>(fields.length);
		for (Field field : fields)
			map.put(field.getName(), getFieldValue(obj, field));
		return map;
	}

	public static <X> X mapToBean(final Map<String, Object> map, final X x) {
		Field[] fields = getDeclaredFields(x, true);
		for (Field field : fields)
			if (map.containsKey(field.getName()))
				setFieldValue(x, field.getName(), map.get(field.getName()));
		return x;
	}

	public static <X> X mapToBean(final Map<String, Object> map,
			final Class<X> cls) {
		try {
			return mapToBean(map, cls.newInstance());
		} catch (Exception e) {
			return null;
		}
	}

	public static Method[] getMethods(final Class<?> cls,
			final boolean IsSuperClass) {
		Method[] methods = cls.getMethods();
		if (IsSuperClass) {
			List<Method> list = new ArrayList<Method>();
			list.addAll(CommonUtil.changeArrayToList(methods));
			Class<?> superClass = getSuperclass(cls);
			if (!CommonUtil.isNull(superClass))
				list.addAll(CommonUtil.changeArrayToList(getMethods(superClass,
						IsSuperClass)));
			return CommonUtil.changeListToArray(list, Method.class);
		} else {
			return methods;
		}
	}

	public static Method[] getMethods(final Object obj,
			final boolean IsSuperClass) {
		return getMethods(getClass(obj), IsSuperClass);
	}

	public static Method[] getDeclaredMethods(final Class<?> cls,
			final boolean IsSuperClass) {
		Method[] methods = cls.getDeclaredMethods();
		if (IsSuperClass) {
			List<Method> list = new ArrayList<Method>();
			list.addAll(CommonUtil.changeArrayToList(methods));
			Class<?> superClass = getSuperclass(cls);
			if (!CommonUtil.isNull(superClass))
				list.addAll(CommonUtil.changeArrayToList(getDeclaredMethods(
						superClass, IsSuperClass)));
			return CommonUtil.changeListToArray(list, Method.class);
		} else {
			return methods;
		}
	}

	public static Method[] getDeclaredMethods(final Object obj,
			final boolean IsSuperClass) {
		return getDeclaredMethods(getClass(obj), IsSuperClass);
	}

	public static Method[] getMethods(final Class<?> cls,
			final String methodName, final boolean IsSuperClass) {
		List<Method> list = new ArrayList<Method>();
		Method[] methods = getDeclaredMethods(cls, IsSuperClass);
		for (Method method : methods)
			if (methodName.equals(method.getName()))
				list.add(method);
		return CommonUtil.changeListToArray(list, Method.class);
	}

	public static Method[] getMethods(final Object obj,
			final String methodName, final boolean IsSuperClass) {
		return getMethods(getClass(obj), methodName, IsSuperClass);
	}

	public static Method[] getDeclaredMethods(final Object obj,
			final String methodName, final boolean IsSuperClass) {
		return getDeclaredMethods(getClass(obj), methodName, IsSuperClass);
	}

	public static Method getMethod(final Class<?> cls, final String methodName,
			final Class<?>[] paramCls) {
		try {
			return cls.getMethod(methodName, paramCls);
		} catch (Exception e) {
			if ("java.lang.Object".equals(getName(cls)))
				return null;
			else
				return getMethod(getSuperclass(cls), methodName, paramCls);
		}
	}

	public static Method getMethod(final Object obj, final String methodName,
			final Class<?>[] paramCls) {
		return getMethod(getClass(obj), methodName, paramCls);
	}

	public static Method getMethod(final Object obj, final String methodName,
			final Object[] params) {
		Class<?>[] paramCls = new Class<?>[params.length];
		for (int i = 0; i < params.length; i++)
			paramCls[i] = params[i].getClass();
		return getMethod(getClass(obj), methodName, paramCls);
	}

	public static Object invoke(final Object obj, final String methodName,
			final Object[] params) {
		Method method = getMethod(obj, methodName, params);
		try {
			return method.invoke(obj, params);
		} catch (Exception e) {
			return null;
		}
	}
}
