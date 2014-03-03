package com.wizard.axis2.application.soa.system;

import org.springframework.stereotype.Component;

@Component("login")
public class LoginImpl implements ILogin {

	@Override
	public Boolean login(String username, String password) {

		if ("admin".equals(username) && "111".equals(password))
			return true;
		return false;
	}

	@Override
	public Boolean logout(String username) {
		return false;
	}

}
