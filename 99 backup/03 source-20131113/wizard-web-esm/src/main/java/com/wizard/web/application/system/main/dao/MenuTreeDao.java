package com.wizard.web.application.system.main.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wizard.web.application.system.main.bean.AuthorityMenu;
import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.domain.entity.WizardMenu;

@Repository
public class MenuTreeDao extends BaseDao {

    public List<WizardMenu> getParentNodes(AuthorityMenu authorityMenu) {
        return super.selectList("wizard.web.framework.system.main.getParentNodes",
                authorityMenu);
    }

    public List<WizardMenu> getChildNodes(AuthorityMenu authorityMenu) {
        return super.selectList("wizard.web.framework.system.main.getChildNodes",
                authorityMenu);
    }

}
