package com.wizard.web.basic.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.j2ee.security.ISecurityService;
import com.wizard.j2ee.security.info.Authentication;
import com.wizard.j2ee.security.info.Permission;
import com.wizard.j2ee.security.info.Role;
import com.wizard.util.common.CommonUtil;
import com.wizard.web.domain.entity.wizard.WizardMenu;
import com.wizard.web.domain.entity.wizard.WizardRoleInfo;
import com.wizard.web.domain.entity.wizard.WizardUserInfo;

@Service
@Transactional
public class DbSecurityService implements ISecurityService {

	@Autowired
	private SecurityDao securityDao = null;

	@Override
	public Authentication getAuthentication(String user, String password) {
		WizardUserInfo userInfo = this.securityDao.getLoginUserInfo(user,
				password);
		Authentication authentication = null;
		if (!CommonUtil.isNull(userInfo)) {
			authentication = new Authentication();
			authentication.setName(userInfo.getUsername());
			authentication.setPassword(userInfo.getPassword());
			authentication.setDetail(userInfo.getUserDetail());
		}
		return authentication;
	}

	@Override
	public Role[] getRoles(Authentication authentication) {
		List<WizardRoleInfo> roleList = this.securityDao
				.getRoles(authentication.getName());
		Role[] roles = new Role[roleList.size()];
		for (int i = 0; i < roleList.size(); i++) {
			roles[i] = new Role();
			roles[i].setName(roleList.get(i).getPkId());
		}
		return roles;
	}

	@Override
	public Permission[] getPermissions(Role role) {
		List<WizardMenu> permissionList = this.securityDao.getPermissions(role
				.getName());
		Permission[] permissions = new Permission[permissionList.size()];
		for (int i = 0; i < permissionList.size(); i++) {
			permissions[i] = new Permission();
			permissions[i].setName(permissionList.get(i).getPkId());
		}
		return permissions;
	}

}
