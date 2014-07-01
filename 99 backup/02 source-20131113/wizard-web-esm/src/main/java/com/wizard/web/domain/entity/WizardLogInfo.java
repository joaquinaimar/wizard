package com.wizard.web.domain.entity;

import java.util.Date;

import com.wizard.web.basic.database.EntityTemplate;

public class WizardLogInfo extends EntityTemplate  {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3946383525704052440L;

	private String pkId = null;

	private String logLevel = null;

	private String logTitle = null;

	private String logDetail = null;

	private Date logTime = null;

	private String userName = null;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogTitle() {
		return logTitle;
	}

	public void setLogTitle(String logTitle) {
		this.logTitle = logTitle;
	}

	public String getLogDetail() {
		return logDetail;
	}

	public void setLogDetail(String logDetail) {
		this.logDetail = logDetail;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
