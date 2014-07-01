package com.wizard.web.basic.tag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.web.basic.tag.dao.MenuDao;
import com.wizard.web.domain.entity.WizardMenu;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao = null;

	@Override
	public List<WizardMenu> getParentNodes(String roleName) {
		return menuDao.getParentNodes(roleName);
	}

	@Override
	public List<WizardMenu> getChildNodes(String pid, String roleName) {
		return menuDao.getChildNodes(pid, roleName);
	}

}
