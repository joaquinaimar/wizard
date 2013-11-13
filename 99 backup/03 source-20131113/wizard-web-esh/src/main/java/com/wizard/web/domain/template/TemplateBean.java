package com.wizard.web.domain.template;

import java.util.List;

public class TemplateBean {

	private String id = null;

	private String name = null;

	private List<ColumnBean> columns = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ColumnBean> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnBean> columns) {
		this.columns = columns;
	}

}
