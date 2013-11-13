package com.wizard.web.basic.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class WizardHttpSessionListener implements HttpSessionListener {

	public WizardHttpSessionListener() {
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {

		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
	}

}
