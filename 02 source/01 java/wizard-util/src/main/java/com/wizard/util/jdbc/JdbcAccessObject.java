package com.wizard.util.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

public class JdbcAccessObject {

	private Connection connection = null;

	public JdbcAccessObject(final String className) {
		JdbcUtil.loadDriver(className);
	}

	public JdbcAccessObject(final String className, final String url) {
		this(className);
		connection(url);
	}

	public JdbcAccessObject(final String className, final String url,
			final boolean autoCommit) {
		this(className);
		connection(url, autoCommit);
	}

	public JdbcAccessObject(final String className, final String url,
			final String user, final String password) {
		this(className);
		connection(url, user, password);
	}

	public JdbcAccessObject(final String className, final String url,
			final String user, final String password, final boolean autoCommit) {
		this(className);
		connection(url, user, password, autoCommit);
	}

	public boolean connection(final String url) {
		return connection(url, true);
	}

	public boolean connection(final String url, final boolean autoCommit) {
		this.connection = JdbcUtil.getConnection(url);
		try {
			this.connection.setAutoCommit(autoCommit);
		} catch (SQLException e) {
		}
		return (null != this.connection);
	}

	public boolean connection(final String url, final String user,
			final String password) {
		return connection(url, user, password, true);
	}

	public boolean connection(final String url, final String user,
			final String password, final boolean autoCommit) {
		this.connection = JdbcUtil.getConnection(url, user, password);
		try {
			this.connection.setAutoCommit(autoCommit);
		} catch (SQLException e) {
		}
		return (null != this.connection);
	}

	public int execute(final String sql) {
		return JdbcUtil.update(this.connection, sql);
	}

	public int execute(final String sql, final Object[] parameters) {
		return JdbcUtil.update(this.connection, sql, parameters);
	}

	public ResultSet query(final String sql) {
		return JdbcUtil.query(this.connection, sql);
	}

	public ResultSet query(final String sql, final Object[] parameters) {
		return JdbcUtil.query(this.connection, sql, parameters);
	}

	public Collection<Map<String, Object>> select(final String sql) {
		return JdbcUtil.select(this.connection, sql);
	}

	public Collection<Map<String, Object>> select(final String sql,
			final Object[] parameters) {
		return JdbcUtil.select(this.connection, sql, parameters);
	}

	public <X> Collection<X> selectEntity(final String sql, final Class<X> cls) {
		return JdbcUtil.selectEntity(this.connection, sql, cls);
	}

	public <X> Collection<X> selectEntity(final String sql,
			final Object[] parameters, final Class<X> cls) {
		return JdbcUtil.selectEntity(this.connection, sql, parameters, cls);
	}

	public boolean rollback() {
		try {
			this.connection.rollback();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean commit() {
		try {
			this.connection.commit();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean close() {
		try {
			if (!this.connection.getAutoCommit())
				commit();
			this.connection.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}
