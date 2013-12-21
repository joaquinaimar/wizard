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
@Table(name = "t_wizard_authority")
public class WizardAuthority extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6977026121655598808L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "PK_ID")
	private String pkId = null;

	@Column(name = "ROLE_ID")
	private String roleId = null;

	@Column(name = "MENU_ID")
	private String menuId = null;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
	private WizardRoleInfo role = null;

	@ManyToOne
	@JoinColumn(name = "MENU_ID", insertable = false, updatable = false)
	private WizardMenu menu = null;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public WizardRoleInfo getRole() {
		return role;
	}

	public void setRole(WizardRoleInfo role) {
		this.role = role;
	}

	public WizardMenu getMenu() {
		return menu;
	}

	public void setMenu(WizardMenu menu) {
		this.menu = menu;
	}

}
