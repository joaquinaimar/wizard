package com.wizard.protobuf.bean;

import java.util.List;

public class WizardDataBean {

	private Integer id = null;

	private String name = null;

	private List<String> data = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

}
