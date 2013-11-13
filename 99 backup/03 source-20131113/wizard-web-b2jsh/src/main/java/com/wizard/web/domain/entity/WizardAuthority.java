package com.wizard.web.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.wizard.web.basic.database.EntityTemplate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "wizard_authority")
public class WizardAuthority extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6977026121655598808L;

	@Id
	@Column(name = "PK_ID")
	private String pkId = null;

	@Column(name = "FK_ROLE_ID")
	private String fkRoleId = null;

	@Column(name = "FK_MENU_ID")
	private String fkMenuId = null;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "FK_ROLE_ID", insertable = false, updatable = false)
	private WizardRoleInfo wizardRoleInfo = null;

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

	public WizardRoleInfo getWizardRoleInfo() {
		return wizardRoleInfo;
	}

	public void setWizardRoleInfo(WizardRoleInfo wizardRoleInfo) {
		this.wizardRoleInfo = wizardRoleInfo;
	}

}
