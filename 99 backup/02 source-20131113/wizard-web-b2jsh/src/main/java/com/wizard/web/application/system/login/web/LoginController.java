package com.wizard.web.application.system.login.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.web.application.system.login.service.LoginService;
import com.wizard.web.basic.constant.WizardWedConstant;
import com.wizard.web.basic.io.AjaxResponse;
import com.wizard.web.basic.log.WizardWebLogger;
import com.wizard.web.utils.WizardSecurityUtils;

@Controller
@RequestMapping("wizardframework/Login")
public class LoginController {

	@Autowired
	private WizardWebLogger wizardLogger = null;

	@Autowired
	private LoginService loginService = null;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse<Boolean> doLogin(HttpServletRequest request,
			@RequestParam String user, @RequestParam String pwd) {

		HttpSession session = request.getSession();

		wizardLogger.info("用户登录", request, user);

		Map<String, Object> userInfo = loginService.login(user,
				WizardSecurityUtils.encodeMd5(pwd));
		session.removeAttribute(WizardWedConstant.SESSION_USER_INFO);
		if (null == userInfo) {
			wizardLogger.warn("登录失败", user, user);
			return new AjaxResponse<Boolean>(true, false);
		} else {
			session.setAttribute(WizardWedConstant.SESSION_USER_INFO, userInfo);
			wizardLogger.info("登录成功", user, user);
			return new AjaxResponse<Boolean>(true, true);
		}

	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	@ResponseBody
	public String doLogout(HttpServletRequest request) {
		wizardLogger.info("用户注销", request);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		request.getSession().invalidate();
		return "logout";
	}
}
