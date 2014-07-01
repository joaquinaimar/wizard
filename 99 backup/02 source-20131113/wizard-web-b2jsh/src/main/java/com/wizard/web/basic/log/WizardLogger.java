package com.wizard.web.basic.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wizard.web.utils.WizardUtils;

public class WizardLogger {

	private static Logger log = null;

	public Object aroundLog(ProceedingJoinPoint point) throws Throwable {

		Class<?> cls = point.getTarget().getClass();
		log = LoggerFactory.getLogger(cls);

		MethodSignature joinPointMethod = (MethodSignature) point
				.getSignature();
		String MethodName = joinPointMethod.getName();

		String paraLog = getParaLog(point.getArgs());

		log.info("[" + cls.getName() + "] [" + MethodName + "] Start");
		log.info(paraLog);
		Object object = point.proceed();
		log.info("[" + cls.getName() + "] [" + MethodName + "] End");

		return object;

	}

	private String getParaLog(Object[] args) {
		int i = 0;
		StringBuffer paraLog = new StringBuffer("parameters of this method:");
		for (Object arg : args) {
			paraLog.append("\n").append(" [").append(++i).append("]: [");
			if (null != arg) {
				paraLog.append(arg.getClass().getName());
			} else {
				paraLog.append("null");
			}
			paraLog.append(":").append(WizardUtils.toJson(arg)).append("]");
		}
		return paraLog.toString();
	}

}
