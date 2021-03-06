package com.wizard.web.basic.security.service;

import java.util.List;

import com.wizard.web.domain.entity.WizardOrgInfo;
import com.wizard.web.domain.entity.WizardRoleInfo;
import com.wizard.web.domain.entity.WizardUserInfo;

public interface SecurityService {

	public WizardUserInfo getUserInfo(String userName, String password);

	public List<WizardRoleInfo> getRoleInfo(String userName);

	public List<WizardOrgInfo> getPermissionInfo(String roleName);

}
