package com.wizard.axis2.application.soa.system;

public interface ILogin {

	public Boolean login(String username, String password);

	public Boolean logout(String username);

}
