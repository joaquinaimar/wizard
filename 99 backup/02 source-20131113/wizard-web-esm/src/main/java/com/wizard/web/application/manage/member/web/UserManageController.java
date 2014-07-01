package com.wizard.web.application.manage.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.web.application.manage.member.bean.UserInfo;
import com.wizard.web.application.manage.member.service.UserManageService;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.basic.io.extjs.ExtPageResponse;
import com.wizard.web.basic.io.extjs.ExtResponse;
import com.wizard.web.basic.log.WizardWebLogger;
import com.wizard.web.domain.entity.WizardUserInfo;
import com.wizard.web.utils.SecurityUtils;
import com.wizard.web.utils.WizardWebUtils;

@Controller
@RequestMapping("wizardframework/UserManage")
public class UserManageController {

    @Autowired
    private WizardWebLogger wizardLogger = null;

    @Autowired
    private UserManageService userManageService = null;

    @RequestMapping(value = "/getUserInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<WizardUserInfo> doGetUserInfo(HttpSession session) {
        String userName = WizardWebUtils.getUserName(session);
        WizardUserInfo wizardUserInfo = userManageService.getUserInfo(userName);
        return new ExtResponse<WizardUserInfo>(true, wizardUserInfo);
    }

    @RequestMapping(value = "/modifyPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doModifyPassword(HttpServletRequest request,
            @RequestParam String pkId, @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        wizardLogger.info("修改密码", request);
        ExtResponse<Object> ext = null;
        int result = userManageService.modifyPassword(pkId,
                SecurityUtils.encodeMd5(oldPassword),
                SecurityUtils.encodeMd5(newPassword));
        if (1 == result) {
            wizardLogger.info("密码修改成功！", request);
            ext = new ExtResponse<Object>(true, "密码修改成功！请重新登录！");
        } else {
            wizardLogger.warn("密码修改失败！", request);
            ext = new ExtResponse<Object>(false, "密码修改失败！原密码输入不正确！");
        }
        return ext;
    }

    @RequestMapping(value = "/searchUserInfo.do", method = RequestMethod.GET)
    @ResponseBody
    public ExtPageResponse<UserInfo> doSearchUserInfo(UserInfo userInfo,
            ExtPageRequest pageRequest) {
        PageResponse<UserInfo> page = userManageService.searchUserInfo(
                userInfo, pageRequest);
        return new ExtPageResponse<UserInfo>(true, page);
    }

    @RequestMapping(value = "/insertUserInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doInsertUserInfo(HttpServletRequest request,
            @RequestParam String fkRoleId, @RequestParam String userName,
            @RequestParam String userDetail) {
        int result = userManageService.insertUserInfo(fkRoleId, userName,
                userDetail);
        String message = (1 == result) ? "新增用户成功" : "新增用户失败";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/updateUserInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doUpdateUserInfo(HttpServletRequest request,
            @RequestParam String pkId, @RequestParam String fkRoleId,
            @RequestParam String userName, @RequestParam String userDetail) {
        int result = userManageService.updateUserInfo(pkId, fkRoleId, userName,
                userDetail);
        String message = (1 == result) ? "用户修改成功" : "用户修改失败";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/deleteUserInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doDeleteUserInfo(HttpServletRequest request,
            @RequestParam String[] pkIds) {
        int result = userManageService.deleteUserInfo(pkIds);
        String message = "删除用户" + result + "条";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

}
