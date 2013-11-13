package com.wizard.web.application.manage.menu.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.web.application.manage.menu.bean.Menu;
import com.wizard.web.application.manage.menu.service.MenuManageService;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.basic.io.extjs.ExtPageResponse;
import com.wizard.web.basic.io.extjs.ExtResponse;
import com.wizard.web.basic.log.WizardWebLogger;

@Controller
@RequestMapping("wizardframework/MenuManage")
public class MenuManageController {

    @Autowired
    private WizardWebLogger wizardLogger = null;

    @Autowired
    private MenuManageService menuManageService = null;

    @RequestMapping(value = "/getFirstPage.do", method = RequestMethod.GET)
    @ResponseBody
    public ExtResponse<Object> doGetFirstPage() {
        String menuPath = menuManageService.getFirstPage();
        return new ExtResponse<Object>(true, menuPath);
    }

    @RequestMapping(value = "/updateFirstPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doUpdateFirstPage(HttpServletRequest request,
            @RequestParam String welcomePath) {

        int result = menuManageService.updateFirstPage(welcomePath);

        String message = (1 == result) ? "首页修改成功" : "首页修改失败";

        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/getParentMenu.do", method = RequestMethod.GET)
    @ResponseBody
    public ExtPageResponse<Menu> doGetParentMenu(ExtPageRequest pageRequest) {
        PageResponse<Menu> page = menuManageService.getParentMenu(pageRequest);
        return new ExtPageResponse<Menu>(true, page);
    }

    @RequestMapping(value = "/updateParentMenu.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doUpdateParentMenu(HttpServletRequest request,
            @RequestParam String pkId, @RequestParam String newId,
            @RequestParam String menuName, @RequestParam String display) {

        int result = menuManageService.updateParentMenu(pkId, newId, menuName,
                display);

        String message = (1 == result) ? "父菜单修改成功" : "父菜单修改失败";

        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/getChildMenu.do", method = RequestMethod.GET)
    @ResponseBody
    public ExtPageResponse<Menu> doGetChildMenu(HttpServletRequest request,
            String pPkId, ExtPageRequest pageRequest) {
        PageResponse<Menu> page = menuManageService.getChildMenu(pPkId,
                pageRequest);
        return new ExtPageResponse<Menu>(true, page);
    }

    @RequestMapping(value = "/updateChildMenu.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doUpdateChildMenu(HttpServletRequest request,
            @RequestParam String pkId, @RequestParam String newId,
            @RequestParam String menuName, @RequestParam String menuPath,
            @RequestParam String display) {
        int result = menuManageService.updateChildMenu(pkId, newId, menuName,
                menuPath, display);

        String message = (1 == result) ? "子菜单修改成功" : "子菜单修改失败";

        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/insertMenu.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doInsertMenu(HttpServletRequest request,
            @RequestParam String pkId, @RequestParam String pPkId,
            @RequestParam String menuName, @RequestParam String menuPath,
            @RequestParam String display) {

        int result = menuManageService.insertMenu(pkId, pPkId, menuName,
                menuPath, display);
        String message = (1 == result) ? "新增菜单成功" : "新增菜单失败";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/deleteParentMenu.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doDeleteParentMenu(HttpServletRequest request,
            @RequestParam String[] pkIds) {
        int result = menuManageService.deleteParentMenu(pkIds);
        String message = "父菜单成功删除" + result + "条";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/deleteChildMenu.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doDeleteChildMenu(HttpServletRequest request,
            @RequestParam String[] pkIds) {
        int result = menuManageService.deleteChildMenu(pkIds);
        String message = "子菜单成功删除" + result + "条";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

}
