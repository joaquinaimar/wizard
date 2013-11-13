package com.wizard.web.application.manage.member.bean;

import com.wizard.web.domain.entity.WizardRoleInfo;

public class RoleInfo extends WizardRoleInfo {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4100725068148659735L;

	private String orgName = null;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
