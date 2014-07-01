package com.wizard.web.application.manage.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.web.application.manage.member.bean.UserInfo;
import com.wizard.web.application.manage.member.dao.UserManageDao;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardUserInfo;
import com.wizard.web.utils.SecurityUtils;

@Service
@Transactional
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    private UserManageDao userManageDao = null;

    @Override
    public int modifyPassword(String pkId, String oldPassword,
            String newPassword) {
        UserInfo userInfo = new UserInfo();
        userInfo.setPkId(pkId);
        userInfo.setPassword(oldPassword);
        userInfo.setNewPassword(newPassword);
        return userManageDao.modifyPassword(userInfo);
    }

    @Override
    public WizardUserInfo getUserInfo(String userName) {
        return userManageDao.getUserInfo(userName);
    }

    @Override
    public PageResponse<UserInfo> searchUserInfo(UserInfo userInfo,
            ExtPageRequest pageRequest) {
        return userManageDao.searchUserInfo(userInfo, pageRequest);
    }

    @Override
    public int insertUserInfo(String fkRoleId, String userName,
            String userDetail) {
        WizardUserInfo wizardUserInfo = new WizardUserInfo();
        wizardUserInfo.setPkId(String.valueOf(userManageDao.getMaxPkId() + 1));
        wizardUserInfo.setFkRoleId(fkRoleId);
        wizardUserInfo.setUserName(userName);
        wizardUserInfo.setPassword(SecurityUtils.encodeMd5("123"));
        wizardUserInfo.setUserDetail(userDetail);
        return userManageDao.insertUserInfo(wizardUserInfo);
    }

    @Override
    public int updateUserInfo(String pkId, String fkRoleId, String userName,
            String userDetail) {
        WizardUserInfo wizardUserInfo = new WizardUserInfo();
        wizardUserInfo.setPkId(pkId);
        wizardUserInfo.setFkRoleId(fkRoleId);
        wizardUserInfo.setUserName(userName);
        wizardUserInfo.setUserDetail(userDetail);
        return userManageDao.updateUserInfo(wizardUserInfo);
    }

    @Override
    public int deleteUserInfo(String[] pkIds) {
        return userManageDao.deleteUserInfo(new ParameterCollection(pkIds));
    }

}
