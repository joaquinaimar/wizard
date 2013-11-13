package com.wizard.web.domain.entity;

import com.wizard.web.basic.database.EntityTemplate;

public class WizardCode extends EntityTemplate {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4040634805429932727L;

	private String pkId = null;

	private String typeId = null;

	private String content = null;

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

}
