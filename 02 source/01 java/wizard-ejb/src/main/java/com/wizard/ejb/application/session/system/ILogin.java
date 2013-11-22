package com.wizard.ejb.application.session.system;

public interface ILogin {

	public Boolean login(String username, String password);

	public Boolean logout(String username);

}
