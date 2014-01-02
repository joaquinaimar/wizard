package com.wizard.web.application.service.manage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.util.common.SecurityUtil;
import com.wizard.web.application.dao.manage.UserManagerDao;
import com.wizard.web.application.service.manage.IUserManagerService;
import com.wizard.web.application.vo.manage.ConfirmVo;
import com.wizard.web.domain.entity.wizard.WizardUserInfo;

@Service
@Transactional
public class UserManagerServiceImpl implements IUserManagerService {

	@Autowired
	private UserManagerDao userManagerDao = null;

	@Override
	public Boolean confirmPassword(String id, ConfirmVo confirmVo) {
		WizardUserInfo userInfo = this.userManagerDao.get(WizardUserInfo.class,
				id);
		return (null != userInfo)
				&& userInfo.getPassword().equals(
						SecurityUtil.encodeMd5(confirmVo.getPassword()));
	}

}
