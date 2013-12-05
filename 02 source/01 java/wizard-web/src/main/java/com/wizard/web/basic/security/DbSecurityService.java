package com.wizard.web.basic.security;

import org.springframework.stereotype.Component;

import com.wizard.j2ee.security.ISecurityService;
import com.wizard.j2ee.security.info.Authentication;
import com.wizard.j2ee.security.info.Permission;
import com.wizard.j2ee.security.info.Role;

@Component
public class DbSecurityService implements ISecurityService {

	@Override
	public Authentication getAuthentication(String user, String password) {
		return null;
	}

	@Override
	public Role[] getRoles(Authentication authentication) {
		return null;
	}

	@Override
	public Permission[] getPermissions(Role role) {
		return null;
	}

}
