package com.wizard.j2ee.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	public static <X> X getBean(String name, Class<X> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	public static boolean isSingleton(String name) {
		return applicationContext.isSingleton(name);
	}

	public static Class<?> getType(String name) {
		return applicationContext.getType(name);
	}

	public static String[] getAliases(String name) {
		return applicationContext.getAliases(name);
	}

}
