package com.wizard.web.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.wizard.web.basic.database.EntityTemplate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "wizard_user_info")
public class WizardUserInfo extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6920648878678598244L;

	@Id
	@Column(name = "PK_ID")
	private String pkId = null;

	@Column(name = "USER_NAME")
	private String userName = null;

	@Column(name = "PASSWORD")
	private String password = null;

	@Column(name = "USER_DETAIL")
	private String userDetail = null;

	@Column(name = "FK_ROLE_ID")
	private String fkRoleId = null;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(String userDetail) {
		this.userDetail = userDetail;
	}

	public String getFkRoleId() {
		return fkRoleId;
	}

	public void setFkRoleId(String fkRoleId) {
		this.fkRoleId = fkRoleId;
	}

}
