package com.wizard.axis2.client;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.wizard.util.common.CommonUtil;

public class Axis2RpcClient {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		RPCServiceClient serviceClient = null;
		try {
			serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference targetEPR = new EndpointReference(
					"http://localhost:8080/wizard-axis2/services/loginWebService/");
			options.setTo(targetEPR);
			Object[] opAddEntryArgs = new Object[] { "admin", "111" };

			Class[] classes = new Class[] { String.class };
			QName opAddEntry = new QName(
					"http://system.soa.application.axis2.wizard.com", "login");
			String result = serviceClient.invokeBlocking(opAddEntry,
					opAddEntryArgs, classes)[0].toString();
			serviceClient.cleanupTransport();
			CommonUtil.consoleOutPrintln(result);
		} catch (AxisFault e) {
			e.printStackTrace();
		}
	}

}
