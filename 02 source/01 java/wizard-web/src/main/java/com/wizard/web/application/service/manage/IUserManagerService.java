package com.wizard.web.application.service.manage;

import com.wizard.web.application.vo.manage.ConfirmPasswordVo;
import com.wizard.web.application.vo.manage.UpdateUserVo;
import com.wizard.web.application.vo.manage.UserInfoVo;

public interface IUserManagerService {

	Boolean confirmPassword(String id, ConfirmPasswordVo confirmPasswordVo);

	Boolean updateUser(String id, UpdateUserVo updateUserVo);

	UserInfoVo getUserInfoById(String id);

}
