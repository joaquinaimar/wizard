package com.wizard.web.domain.entity.wizard;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.wizard.j2ee.dao.EntityTemplate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "t_wizard_menu")
public class WizardMenu extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1134216562047061676L;

	@Id
	@Column(name = "PK_ID")
	private String pkId = null;

	@Column(name = "P_PK_ID")
	private String pPkId = null;

	@Column(name = "MENU_NAME")
	private String menuName = null;

	@Column(name = "MENU_PATH")
	private String menuPath = null;

	@Column(name = "DISPLAY")
	private String display = null;

	@OneToMany
	@JoinColumn(name = "P_PK_ID")
	private Set<WizardMenu> child = null;

	@OneToMany
	@JoinColumn(name = "MENU_ID")
	private Set<WizardAuthority> authoritys = null;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getPPkId() {
		return pPkId;
	}

	public void setPPkId(String pPkId) {
		this.pPkId = pPkId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Set<WizardMenu> getChild() {
		return child;
	}

	public void setChildMenus(Set<WizardMenu> child) {
		this.child = child;
	}

	public Set<WizardAuthority> getAuthoritys() {
		return authoritys;
	}

	public void setAuthoritys(Set<WizardAuthority> authoritys) {
		this.authoritys = authoritys;
	}

}
