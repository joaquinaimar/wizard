package com.wizard.web.application.manage.member.service;

import java.util.List;

import com.wizard.web.application.manage.member.bean.RoleInfo;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardRoleInfo;

public interface RoleManageService {

	public PageResponse<RoleInfo> searchRoleInfo(RoleInfo roleInfo,
			ExtPageRequest pageRequest);

	public List<WizardRoleInfo> getRoleInfoList();

	public int insertRoleInfo(String fkOrgId, String roleName, String roleDetail);

	public int updateRoleInfo(String pkId, String fkOrgId, String roleName,
			String roleDetail);

    public int deleteRoleInfo(String[] pkIds);

}
