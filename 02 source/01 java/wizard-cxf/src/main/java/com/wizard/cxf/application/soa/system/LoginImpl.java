package com.wizard.cxf.application.soa.system;

import com.wizard.cxf.basic.annotation.WizardCxf;

@WizardCxf()
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
