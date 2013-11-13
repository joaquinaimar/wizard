package com.wizard.web.application.system.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.web.application.system.main.bean.AuthorityMenu;
import com.wizard.web.application.system.main.bean.TreeNode;
import com.wizard.web.application.system.main.dao.MenuTreeDao;
import com.wizard.web.domain.entity.WizardMenu;

@Service
@Transactional
public class MenuTreeServiceImpl implements MenuTreeService {

    @Autowired
    private MenuTreeDao menuTreeDao = null;

    @Override
    public List<TreeNode> getParentNodes(String roleName) {
        AuthorityMenu authorityMenu = new AuthorityMenu();
        authorityMenu.setRoleName(roleName);
        List<WizardMenu> menus = menuTreeDao.getParentNodes(authorityMenu);
        return getNodes(menus);
    }

    @Override
    public List<TreeNode> getChildNodes(String pid, String roleName) {
        AuthorityMenu authorityMenu = new AuthorityMenu();
        authorityMenu.setPid(pid);
        authorityMenu.setRoleName(roleName);
        List<WizardMenu> menus = menuTreeDao.getChildNodes(authorityMenu);
        return getNodes(menus);
    }

    private List<TreeNode> getNodes(List<WizardMenu> menus) {
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        TreeNode node = null;
        for (WizardMenu menu : menus) {
            node = new TreeNode();
            node.setId(menu.getPkId());
            node.setText(menu.getMenuName());
            if (null == menu.getMenuPath() || "".equals(menu.getMenuPath())) {
                node.setLeaf(false);
            } else {
                node.setUrl((String) menu.getMenuPath());
                node.setLeaf(true);
            }

            nodes.add(node);
        }
        return nodes;
    }
}
