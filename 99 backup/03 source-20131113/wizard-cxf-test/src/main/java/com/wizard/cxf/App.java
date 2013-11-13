package com.wizard.cxf;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.wizard.cxf.application.system.ILogin;

/**
 * Hello world!
 * 
 */
public class App {

	public static void main(String[] args) {

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ILogin.class);
		factory.setAddress("http://localhost:8080/wizard-cxf/login");

		ILogin service = (ILogin) factory.create();
		System.err.println(service.login("admin", "123"));
	}

}
