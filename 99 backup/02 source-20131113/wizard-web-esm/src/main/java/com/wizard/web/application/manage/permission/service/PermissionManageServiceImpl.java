package com.wizard.web.application.manage.permission.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.web.application.manage.permission.bean.Menu;
import com.wizard.web.application.manage.permission.dao.PermissionManageDao;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardAuthority;

@Service
@Transactional
public class PermissionManageServiceImpl implements PermissionManageService {

	@Autowired
	private PermissionManageDao permissionManageDao = null;

	@Override
	public PageResponse<Menu> searchMenu(ExtPageRequest request) {
		return permissionManageDao.searchMenu(request);
	}

	@Override
	public Map<String, List<String>> getAuthority() {
		List<WizardAuthority> authorityList = permissionManageDao
				.getAuthority();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (WizardAuthority authority : authorityList) {
			List<String> temp = map.get(authority.getFkRoleId());
			if (null == temp) {
				temp = new ArrayList<String>();
			}
			temp.add(authority.getFkMenuId());
			map.put(authority.getFkRoleId(), temp);
		}
		return map;
	}

	@Override
	public void doSaveAuthority(Map<String, List<String>> map) {
		permissionManageDao.clearAuthority();
		Set<String> keys = map.keySet();
		WizardAuthority authority = null;
		for (String key : keys) {
			List<String> values = map.get(key);
			for (String value : values) {
				authority = new WizardAuthority();
				authority.setPkId(UUID.randomUUID().toString());
				authority.setFkRoleId(key);
				authority.setFkMenuId(value);
				permissionManageDao.insertAuthority(authority);
			}
		}
	}

}
