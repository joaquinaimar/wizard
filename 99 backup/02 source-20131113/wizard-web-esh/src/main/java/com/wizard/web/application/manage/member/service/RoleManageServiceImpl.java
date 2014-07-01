package com.wizard.web.application.manage.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.web.application.manage.member.bean.RoleInfo;
import com.wizard.web.application.manage.member.dao.RoleManageDao;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardRoleInfo;

@Service
@Transactional
public class RoleManageServiceImpl implements RoleManageService {

    @Autowired
    private RoleManageDao roleManageDao = null;

    @Override
    public PageResponse<RoleInfo> searchRoleInfo(RoleInfo roleInfo,
            ExtPageRequest pageRequest) {
        return roleManageDao.searchRoleInfo(roleInfo, pageRequest);
    }

    @Override
    public List<WizardRoleInfo> getRoleInfoList() {
        return roleManageDao.getRoleInfoList();
    }

    @Override
    public int insertRoleInfo(String fkOrgId, String roleName, String roleDetail) {
        WizardRoleInfo wizardRoleInfo = new WizardRoleInfo();
        wizardRoleInfo.setPkId(String.valueOf(roleManageDao.getMaxPkId() + 1));
        wizardRoleInfo.setFkOrgId(fkOrgId);
        wizardRoleInfo.setRoleName(roleName);
        wizardRoleInfo.setRoleDetail(roleDetail);
        return roleManageDao.insertRoleInfo(wizardRoleInfo);
    }

    @Override
    public int updateRoleInfo(String pkId, String fkOrgId, String roleName,
            String roleDetail) {
        WizardRoleInfo wizardRoleInfo = new WizardRoleInfo();
        wizardRoleInfo.setPkId(pkId);
        wizardRoleInfo.setFkOrgId(fkOrgId);
        wizardRoleInfo.setRoleName(roleName);
        wizardRoleInfo.setRoleDetail(roleDetail);
        return roleManageDao.updateRoleInfo(wizardRoleInfo);
    }

    @Override
    public int deleteRoleInfo(String[] pkIds) {
        return roleManageDao.deleteRoleInfo(new ParameterCollection(pkIds));
    }

}
