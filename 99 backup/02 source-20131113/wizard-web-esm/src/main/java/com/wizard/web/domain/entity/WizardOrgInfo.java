package com.wizard.web.domain.entity;

import com.wizard.web.basic.database.EntityTemplate;

public class WizardOrgInfo extends EntityTemplate  {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8720717869942420338L;

	private String pkId = null;

	private String orgName = null;

	private String orgDetail = null;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgDetail() {
		return orgDetail;
	}

	public void setOrgDetail(String orgDetail) {
		this.orgDetail = orgDetail;
	}
}
