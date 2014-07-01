package com.wizard.web.application.manage.menu.dao;

import org.springframework.stereotype.Repository;

import com.wizard.web.application.manage.menu.bean.Menu;
import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.basic.io.PageRequest;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.domain.entity.WizardMenu;

@Repository
public class MenuManageDao extends BaseDao {

    public String getFirstPage() {
        return selectOne("wizard.web.framework.manage.menuManage.getFirstPage");
    }

    public int updateFirstPage(String menuPath) {
        return update("wizard.web.framework.manage.menuManage.updateFirstPage",
                menuPath);
    }

    public PageResponse<Menu> getParentMenu(PageRequest request) {
        return pageList("wizard.web.framework.manage.menuManage.getParentMenu",
                request);
    }

    public int updateMenu(Menu menu) {
        return update("wizard.web.framework.manage.menuManage.updateMenu", menu);
    }

    public int batchUpdateMenu(Menu menu) {
        return update("wizard.web.framework.manage.menuManage.batchUpdateMenu",
                menu);
    }

    public PageResponse<Menu> getChildMenu(String pPkId, PageRequest request) {
        return pageList("wizard.web.framework.manage.menuManage.getChildMenu",
                pPkId, request);
    }

    public int insertMenu(WizardMenu wizardMenu) {
        return insert("wizard.web.framework.manage.menuManage.insertMenu",
                wizardMenu);
    }

    public int deleteMenu(ParameterCollection collection) {
        return delete("wizard.web.framework.manage.menuManage.deleteMenu",
                collection);
    }

    public int deleteChildMenu(ParameterCollection collection) {
        return delete("wizard.web.framework.manage.menuManage.deleteChildMenu",
                collection);
    }

}
