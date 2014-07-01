package com.wizard.web.application.manage.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wizard.web.application.manage.member.bean.RoleInfo;
import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardRoleInfo;

@Repository
public class RoleManageDao extends BaseDao {

	public PageResponse<RoleInfo> searchRoleInfo(RoleInfo roleInfo,
			ExtPageRequest request) {
		return super.pageList(
				"wizard.web.framework.manage.member.roleManage.searchRoleInfo",
				roleInfo, request);
	}

	public List<WizardRoleInfo> getRoleInfoList() {
		return super
				.selectList("wizard.web.framework.manage.member.roleManage.getRoleInfoList");
	}

	public Integer getMaxPkId() {
		Integer result = selectOne("wizard.web.framework.manage.member.roleManage.getMaxPkId");
		return (null == result) ? 0 : result;
	}

	public int insertRoleInfo(WizardRoleInfo wizardRoleInfo) {
		return super.insert(
				"wizard.web.framework.manage.member.roleManage.insertRoleInfo",
				wizardRoleInfo);
	}

	public int updateRoleInfo(WizardRoleInfo wizardRoleInfo) {
		return super.update(
				"wizard.web.framework.manage.member.roleManage.updateRoleInfo",
				wizardRoleInfo);
	}

	public int deleteRoleInfo(ParameterCollection collection) {
		return delete(
				"wizard.web.framework.manage.member.roleManage.deleteRoleInfo",
				collection);
	}

}
