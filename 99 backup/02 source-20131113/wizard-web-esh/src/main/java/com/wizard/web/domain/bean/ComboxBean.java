package com.wizard.web.domain.bean;

public class ComboxBean {

	private String valueField = null;

	private String displayField = null;

	public ComboxBean(String valueField) {
		this(valueField, valueField);
	}

	public ComboxBean(String valueField, String displayField) {
		setValueField(valueField);
		setDisplayField(displayField);
	}

	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public String getDisplayField() {
		return displayField;
	}

	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}

}
