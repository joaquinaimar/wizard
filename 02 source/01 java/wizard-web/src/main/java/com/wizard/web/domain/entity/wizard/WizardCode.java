package com.wizard.web.domain.entity.wizard;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.wizard.j2ee.dao.EntityTemplate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "t_wizard_code")
public class WizardCode extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5385713154001386403L;

	@Id
	@Column(name = "PK_ID")
	private String pkId = null;

	@Column(name = "TYPE_ID")
	private String typeId = null;

	@Column(name = "CONTENT")
	private String content = null;

	@OneToMany
	@JsonIgnore
	@JoinColumn(name = "TYPE_ID", insertable = false, updatable = false)
	private Set<WizardCode> wizardCodeTypeCode = null;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<WizardCode> getWizardCodeTypeCode() {
		return wizardCodeTypeCode;
	}

	public void setWizardCodeTypeCode(Set<WizardCode> wizardCodeTypeCode) {
		this.wizardCodeTypeCode = wizardCodeTypeCode;
	}

}
