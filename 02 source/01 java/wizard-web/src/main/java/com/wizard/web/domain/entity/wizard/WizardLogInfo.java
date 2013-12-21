package com.wizard.web.domain.entity.wizard;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.wizard.j2ee.dao.EntityTemplate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "t_wizard_log_info")
public class WizardLogInfo extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3946383525704052440L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
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

	@Column(name = "USERNAME")
	private String username = null;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public WizardCode getWizardCodeLogLevel() {
		return wizardCodeLogLevel;
	}

	public void setWizardCodeLogLevel(WizardCode wizardCodeLogLevel) {
		this.wizardCodeLogLevel = wizardCodeLogLevel;
	}

}
