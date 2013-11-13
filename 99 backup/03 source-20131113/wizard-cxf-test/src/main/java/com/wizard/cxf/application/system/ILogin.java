package com.wizard.cxf.application.system;

import javax.jws.WebService;

@WebService
public interface ILogin {

	public Boolean login(String username, String password);

	public Boolean logout(String username);

}
