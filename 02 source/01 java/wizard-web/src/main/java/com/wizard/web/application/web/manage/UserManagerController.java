package com.wizard.web.application.web.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.j2ee.domain.ResponseVo;
import com.wizard.j2ee.util.SpringWebUtil;
import com.wizard.web.application.service.manage.IUserManagerService;
import com.wizard.web.application.vo.manage.ConfirmVo;
import com.wizard.web.domain.bean.LoginInfo;

@Controller
@RequestMapping("wizard/manage/user-manager")
public class UserManagerController {

	@Autowired
	private IUserManagerService userManagerService = null;

	@RequestMapping(value = "/confirmPassword.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> confirmPassword(
			@ModelAttribute ConfirmVo confirmVo) {
		String id = ((LoginInfo) SpringWebUtil.getSessionAttribute("loginInfo"))
				.getId();
		return new ResponseVo<Boolean>(true,
				this.userManagerService.confirmPassword(id, confirmVo));
	}

}
