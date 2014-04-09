package com.wizard.j2ee.security.info;

import java.io.Serializable;

public class Authentication implements Serializable  {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8344034076380593620L;

	private String name = null;

	private String password = null;

	private String detail = null;

	private Role[] roles = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Role[] getRoles() {
		return roles;
	}

	public void setRoles(Role[] roles) {
		this.roles = roles;
	}

}
