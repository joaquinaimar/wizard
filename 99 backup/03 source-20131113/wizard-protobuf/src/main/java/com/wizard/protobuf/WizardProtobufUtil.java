package com.wizard.protobuf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.GeneratedMessage.Builder;
import com.wizard.core.util.WizardReflexUtil;
import com.wizard.core.util.WizardUtil;

/**
 * 
 * @author zhanglizhi042888
 * 
 */
public final class WizardProtobufUtil {

	private final static String METHOD_SET = "set";

	private final static String METHOD_GET = "get";

	private final static String METHOD_ADD = "add";

	/**
	 * 
	 * @param obj
	 * @param protobuf
	 * @return
	 */
	public static <X extends Builder<X>> X toProtobuf(Object obj, X protobuf) {

		if (null == obj || null == protobuf) {
			return null;
		}

		List<Method> methods = WizardReflexUtil.getGetMethods(obj);

		for (Method method : methods) {
			setProtobuf(obj, protobuf, method);
		}
		return protobuf;
	}

	/**
	 * 
	 * @param protobuf
	 * @param obj
	 * @return
	 */
	public static <X> X toObject(Builder<?> protobuf, X obj) {

		if (null == protobuf || null == obj) {
			return null;
		}

		Set<FieldDescriptor> fieldSet = protobuf.getAllFields().keySet();

		for (FieldDescriptor field : fieldSet) {
			setObject(protobuf, obj, field);
		}

		return obj;
	}

	/**
	 * 
	 * @param obj
	 * @param protobuf
	 * @param method
	 */
	private static void setProtobuf(Object obj, Builder<?> protobuf,
			Method method) {
		try {
			Object value = method.invoke(obj);
			String methodName = method.getName();
			Method pbMethod = null;

			if (value instanceof Iterable<?>) {
				pbMethod = WizardReflexUtil.getMethodByName(protobuf,
						methodName.replaceFirst(METHOD_GET, METHOD_ADD));
				if (null == pbMethod) {
					return;
				}
				for (Object o : (Iterable<?>) value) {
					pbMethod.invoke(protobuf, o);
				}
			} else {
				pbMethod = WizardReflexUtil.getMethodByName(protobuf,
						methodName.replaceFirst(METHOD_GET, METHOD_SET));
				if (null == pbMethod) {
					return;
				}
				pbMethod.invoke(protobuf, value);
			}

		} catch (IllegalAccessException e) {
		} catch (IllegalArgumentException e) {
		} catch (InvocationTargetException e) {
		}

	}

	private static void setObject(Builder<?> protobuf, Object obj,
			FieldDescriptor field) {

		Method method = WizardReflexUtil.getMethodByName(obj, METHOD_SET
				+ WizardUtil.firstUpper(field.getName()));

		if (null == method) {
			return;
		}

		Object value = protobuf.getField(field);

		try {

			if (value instanceof Iterable<?>) {
				List<Object> list = new ArrayList<Object>();
				for (Object o : (Iterable<?>) value) {
					list.add(o);
				}
				method.invoke(obj, list);
			} else {
				method.invoke(obj, value);
			}
		} catch (IllegalAccessException e) {
		} catch (IllegalArgumentException e) {
		} catch (InvocationTargetException e) {
		}

	}
}
