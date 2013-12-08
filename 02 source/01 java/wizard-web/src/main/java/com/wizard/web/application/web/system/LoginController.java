package com.wizard.web.application.web.system;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.j2ee.domain.ResponseVo;
import com.wizard.j2ee.util.SpringWebUtil;
import com.wizard.web.application.service.system.ILoginService;
import com.wizard.web.application.vo.system.LoginVo;

@Controller
@RequestMapping("wizardframework/login")
public class LoginController {

	@Autowired
	private ILoginService loginService = null;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> login(@ModelAttribute LoginVo loginVo) {
		if (!loginService.login(loginVo)) {
			return new ResponseVo<Boolean>(true, false);
		} else {
			return new ResponseVo<Boolean>(true, true);
		}

	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	@ResponseBody
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		SpringWebUtil.sessionInvalidate();
		return "logout";
	}

}
