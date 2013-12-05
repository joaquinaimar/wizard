package com.wizard.j2ee.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wizard.util.common.BeanUtil;
import com.wizard.util.common.CommonUtil;
import com.wizard.util.file.json.JsonUtil;

public class ExecuteLogger {

	private Logger log = null;

	public Object aroundLog(ProceedingJoinPoint point) throws Throwable {

		Class<?> cls = BeanUtil.getClass(point.getTarget());
		log = LoggerFactory.getLogger(cls);

		MethodSignature joinPointMethod = (MethodSignature) point
				.getSignature();
		String methodName = joinPointMethod.getName();
		String paraLog = getParaLog(point.getArgs());
		log.info("[" + BeanUtil.getName(cls) + "] [" + methodName + "] Start");
		log.debug(paraLog);
		Object object = point.proceed();
		log.info("[" + BeanUtil.getName(cls) + "] [" + methodName + "] End");

		return object;

	}

	private String getParaLog(Object[] args) {
		int i = 0;
		StringBuffer paraLog = new StringBuffer("parameters of this method:");
		for (Object arg : args) {
			paraLog.append("\n").append(" [").append(++i).append("]: [");
			if (!CommonUtil.isNull(arg))
				paraLog.append(BeanUtil.getName(arg));
			else
				paraLog.append("null");
			paraLog.append(":").append(JsonUtil.convertToJson(arg)).append("]");
		}
		return paraLog.toString();
	}

}
