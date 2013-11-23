package com.wizard.cxf.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wizard.cxf.application.soa.system.ILogin;
import com.wizard.util.common.CommonUtil;

/**
 * Hello world!
 * 
 */
public class CxfClient {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring/wizard.xml");

		ILogin loginService = (ILogin) context.getBean("login");

		CommonUtil.consoleOutPrintln(loginService.login("admin", "111"));

	}
}
