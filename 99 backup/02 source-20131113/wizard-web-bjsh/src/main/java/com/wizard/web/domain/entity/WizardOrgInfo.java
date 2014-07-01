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
@Table(name = "wizard_org_info")
public class WizardOrgInfo extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8720717869942420338L;

	@Id
	@Column(name = "PK_ID")
	private String pkId = null;

	@Column(name = "ORG_NAME")
	private String orgName = null;

	@Column(name = "ORG_DETAIL")
	private String orgDetail = null;

	@OneToMany
	@JsonIgnore
	@JoinColumn(name = "FK_ORG_ID")
	private Set<WizardRoleInfo> wizardRoleInfo = new HashSet<WizardRoleInfo>();

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

	public Set<WizardRoleInfo> getWizardRoleInfo() {
		return wizardRoleInfo;
	}

	public void setWizardRoleInfo(Set<WizardRoleInfo> wizardRoleInfo) {
		this.wizardRoleInfo = wizardRoleInfo;
	}

}
