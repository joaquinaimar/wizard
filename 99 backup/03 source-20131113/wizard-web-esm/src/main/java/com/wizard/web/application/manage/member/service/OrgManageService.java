package com.wizard.web.application.manage.member.service;

import java.util.List;

import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardOrgInfo;

public interface OrgManageService {

    public PageResponse<WizardOrgInfo> searchOrgInfo(WizardOrgInfo orgInfo,
            ExtPageRequest pageRequest);

	public List<WizardOrgInfo> getOrgInfoList();

	public int insertOrgInfo(String orgName, String orgDetail);

	public int updateOrgInfo(String pkId, String orgName, String orgDetail);

    public int deleteOrgInfo(String[] pkIds);

}
