package com.wizard.web.basic.io;

public class ParameterCollection {

	private Object[] records = null;

	public ParameterCollection(Object[] records) {
		setRecords(records);
	}

	public Object[] getRecords() {
		return records;
	}

	public void setRecords(Object[] records) {
		this.records = records;
	}

}
