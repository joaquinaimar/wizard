package com.wizard.web.application.service.manage;

import com.wizard.web.application.vo.manage.ConfirmVo;

public interface IUserManagerService {

	Boolean confirmPassword(String id, ConfirmVo confirmVo);

}
