package com.wizard.web.application.manage.permission.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wizard.web.application.manage.permission.bean.Menu;
import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardAuthority;

@Repository
public class PermissionManageDao extends BaseDao {

    public PageResponse<Menu> searchMenu(ExtPageRequest request) {
        return super.pageList(
                "wizard.web.framework.manage.permissionManage.searchMenu",
                request);
    }

    public List<WizardAuthority> getAuthority() {
        return super
                .selectList("wizard.web.framework.manage.permissionManage.getAuthority");
    }

    public int clearAuthority() {
        return super
                .delete("wizard.web.framework.manage.permissionManage.clearAuthority");
    }

    public int insertAuthority(WizardAuthority authority) {
        return super.insert(
                "wizard.web.framework.manage.permissionManage.insertAuthority",
                authority);
    }

}
