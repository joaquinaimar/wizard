package com.wizard.web.domain.entity;

import com.wizard.web.basic.database.EntityTemplate;

public class WizardAuthority extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6977026121655598808L;

	private String pkId = null;

	private String fkRoleId = null;

	private String fkMenuId = null;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getFkRoleId() {
		return fkRoleId;
	}

	public void setFkRoleId(String fkRoleId) {
		this.fkRoleId = fkRoleId;
	}

	public String getFkMenuId() {
		return fkMenuId;
	}

	public void setFkMenuId(String fkMenuId) {
		this.fkMenuId = fkMenuId;
	}

}
