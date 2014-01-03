package com.wizard.web.application.service.manage.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.util.common.BeanUtil;
import com.wizard.util.common.CommonUtil;
import com.wizard.util.common.SecurityUtil;
import com.wizard.web.application.dao.manage.UserManagerDao;
import com.wizard.web.application.service.manage.IUserManagerService;
import com.wizard.web.application.vo.manage.ConfirmPasswordVo;
import com.wizard.web.application.vo.manage.UpdateUserVo;
import com.wizard.web.application.vo.manage.UserInfoVo;
import com.wizard.web.domain.entity.wizard.WizardUserInfo;

@Service
@Transactional
public class UserManagerServiceImpl implements IUserManagerService {

	@Autowired
	private UserManagerDao userManagerDao = null;

	@Override
	public Boolean confirmPassword(String id,
			ConfirmPasswordVo confirmPasswordVo) {
		WizardUserInfo userInfo = this.userManagerDao.get(WizardUserInfo.class,
				id);
		return (null != userInfo)
				&& userInfo.getPassword()
						.equals(SecurityUtil.encodeMd5(confirmPasswordVo
								.getPassword()));
	}

	@Override
	public Boolean updateUser(String id, UpdateUserVo updateUserVo) {
		Map<String, Object> params = BeanUtil.beanToMap(updateUserVo);
		params.put("id", id);
		if (!CommonUtil.isNull(params.get("newPassword")))
			params.put("newPassword",
					SecurityUtil.encodeMd5((String) params.get("newPassword")));
		if (!CommonUtil.isNull(params.get("oldPassword")))
			params.put("oldPassword",
					SecurityUtil.encodeMd5((String) params.get("oldPassword")));

		return 1 == this.userManagerDao.updateUser(params);
	}

	@Override
	public UserInfoVo getUserInfoById(String id) {
		WizardUserInfo userInfo = userManagerDao.get(WizardUserInfo.class, id);
		UserInfoVo vo = new UserInfoVo();
		BeanUtil.copy(userInfo, vo);
		return vo;
	}

}
