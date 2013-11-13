package com.wizard.web.application.manage.member.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.web.application.manage.member.bean.RoleInfo;
import com.wizard.web.application.manage.member.service.RoleManageService;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.basic.io.extjs.ExtPageResponse;
import com.wizard.web.basic.io.extjs.ExtResponse;
import com.wizard.web.basic.log.WizardWebLogger;
import com.wizard.web.domain.entity.WizardRoleInfo;

@Controller
@RequestMapping("wizardframework/RoleManage")
public class RoleManageController {

    @Autowired
    private WizardWebLogger wizardLogger = null;

    @Autowired
    private RoleManageService roleManageService = null;

    @RequestMapping(value = "/searchRoleInfo.do", method = RequestMethod.GET)
    @ResponseBody
    public ExtPageResponse<RoleInfo> doSearchRoleInfo(RoleInfo roleInfo,
            ExtPageRequest pageRequest) {
        PageResponse<RoleInfo> page = roleManageService.searchRoleInfo(
                roleInfo, pageRequest);
        return new ExtPageResponse<RoleInfo>(true, page);
    }

    @RequestMapping(value = "/getRoleInfoList.do", method = RequestMethod.GET)
    @ResponseBody
    public ExtResponse<List<WizardRoleInfo>> doGetRoleInfoList() {
        List<WizardRoleInfo> list = roleManageService.getRoleInfoList();
        return new ExtResponse<List<WizardRoleInfo>>(true, list);
    }

    @RequestMapping(value = "/insertRoleInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doInsertRoleInfo(HttpServletRequest request,
            @RequestParam String fkOrgId, @RequestParam String roleName,
            @RequestParam String roleDetail) {
        int result = roleManageService.insertRoleInfo(fkOrgId, roleName,
                roleDetail);
        String message = (1 == result) ? "新增角色成功" : "新增角色失败";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/updateRoleInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doUpdateRoleInfo(HttpServletRequest request,
            @RequestParam String pkId, @RequestParam String fkOrgId,
            @RequestParam String roleName, @RequestParam String roleDetail) {
        int result = roleManageService.updateRoleInfo(pkId, fkOrgId, roleName,
                roleDetail);
        String message = (1 == result) ? "角色修改成功" : "角色修改失败";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/deleteRoleInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doDeleteRoleInfo(HttpServletRequest request,
            @RequestParam String[] pkIds) {
        int result = roleManageService.deleteRoleInfo(pkIds);
        String message = "删除角色" + result + "条";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

}
