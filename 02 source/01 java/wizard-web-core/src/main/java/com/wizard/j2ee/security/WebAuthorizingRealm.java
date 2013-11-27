package com.wizard.j2ee.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.wizard.j2ee.security.info.Authentication;
import com.wizard.j2ee.security.info.Permission;
import com.wizard.j2ee.security.info.Role;
import com.wizard.util.common.CommonUtil;
import com.wizard.util.common.StringUtil;

public class WebAuthorizingRealm extends AuthorizingRealm {

	private ISecurityService securityService = null;

	public void setSecurityService(ISecurityService securityService) {
		this.securityService = securityService;
	}

	public WebAuthorizingRealm() {
		super();
		setAuthenticationTokenClass(UsernamePasswordToken.class);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String name = token.getUsername();
		if (!StringUtil.isBlank(name)) {
			Authentication authentication = securityService.getAuthentication(
					name, String.valueOf(token.getPassword()));
			if (!CommonUtil.isNull(authentication)) {
				SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
						authentication, authentication.getPassword(), getName());
				return info;
			}
		}
		return null;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Authentication authentication = (Authentication) principals
				.fromRealm(getName()).iterator().next();
		Role[] roles = securityService.getRoles(authentication);
		Permission[] permissionos = null;
		if (!CommonUtil.isEmpty(roles)) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (Role role : roles) {
				info.addRole(role.getName());
				permissionos = securityService.getPermissions(role);
				for (Permission permission : permissionos)
					info.addStringPermission(permission.getName());
			}
			return info;
		}
		return null;
	}

	public AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
		return super.getAuthorizationInfo(principals);
	}
}
