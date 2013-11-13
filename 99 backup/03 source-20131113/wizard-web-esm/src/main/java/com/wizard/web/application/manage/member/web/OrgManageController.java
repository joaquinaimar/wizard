package com.wizard.web.application.manage.member.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.web.application.manage.member.service.OrgManageService;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.basic.io.extjs.ExtPageResponse;
import com.wizard.web.basic.io.extjs.ExtResponse;
import com.wizard.web.basic.log.WizardWebLogger;
import com.wizard.web.domain.entity.WizardOrgInfo;

@Controller
@RequestMapping("wizardframework/OrgManage")
public class OrgManageController {

    @Autowired
    private WizardWebLogger wizardLogger = null;

    @Autowired
    private OrgManageService orgManageService = null;

    @RequestMapping(value = "/searchOrgInfo.do", method = RequestMethod.GET)
    @ResponseBody
    public ExtPageResponse<WizardOrgInfo> doSearchOrgInfo(
            WizardOrgInfo orgInfo, ExtPageRequest pageRequest) {
        PageResponse<WizardOrgInfo> page = orgManageService.searchOrgInfo(
                orgInfo, pageRequest);
        return new ExtPageResponse<WizardOrgInfo>(true, page);
    }

    @RequestMapping(value = "/getOrgInfoList.do", method = RequestMethod.GET)
    @ResponseBody
    public ExtResponse<List<WizardOrgInfo>> doGetOrgInfoList() {
        List<WizardOrgInfo> list = orgManageService.getOrgInfoList();
        return new ExtResponse<List<WizardOrgInfo>>(true, list);
    }

    @RequestMapping(value = "/insertOrgInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doInsertOrgInfo(HttpServletRequest request,
            @RequestParam String orgName, @RequestParam String orgDetail) {
        int result = orgManageService.insertOrgInfo(orgName, orgDetail);
        String message = (1 == result) ? "新增组织成功" : "新增组织失败";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/updateOrgInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doUpdateOrgInfo(HttpServletRequest request,
            @RequestParam String pkId, @RequestParam String orgName,
            @RequestParam String orgDetail) {
        int result = orgManageService.updateOrgInfo(pkId, orgName, orgDetail);
        String message = (1 == result) ? "组织修改成功" : "组织修改失败";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/deleteOrgInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doDeleteOrgInfo(HttpServletRequest request,
            @RequestParam String[] pkIds) {
        int result = orgManageService.deleteOrgInfo(pkIds);
        String message = "删除组织" + result + "条";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

}
