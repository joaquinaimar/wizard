package com.wizard.web.application.system.main.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.web.application.system.main.bean.TreeNode;
import com.wizard.web.application.system.main.service.MenuTreeService;
import com.wizard.web.basic.io.extjs.ExtResponse;
import com.wizard.web.basic.log.WizardWebLogger;
import com.wizard.web.utils.WizardWebUtils;

@Controller
@RequestMapping("wizardframework/Main")
public class MainController {

    @Autowired
    private WizardWebLogger wizardLogger = null;

    @Autowired
    private MenuTreeService menuTreeService = null;

    @RequestMapping(value = "/showMenu.do", method = RequestMethod.GET)
    @ResponseBody
    public ExtResponse<List<TreeNode>> doShowMenu(HttpSession session,
            String pid) {
        List<TreeNode> nodes = null;
        if ("0".equals(pid)) {
            nodes = menuTreeService.getParentNodes(WizardWebUtils
                    .getRoleName(session));
        } else {
            nodes = menuTreeService.getChildNodes(pid,
                    WizardWebUtils.getRoleName(session));
        }
        return new ExtResponse<List<TreeNode>>(true, nodes);
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    @ResponseBody
    public String doLogout(HttpServletRequest request) {
        wizardLogger.info("用户注销", request);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        request.getSession().invalidate();
        return "logout";
    }

}
