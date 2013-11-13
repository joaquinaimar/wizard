package com.wizard.web.application.manage.permission.service;

import java.util.List;
import java.util.Map;

import com.wizard.web.application.manage.permission.bean.Menu;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.extjs.ExtPageRequest;

public interface PermissionManageService {

    public PageResponse<Menu> searchMenu(ExtPageRequest request);

    public Map<String, List<String>> getAuthority();

    public void doSaveAuthority(Map<String, List<String>> map);

}
