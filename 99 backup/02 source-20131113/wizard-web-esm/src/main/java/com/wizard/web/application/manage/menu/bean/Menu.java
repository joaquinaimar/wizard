package com.wizard.web.application.manage.menu.bean;

import com.wizard.web.domain.entity.WizardMenu;

public class Menu extends WizardMenu {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2869729587969777045L;
	
	private String newId = null;

	public String getNewId() {
		return newId;
	}

	public void setNewId(String newId) {
		this.newId = newId;
	}

}
