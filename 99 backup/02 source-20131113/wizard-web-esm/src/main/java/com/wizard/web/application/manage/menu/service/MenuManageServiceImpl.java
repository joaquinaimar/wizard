package com.wizard.web.application.manage.menu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.web.application.manage.menu.bean.Menu;
import com.wizard.web.application.manage.menu.dao.MenuManageDao;
import com.wizard.web.basic.io.PageRequest;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.domain.entity.WizardMenu;

@Service
@Transactional
public class MenuManageServiceImpl implements MenuManageService {

	@Autowired
	private MenuManageDao menuManageDao = null;

	@Override
	public String getFirstPage() {
		return menuManageDao.getFirstPage();
	}

	@Override
	public int updateFirstPage(String menuPath) {
		return menuManageDao.updateFirstPage(menuPath);
	}

	@Override
	public PageResponse<Menu> getParentMenu(PageRequest request) {
		return menuManageDao.getParentMenu(request);
	}

	@Override
	public int updateParentMenu(String pkId, String newId, String menuName,
			String display) {
		Menu menu = new Menu();
		menu.setPkId(pkId);
		menu.setNewId(newId);
		menu.setMenuName(menuName);
		menu.setDisplay(display);
		if (!pkId.equals(newId)) {
			menuManageDao.batchUpdateMenu(menu);
		}
		return menuManageDao.updateMenu(menu);
	}

	@Override
	public PageResponse<Menu> getChildMenu(String pPkId, PageRequest request) {
		return menuManageDao.getChildMenu(pPkId, request);
	}

	@Override
	public int updateChildMenu(String pkId, String newId, String menuName,
			String menuPath, String display) {
		Menu menu = new Menu();
		menu.setPkId(pkId);
		menu.setNewId(newId);
		menu.setMenuName(menuName);
		menu.setMenuPath(menuPath);
		menu.setDisplay(display);
		return menuManageDao.updateMenu(menu);
	}

	@Override
	public int insertMenu(String pkId, String pPkId, String menuName,
			String menuPath, String display) {
		WizardMenu wizardMenu = new WizardMenu();
		wizardMenu.setPkId(pkId);
		wizardMenu.setPPkId(pPkId);
		wizardMenu.setMenuName(menuName);
		wizardMenu.setMenuPath(menuPath);
		wizardMenu.setDisplay(display);
		return menuManageDao.insertMenu(wizardMenu);
	}

	@Override
	public int deleteParentMenu(String[] pkIds) {
		ParameterCollection collection = new ParameterCollection(pkIds);
		menuManageDao.deleteChildMenu(collection);
		return menuManageDao.deleteMenu(collection);
	}

	@Override
	public int deleteChildMenu(String[] pkIds) {
		ParameterCollection collection = new ParameterCollection(pkIds);
		return menuManageDao.deleteMenu(collection);
	}

}
