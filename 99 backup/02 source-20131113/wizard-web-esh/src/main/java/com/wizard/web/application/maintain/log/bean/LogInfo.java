package com.wizard.web.application.maintain.log.bean;

import java.util.Date;

import com.wizard.web.domain.entity.WizardLogInfo;

public class LogInfo extends WizardLogInfo {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7821488706584842471L;

	private String logLevelValue = null;

	private Date logTimeStart = null;

	private Date logTimeEnd = null;

	public String getLogLevelValue() {
		return logLevelValue;
	}

	public void setLogLevelValue(String logLevelValue) {
		this.logLevelValue = logLevelValue;
	}

	public Date getLogTimeStart() {
		return logTimeStart;
	}

	public void setLogTimeStart(Date logTimeStart) {
		this.logTimeStart = logTimeStart;
	}

	public Date getLogTimeEnd() {
		return logTimeEnd;
	}

	public void setLogTimeEnd(Date logTimeEnd) {
		this.logTimeEnd = logTimeEnd;
	}

}
