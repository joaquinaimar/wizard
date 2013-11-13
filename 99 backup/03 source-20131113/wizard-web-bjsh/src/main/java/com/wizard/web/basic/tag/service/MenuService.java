package com.wizard.web.basic.tag.service;

import java.util.List;

import com.wizard.web.domain.entity.WizardMenu;

public interface MenuService {

	public List<WizardMenu> getParentNodes(String roleName);
	
	public List<WizardMenu> getChildNodes(String pid, String roleName);

}
