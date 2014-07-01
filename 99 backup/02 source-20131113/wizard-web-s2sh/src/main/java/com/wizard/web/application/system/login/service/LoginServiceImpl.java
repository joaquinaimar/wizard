package com.wizard.web.application.system.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.web.application.system.login.dao.LoginDao;
import com.wizard.web.application.system.login.form.LoginForm;
import com.wizard.web.domain.entity.WizardUserInfo;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao = null;

	@Override
	public boolean login(LoginForm loginForm) {

		WizardUserInfo userInfo = new WizardUserInfo();

		userInfo.setUserName(loginForm.getUser());
		userInfo.setPassword(loginForm.getPwd());

		userInfo = loginDao.login(userInfo);

		return (null != userInfo
				&& userInfo.getUserName().equals(loginForm.getUser()) && userInfo
				.getPassword().equals(loginForm.getPwd()));
	}

}
