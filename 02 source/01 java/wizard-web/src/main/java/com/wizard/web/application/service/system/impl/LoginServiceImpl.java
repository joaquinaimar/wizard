package com.wizard.web.application.service.system.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.util.common.SecurityUtil;
import com.wizard.web.application.dao.system.LoginDao;
import com.wizard.web.application.service.system.ILoginService;
import com.wizard.web.application.vo.system.LoginVo;
import com.wizard.web.domain.bean.LoginInfo;
import com.wizard.web.domain.entity.wizard.WizardUserInfo;

@Service
@Transactional
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private LoginDao loginDao = null;

	@Override
	public boolean login(LoginVo loginVo) {

		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				loginVo.getUsername(), SecurityUtil.encodeMd5(loginVo
						.getPassword()));
		token.setRememberMe(true);

		try {
			currentUser.login(token);
		} catch (UnknownAccountException uae) {
			return false;
		} catch (IncorrectCredentialsException ice) {
			return false;
		} catch (LockedAccountException lae) {
			return false;
		} catch (AuthenticationException ae) {
			return false;
		}

		return true;
	}

	@Override
	public LoginInfo getLoginInfo(LoginVo loginVo) {
		loginVo.setPassword(SecurityUtil.encodeMd5(loginVo.getPassword()));
		WizardUserInfo userInfo = this.loginDao.getLoginInfo(loginVo);

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setId(userInfo.getPkId());
		loginInfo.setUsername(userInfo.getUsername());
		loginInfo.setDetail(userInfo.getUserDetail());
		return loginInfo;
	}
}
