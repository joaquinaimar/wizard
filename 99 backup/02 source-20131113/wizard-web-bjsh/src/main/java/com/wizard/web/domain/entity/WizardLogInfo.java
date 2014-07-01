package com.wizard.web.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.wizard.web.basic.database.EntityTemplate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "wizard_log_info")
public class WizardLogInfo extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3946383525704052440L;

	@Id
	@Column(name = "PK_ID")
	private String pkId = null;

	@Column(name = "LOG_LEVEL")
	private String logLevel = null;

	@Column(name = "LOG_TITLE")
	private String logTitle = null;

	@Column(name = "LOG_DETAIL")
	private String logDetail = null;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOG_TIME")
	private Date logTime = null;

	@Column(name = "USER_NAME")
	private String userName = null;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "LOG_LEVEL", insertable = false, updatable = false)
	private WizardCode wizardCodeLogLevel = null;

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

	public WizardCode getWizardCodeLogLevel() {
		return wizardCodeLogLevel;
	}

	public void setWizardCodeLogLevel(WizardCode wizardCodeLogLevel) {
		this.wizardCodeLogLevel = wizardCodeLogLevel;
	}

}
