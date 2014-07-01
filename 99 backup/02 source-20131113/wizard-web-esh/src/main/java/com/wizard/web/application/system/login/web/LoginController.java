package com.wizard.web.application.system.login.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wizard.web.application.system.login.service.LoginService;
import com.wizard.web.basic.constant.WizardWedConstant;
import com.wizard.web.basic.constant.WizardWedUrl;
import com.wizard.web.basic.log.WizardWebLogger;
import com.wizard.web.utils.SecurityUtils;

@Controller
@RequestMapping("wizardframework/Login")
public class LoginController {

	@Autowired
	private WizardWebLogger wizardLogger = null;

	@Autowired
	private LoginService loginService = null;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request,
			@RequestParam String user, @RequestParam String pwd) {

		HttpSession session = request.getSession();

		wizardLogger.info("用户登录", request, user);

		Map<String, Object> userInfo = loginService.login(user,
				SecurityUtils.encodeMd5(pwd));

		session.removeAttribute(WizardWedConstant.SESSION_LOGIN_INFO);
		session.removeAttribute(WizardWedConstant.SESSION_USER_INFO);

		if (null == userInfo) {
			session.setAttribute(WizardWedConstant.SESSION_LOGIN_INFO,
					"用户名或密码不正确！");
			wizardLogger.warn("登录失败", user, user);
			return new ModelAndView(new RedirectView(WizardWedUrl.URL_LOGIN));
		} else {
			session.setAttribute(WizardWedConstant.SESSION_USER_INFO, userInfo);
			wizardLogger.info("登录成功", user, user);
			return new ModelAndView(new RedirectView(WizardWedUrl.URL_MAIN));
		}

	}
}
