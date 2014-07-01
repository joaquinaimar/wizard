package com.wizard.web.basic.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.web.basic.security.dao.SecurityDao;
import com.wizard.web.domain.entity.WizardOrgInfo;
import com.wizard.web.domain.entity.WizardRoleInfo;
import com.wizard.web.domain.entity.WizardUserInfo;

@Service("securityService")
@Transactional
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private SecurityDao securityDao = null;

	@Override
	public WizardUserInfo getUserInfo(String userName, String password) {
		WizardUserInfo userInfo = new WizardUserInfo();
		userInfo.setUserName(userName);
		userInfo.setPassword(password);

		return securityDao.authenticationQuery(userInfo);
	}

	@Override
	public List<WizardRoleInfo> getRoleInfo(String userName) {
		return securityDao.userRolesQuery(userName);
	}

	@Override
	public List<WizardOrgInfo> getPermissionInfo(String roleName) {
		return securityDao.permissionsQuery(roleName);
	}

}
