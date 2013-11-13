package com.wizard.web.domain.entity;

import com.wizard.web.basic.database.EntityTemplate;

public class WizardMenu extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1134216562047061676L;

	private String pkId = null;

	private String pPkId = null;

	private String menuName = null;

	private String menuPath = null;
	
	private String display = null;

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

}
