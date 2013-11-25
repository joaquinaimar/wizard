package com.wizard.util.jdbc;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Properties;

import com.wizard.util.common.BeanUtil;
import com.wizard.util.common.CommonUtil;

public final class JdbcUtil {

	private JdbcUtil() {
		throw new UnsupportedOperationException("Not supported");
	}

	public static void loadDriver(String className) {
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Class Not Found");
		}
	}

	public static void loadDriver(Class<?> cls) {
		loadDriver(cls.getName());
	}

	public static Connection getConnection(final String url) {
		try {
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			return null;
		}
	}

	public static Connection getConnection(final String url, final String user,
			final String password) {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			return null;
		}
	}

	public static Connection getConnection(final String url,
			final Properties info) {
		try {
			return DriverManager.getConnection(url, info);
		} catch (SQLException e) {
			return null;
		}
	}

	public static Statement createStatement(final Connection con) {
		try {
			return con.createStatement();
		} catch (SQLException e) {
			return null;
		}
	}

	public static PreparedStatement prepareStatement(final Connection con,
			final String sql) {
		try {
			return con.prepareStatement(sql);
		} catch (SQLException e) {
			return null;
		}
	}

	public static ResultSet query(final Statement statement, final String sql) {
		try {
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			return null;
		}
	}

	public static int update(final Statement statement, final String sql) {
		try {
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			return -1 * e.getErrorCode();
		}
	}

	public static PreparedStatement setParameters(
			final PreparedStatement statement, final Object[] parameters) {
		int i = 0;
		for (Object parameter : parameters) {
			try {
				if (BeanUtil.isArray(parameter))
					statement.setArray(
							i++,
							statement.getConnection().createArrayOf(
									BeanUtil.getName(parameter),
									(Object[]) parameter));
				else if (BeanUtil.isCollection(parameter))
					statement
							.setArray(
									i++,
									statement
											.getConnection()
											.createArrayOf(
													BeanUtil.getName(parameter),
													CommonUtil
															.changeListToObjects((Collection<?>) parameter)));
				else if (BeanUtil.isInstanceof(parameter, java.util.Date.class))
					statement.setDate(i++, (java.sql.Date) parameter);
				else if (BeanUtil.isInstanceof(parameter, Blob.class))
					statement.setBlob(i++, (Blob) parameter);
				else
					statement.setString(i++, String.valueOf(parameter));
			} catch (SQLException e) {
				continue;
			}
		}
		return statement;
	}

	public static ResultSet query(final PreparedStatement statement,
			final Object[] parameters) {
		try {
			setParameters(statement, parameters);
			return statement.executeQuery();
		} catch (SQLException e) {
			return null;
		}
	}

	public static int update(final PreparedStatement statement,
			final Object[] parameters) {
		try {
			setParameters(statement, parameters);
			return statement.executeUpdate();
		} catch (SQLException e) {
			return -1 * e.getErrorCode();
		}
	}

}
