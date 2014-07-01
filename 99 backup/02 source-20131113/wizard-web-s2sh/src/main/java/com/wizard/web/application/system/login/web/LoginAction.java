package com.wizard.web.application.system.login.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wizard.web.application.system.login.form.LoginForm;
import com.wizard.web.application.system.login.service.LoginService;

@Component("loginAction")
@Scope("prototype")
public class LoginAction extends LoginForm {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -606755928435573438L;

	@Autowired
	private LoginService loginService = null;

	public String execute() {
		try {
			if (loginService.login(this)) {
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			return "fail";
		}
	}

	public String jsonTest() {
		this.setUser("zlz");
		this.setPwd("pwd");
		return "success";
	}

}
