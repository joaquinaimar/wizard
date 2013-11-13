package com.wizard.web.application.system.login.service;

import java.util.Map;

public interface LoginService {

	public Map<String, Object> login(String userName, String password);

}
