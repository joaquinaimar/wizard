package com.wizard.axis2.client;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import com.wizard.axis2.application.soa.system.LoginWebServiceStub;
import com.wizard.axis2.application.soa.system.LoginWebServiceStub.Login;
import com.wizard.axis2.application.soa.system.LoginWebServiceStub.LoginResponse;
import com.wizard.util.common.CommonUtil;

public class Axis2StubClient {

	public static void main(String[] args) {
		try {
			LoginWebServiceStub stub = new LoginWebServiceStub();
			Login login = new Login();
			login.setUsername("admin");
			login.setPassword("111");
			LoginResponse response = stub.login(login);
			CommonUtil.consoleOutPrintln(response.get_return());
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
}
