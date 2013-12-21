package com.wizard.web.application.web.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.j2ee.domain.ResponseVo;
import com.wizard.j2ee.util.SpringWebUtil;
import com.wizard.web.application.service.system.IMenuService;
import com.wizard.web.application.vo.system.MenuVo;
import com.wizard.web.domain.bean.LoginInfo;

@Controller
@RequestMapping("wizard/menu")
public class MenuController {

	@Autowired
	private IMenuService menuService = null;

	@RequestMapping(value = "/getHomePage.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<MenuVo> getHomePage() {
		return new ResponseVo<MenuVo>(true, this.menuService.getHomePage());
	}

	@RequestMapping(value = "/getMenu.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<MenuVo[]> getMenu() {
		String id = ((LoginInfo) SpringWebUtil.getSessionAttribute("loginInfo"))
				.getId();
		return new ResponseVo<MenuVo[]>(true, this.menuService.getMenu(id));
	}

}
