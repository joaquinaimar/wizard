package com.wizard.web.application.vo.system;

public class MenuVo {

	private String id = null;

	private String name = null;

	private String url = null;

	private MenuVo[] child = null;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MenuVo[] getChild() {
		return child;
	}

	public void setChild(MenuVo[] child) {
		this.child = child;
	}

}
