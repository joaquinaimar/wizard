package com.wizard.sql.base;

public abstract class SessionFactory {

	private DataSource dataSource = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
