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
@Table(name = "t_wizard_user_info")
public class WizardUserInfo extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6920648878678598244L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "PK_ID")
	private String pkId = null;

	@Column(name = "USERNAME")
	private String username = null;

	@Column(name = "PASSWORD")
	private String password = null;

	@Column(name = "USER_DETAIL")
	private String userDetail = null;

	@OneToMany
	@JoinColumn(name = "USER_ID")
	private Set<WizardUserRole> roles = null;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Set<WizardUserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<WizardUserRole> roles) {
		this.roles = roles;
	}

}
