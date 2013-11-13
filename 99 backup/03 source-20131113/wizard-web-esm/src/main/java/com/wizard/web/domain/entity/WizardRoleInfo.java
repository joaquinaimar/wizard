package com.wizard.web.domain.entity;

import com.wizard.web.basic.database.EntityTemplate;

public class WizardRoleInfo extends EntityTemplate  {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -602118531530483270L;

	private String pkId = null;

	private String roleName = null;

	private String roleDetail = null;

	private String fkOrgId = null;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDetail() {
		return roleDetail;
	}

	public void setRoleDetail(String roleDetail) {
		this.roleDetail = roleDetail;
	}

	public String getFkOrgId() {
		return fkOrgId;
	}

	public void setFkOrgId(String fkOrgId) {
		this.fkOrgId = fkOrgId;
	}
}
