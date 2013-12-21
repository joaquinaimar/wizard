package com.wizard.web.domain.entity.wizard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.wizard.j2ee.dao.EntityTemplate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "t_wizard_user_role")
public class WizardUserRole extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6534803873996687246L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "PK_ID")
	private String pkId = null;

	@Column(name = "USER_ID")
	private String userId = null;

	@Column(name = "ROLE_ID")
	private String roleId = null;

	@ManyToOne
	@JoinColumn(name = "USER_ID", insertable = false, updatable = false)
	private WizardUserInfo user = null;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
	private WizardRoleInfo role = null;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public WizardUserInfo getUser() {
		return user;
	}

	public void setUser(WizardUserInfo user) {
		this.user = user;
	}

	public WizardRoleInfo getRole() {
		return role;
	}

	public void setRole(WizardRoleInfo role) {
		this.role = role;
	}

}
