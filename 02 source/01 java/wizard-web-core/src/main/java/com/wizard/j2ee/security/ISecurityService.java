package com.wizard.j2ee.security;

import com.wizard.j2ee.security.info.Authentication;
import com.wizard.j2ee.security.info.Permission;
import com.wizard.j2ee.security.info.Role;

public interface ISecurityService {

	public Authentication getAuthentication(final String user,
			final String password);

	public Role[] getRoles(final Authentication authentication);

	public Permission[] getPermissions(final Role role);

}
