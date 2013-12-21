package com.wizard.web.application.service.system;

import com.wizard.web.application.vo.system.LoginVo;
import com.wizard.web.domain.bean.LoginInfo;

public interface ILoginService {

	boolean login(LoginVo loginVo);

	LoginInfo getLoginInfo(LoginVo loginVo);

}
