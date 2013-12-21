package com.wizard.web.domain.entity.wizard;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.wizard.j2ee.dao.EntityTemplate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "t_wizard_org_info")
public class WizardOrgInfo extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8720717869942420338L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "PK_ID")
	private String pkId = null;

	@Column(name = "ORG_NAME")
	private String orgName = null;

	@Column(name = "ORG_DETAIL")
	private String orgDetail = null;

	@OneToMany
	@JoinColumn(name = "ORG_ID")
	private Set<WizardRoleOrg> roles = null;

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

	public Set<WizardRoleOrg> getRoles() {
		return roles;
	}

	public void setRoles(Set<WizardRoleOrg> roles) {
		this.roles = roles;
	}

}
