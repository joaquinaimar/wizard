package com.wizard.web.application.manage.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardOrgInfo;

@Repository
public class OrgManageDao extends BaseDao {

	public PageResponse<WizardOrgInfo> searchOrgInfo(WizardOrgInfo orgInfo,
			ExtPageRequest request) {
		return super.pageList(
				"wizard.web.framework.manage.member.orgManage.searchOrgInfo",
				orgInfo, request);
	}

	public List<WizardOrgInfo> getOrgInfoList() {
		return super
				.selectList("wizard.web.framework.manage.member.orgManage.getOrgInfoList");
	}

	public Integer getMaxPkId() {
		Integer result = selectOne("wizard.web.framework.manage.member.orgManage.getMaxPkId");
		return (null == result) ? 0 : result;
	}

	public int insertOrgInfo(WizardOrgInfo wizardOrgInfo) {
		return super.insert(
				"wizard.web.framework.manage.member.orgManage.insertOrgInfo",
				wizardOrgInfo);
	}

	public int updateOrgInfo(WizardOrgInfo wizardOrgInfo) {
		return super.update(
				"wizard.web.framework.manage.member.orgManage.updateOrgInfo",
				wizardOrgInfo);
	}

	public int deleteOrgInfo(ParameterCollection collection) {
		return delete(
				"wizard.web.framework.manage.member.orgManage.deleteOrgInfo",
				collection);
	}

}
