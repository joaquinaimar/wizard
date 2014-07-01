package com.wizard.web.basic.security;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.wizard.web.basic.security.service.SecurityService;
import com.wizard.web.domain.entity.WizardOrgInfo;
import com.wizard.web.domain.entity.WizardRoleInfo;
import com.wizard.web.domain.entity.WizardUserInfo;

public class WizardWebRealm extends AuthorizingRealm {

	private SecurityService securityService = null;

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public WizardWebRealm() {
		super();
		// 设置认证token的实现类
		setAuthenticationTokenClass(UsernamePasswordToken.class);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();
		if (userName != null && !"".equals(userName)) {
			WizardUserInfo userInfo = securityService.getUserInfo(userName,
					String.valueOf(token.getPassword()));
			if (userInfo != null) {
				String password = userInfo.getPassword();
				SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
						userName, password, getName());
				return info;
			}
		}
		return null;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userName = (String) principals.fromRealm(getName()).iterator()
				.next();
		List<WizardRoleInfo> roleInfos = securityService.getRoleInfo(userName);
		List<WizardOrgInfo> permissionInfos = null;
		if (null == roleInfos || 0 == roleInfos.size()) {
			return null;
		} else {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (WizardRoleInfo roleInfo : roleInfos) {
				info.addRole(roleInfo.getRoleName());
				permissionInfos = securityService.getPermissionInfo(roleInfo
						.getRoleName());
				for (WizardOrgInfo permissionInfo : permissionInfos) {
					info.addStringPermission(permissionInfo.getOrgName());
				}
			}
			return info;
		}

	}

	public AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
		return super.getAuthorizationInfo(principals);
	}

}
