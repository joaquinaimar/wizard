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
@Table(name = "t_wizard_role_info")
public class WizardRoleInfo extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -602118531530483270L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "PK_ID")
	private String pkId = null;

	@Column(name = "ROLE_NAME")
	private String roleName = null;

	@Column(name = "ROLE_DETAIL")
	private String roleDetail = null;

	@OneToMany
	@JoinColumn(name = "ROLE_ID")
	private Set<WizardUserRole> users = null;

	@OneToMany
	@JoinColumn(name = "ROLE_ID")
	private Set<WizardRoleOrg> orgs = null;

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

	public Set<WizardUserRole> getUsers() {
		return users;
	}

	public void setUsers(Set<WizardUserRole> users) {
		this.users = users;
	}

	public Set<WizardRoleOrg> getOrgs() {
		return orgs;
	}

	public void setOrgs(Set<WizardRoleOrg> orgs) {
		this.orgs = orgs;
	}

}
