package com.wizard.j2ee.security.info;

import java.io.Serializable;

public class Role implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6150772447950464875L;

	private String name = null;

	private Permission[] permissions = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Permission[] getPermissions() {
		return permissions;
	}

	public void setPermissions(Permission[] permissions) {
		this.permissions = permissions;
	}

}
