package com.wizard.web.application.system.login.form;

import com.opensymphony.xwork2.ActionSupport;

public class LoginForm extends ActionSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5187608316120306293L;

	private String user = null;

	private String pwd = null;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
