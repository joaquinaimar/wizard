package com.wizard.web.application.manage.menu.service;

import com.wizard.web.application.manage.menu.bean.Menu;
import com.wizard.web.basic.io.PageRequest;
import com.wizard.web.basic.io.PageResponse;

public interface MenuManageService {

	public String getFirstPage();

	public int updateFirstPage(String menuPath);

	public PageResponse<Menu> getParentMenu(PageRequest request);

	public int updateParentMenu(String pkId, String newId, String menuName,
			String display);

	public PageResponse<Menu> getChildMenu(String pPkId, PageRequest request);

	public int updateChildMenu(String pkId, String newId, String menuName,
			String menuPath, String display);

	public int insertMenu(String pkId, String pPkId, String menuName,
			String menuPath, String display);

	public int deleteParentMenu(String[] pkIds);

	public int deleteChildMenu(String[] pkIds);

}
