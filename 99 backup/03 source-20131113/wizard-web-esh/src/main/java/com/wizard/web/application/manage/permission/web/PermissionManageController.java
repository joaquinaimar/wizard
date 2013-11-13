package com.wizard.web.application.manage.permission.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.web.application.manage.permission.bean.Menu;
import com.wizard.web.application.manage.permission.service.PermissionManageService;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.basic.io.extjs.ExtPageResponse;
import com.wizard.web.basic.io.extjs.ExtResponse;
import com.wizard.web.utils.WizardUtils;

@Controller
@RequestMapping("wizardframework/PermissionManage")
public class PermissionManageController {

    @Autowired
    private PermissionManageService permissionManageService = null;

    @RequestMapping(value = "/searchMenu.do", method = RequestMethod.GET)
    @ResponseBody
    public ExtPageResponse<Menu> doSearchMenu(ExtPageRequest pageRequest) {
        PageResponse<Menu> page = permissionManageService
                .searchMenu(pageRequest);
        return new ExtPageResponse<Menu>(true, page);
    }

    @RequestMapping(value = "/getAuthority.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Map<String, List<String>>> doGetAuthority() {
        return new ExtResponse<Map<String, List<String>>>(true,
                permissionManageService.getAuthority());
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/saveAuthority.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doSaveAuthority(@RequestParam String authority) {
        permissionManageService.doSaveAuthority(WizardUtils.toMap(authority));
        return new ExtResponse<Object>(true);
    }

}
