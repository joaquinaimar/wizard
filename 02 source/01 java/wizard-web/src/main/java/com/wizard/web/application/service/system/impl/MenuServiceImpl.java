package com.wizard.web.application.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.util.common.CommonUtil;
import com.wizard.web.application.dao.system.MenuDao;
import com.wizard.web.application.po.system.MenuPo;
import com.wizard.web.application.service.system.IMenuService;
import com.wizard.web.application.vo.system.MenuVo;
import com.wizard.web.domain.entity.wizard.WizardMenu;

@Service
@Transactional
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private MenuDao menuDao = null;

	@Override
	public MenuVo getHomePage() {
		WizardMenu menu = this.menuDao.getHomePage();
		MenuVo menuVo = new MenuVo();
		menuVo.setId(menu.getPkId());
		menuVo.setName(menu.getMenuName());
		menuVo.setUrl(menu.getMenuPath());
		return menuVo;
	}

	@Override
	public MenuVo[] getMenu(String id) {
		List<MenuPo> menus = this.menuDao.getMenu(id);
		List<MenuVo> pList = new ArrayList<MenuVo>();
		List<MenuVo> cList = null;

		String pid = null;
		MenuVo p = null;
		MenuVo c = null;

		for (MenuPo m : menus) {
			if (null == pid || !pid.equals(m.getPid())) {
				if (null != pid) {
					p.setChild(CommonUtil.changeListToArray(cList));
					pList.add(p);
				}
				p = new MenuVo();
				p.setId(m.getPid());
				p.setName(m.getPname());
				p.setUrl(m.getPurl());
				pid = m.getPid();
				cList = new ArrayList<MenuVo>();
			}
			if (null != m.getCid()) {
				c = new MenuVo();
				c.setId(m.getCid());
				c.setName(m.getCname());
				c.setUrl(m.getCurl());
				cList.add(c);
			}
		}

		if (null != pid) {
			p.setChild(CommonUtil.changeListToArray(cList));
			pList.add(p);
		}

		return CommonUtil.changeListToArray(pList);
	}
}
