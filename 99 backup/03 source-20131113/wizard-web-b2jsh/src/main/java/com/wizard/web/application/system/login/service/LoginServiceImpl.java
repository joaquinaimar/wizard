package com.wizard.web.application.system.login.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.web.basic.security.WizardWebSecurityManager;
import com.wizard.web.utils.WizardWebUtils;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Override
	public Map<String, Object> login(String userName, String password) {

		Map<String, Object> loginInfo = new HashMap<String, Object>();

		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,
				password);
		token.setRememberMe(true);

		try {
			currentUser.login(token);
		} catch (UnknownAccountException uae) {
			return null;
		} catch (IncorrectCredentialsException ice) {
			return null;
		} catch (LockedAccountException lae) {
			return null;
		} catch (AuthenticationException ae) {
			return null;
		}
		loginInfo.put(WizardWebUtils.USER_NAME, userName);
		loginInfo.put(WizardWebUtils.ROLE_NAME, getRole(currentUser));

		return loginInfo;
	}

	private String getRole(Subject currentUser) {
		WebDelegatingSubject user = (WebDelegatingSubject) currentUser;
		WizardWebSecurityManager securityManager = (WizardWebSecurityManager) user
				.getSecurityManager();
		List<String> role = securityManager.getRoles(user.getPrincipals());
		return role.isEmpty() ? null : role.get(0);
	}

}
