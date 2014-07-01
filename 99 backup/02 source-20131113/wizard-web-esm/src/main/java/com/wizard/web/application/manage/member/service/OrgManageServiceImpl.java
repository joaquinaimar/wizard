package com.wizard.web.application.manage.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.web.application.manage.member.dao.OrgManageDao;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardOrgInfo;

@Service
@Transactional
public class OrgManageServiceImpl implements OrgManageService {

    @Autowired
    private OrgManageDao orgManageDao = null;

    @Override
    public PageResponse<WizardOrgInfo> searchOrgInfo(WizardOrgInfo orgInfo,
            ExtPageRequest pageRequest) {
        return orgManageDao.searchOrgInfo(orgInfo, pageRequest);
    }

    @Override
    public List<WizardOrgInfo> getOrgInfoList() {
        return orgManageDao.getOrgInfoList();
    }

    @Override
    public int insertOrgInfo(String orgName, String orgDetail) {
        WizardOrgInfo wizardOrgInfo = new WizardOrgInfo();
        wizardOrgInfo.setPkId(String.valueOf(orgManageDao.getMaxPkId() + 1));
        wizardOrgInfo.setOrgName(orgName);
        wizardOrgInfo.setOrgDetail(orgDetail);
        return orgManageDao.insertOrgInfo(wizardOrgInfo);
    }

    @Override
    public int updateOrgInfo(String pkId, String orgName, String orgDetail) {
        WizardOrgInfo wizardOrgInfo = new WizardOrgInfo();
        wizardOrgInfo.setPkId(pkId);
        wizardOrgInfo.setOrgName(orgName);
        wizardOrgInfo.setOrgDetail(orgDetail);
        return orgManageDao.updateOrgInfo(wizardOrgInfo);
    }

    @Override
    public int deleteOrgInfo(String[] pkIds) {
        return orgManageDao.deleteOrgInfo(new ParameterCollection(pkIds));
    }

}
