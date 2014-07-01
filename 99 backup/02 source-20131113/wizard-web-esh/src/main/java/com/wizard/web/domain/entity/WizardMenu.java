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
@Table(name = "wizard_menu")
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
	@JsonIgnore
	@JoinColumn(name = "P_PK_ID")
	private Set<WizardMenu> wizardMenu = new HashSet<WizardMenu>();

	@OneToMany
	@JsonIgnore
	@JoinColumn(name = "FK_MENU_ID")
	private Set<WizardAuthority> wizardAuthority = new HashSet<WizardAuthority>();

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

	public Set<WizardMenu> getWizardMenu() {
		return wizardMenu;
	}

	public void setWizardMenu(Set<WizardMenu> wizardMenu) {
		this.wizardMenu = wizardMenu;
	}

	public Set<WizardAuthority> getWizardAuthority() {
		return wizardAuthority;
	}

	public void setWizardAuthority(Set<WizardAuthority> wizardAuthority) {
		this.wizardAuthority = wizardAuthority;
	}

}
