package com.wizard.ejb.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.wizard.ejb.application.session.system.ILogin;

public class EjbClient {

	public static void main(String args[]) throws NamingException {
		Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		Context context = new InitialContext(jndiProperties);

		final String appName = "";
		final String moduleName = "wizard-ejb";
		final String distinctName = "";

		ILogin login = (ILogin) context.lookup("ejb:" + appName + "/"
				+ moduleName + "/" + distinctName
				+ "/LoginImpl!com.wizard.ejb.application.session.system.ILogin");
		System.err.println(login.login("admin", "111"));

	}

}
