package com.wizard.web.application.maintain.code.bean;

import com.wizard.web.domain.entity.WizardCode;

public class Code extends WizardCode {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5048080053037749207L;

	private String newId = null;

	private String type = null;

	public String getNewId() {
		return newId;
	}

	public void setNewId(String newId) {
		this.newId = newId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
