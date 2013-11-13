package com.wizard.web.application.manage.member.dao;

import org.springframework.stereotype.Repository;

import com.wizard.web.application.manage.member.bean.UserInfo;
import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardUserInfo;

@Repository
public class UserManageDao extends BaseDao {

	public WizardUserInfo getUserInfo(String userName) {
		return super.selectOne(
				"wizard.web.framework.manage.member.userManage.getUserInfo",
				userName);
	}

	public int modifyPassword(UserInfo userInfo) {
		return super.update(
				"wizard.web.framework.manage.member.userManage.modifyPassword",
				userInfo);
	}

	public PageResponse<UserInfo> searchUserInfo(UserInfo userInfo,
			ExtPageRequest request) {
		return super.pageList(
				"wizard.web.framework.manage.member.userManage.searchUserInfo",
				userInfo, request);
	}

	public Integer getMaxPkId() {
		Integer result = selectOne("wizard.web.framework.manage.member.userManage.getMaxPkId");
		return (null == result) ? 0 : result;
	}

	public int insertUserInfo(WizardUserInfo wizardUserInfo) {
		return super.insert(
				"wizard.web.framework.manage.member.userManage.insertUserInfo",
				wizardUserInfo);
	}

	public int updateUserInfo(WizardUserInfo wizardUserInfo) {
		return super.update(
				"wizard.web.framework.manage.member.userManage.updateUserInfo",
				wizardUserInfo);
	}

	public int deleteUserInfo(ParameterCollection collection) {
		return delete(
				"wizard.web.framework.manage.member.userManage.deleteUserInfo",
				collection);
	}

}
