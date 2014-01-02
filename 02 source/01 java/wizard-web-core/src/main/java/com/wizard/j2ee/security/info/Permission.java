package com.wizard.j2ee.security.info;

import java.io.Serializable;

public class Permission implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3432495223332284705L;

	private String name = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
