package com.wizard.ejb.application.session.system;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(ILogin.class)
public class LoginImpl implements ILogin {

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
