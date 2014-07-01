package com.wizard.web.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.wizard.web.basic.database.EntityTemplate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "wizard_role_info")
public class WizardRoleInfo extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -602118531530483270L;

	@Id
	@Column(name = "PK_ID")
	private String pkId = null;

	@Column(name = "ROLE_NAME")
	private String roleName = null;

	@Column(name = "ROLE_DETAIL")
	private String roleDetail = null;

	@Column(name = "FK_ORG_ID")
	private String fkOrgId = null;

	@OneToMany
	@JsonIgnore
	@JoinColumn(name = "FK_ROLE_ID")
	private Set<WizardUserInfo> wizardUserInfo = new HashSet<WizardUserInfo>();

	@OneToMany
	@JsonIgnore
	@JoinColumn(name = "FK_ROLE_ID")
	private Set<WizardAuthority> wizardAuthority = new HashSet<WizardAuthority>();

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

	public Set<WizardUserInfo> getWizardUserInfo() {
		return wizardUserInfo;
	}

	public void setWizardUserInfo(Set<WizardUserInfo> wizardUserInfo) {
		this.wizardUserInfo = wizardUserInfo;
	}

	public Set<WizardAuthority> getWizardAuthority() {
		return wizardAuthority;
	}

	public void setWizardAuthority(Set<WizardAuthority> wizardAuthority) {
		this.wizardAuthority = wizardAuthority;
	}

}
