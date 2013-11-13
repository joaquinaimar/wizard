package com.wizard.web.application.manage.member.service;

import com.wizard.web.application.manage.member.bean.UserInfo;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardUserInfo;

public interface UserManageService {

	public WizardUserInfo getUserInfo(String userName);

	public int modifyPassword(String pkId, String oldPassword,
			String newPassword);

    public PageResponse<UserInfo> searchUserInfo(UserInfo userInfo,
            ExtPageRequest pageRequest);

	public int insertUserInfo(String fkRoleId, String userName,
			String userDetail);

	public int updateUserInfo(String pkId, String fkRoleId, String userName,
			String userDetail);

    public int deleteUserInfo(String[] pkIds);

}
