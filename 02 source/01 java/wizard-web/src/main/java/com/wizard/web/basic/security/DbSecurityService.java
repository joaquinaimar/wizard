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
		Authentication authentication = new Authentication();
		authentication.setName(user);
		authentication.setPassword(password);
		return authentication;
	}

	@Override
	public Role[] getRoles(Authentication authentication) {
		Role role1 = new Role();
		return new Role[] { role1 };
	}

	@Override
	public Permission[] getPermissions(Role role) {
		Permission permission1 = new Permission();
		return new Permission[] { permission1 };
	}

}
