package com.wizard.web.application.service.system;

import com.wizard.web.application.vo.system.MenuVo;

public interface IMenuService {

	MenuVo getHomePage();

	MenuVo[] getMenu(String id);

}
