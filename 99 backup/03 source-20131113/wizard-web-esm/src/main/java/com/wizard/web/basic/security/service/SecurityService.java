package com.wizard.web.basic.security.service;

import java.util.List;

import com.wizard.web.domain.entity.WizardUserInfo;

public interface SecurityService {

	public WizardUserInfo getUserInfo(String userName, String password);

	public List<String> getRoleInfo(String userName);

	public List<String> getPermissionInfo(String roleName);

}
