package com.wizard.util.jdbc;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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

	public static ResultSet query(final Connection con, final String sql) {
		return query(createStatement(con), sql);
	}

	public static int update(final Statement statement, final String sql) {
		try {
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			return -1 * e.getErrorCode();
		}
	}

	public static int update(final Connection con, final String sql) {
		return update(createStatement(con), sql);
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

	public static ResultSet query(final Connection con, final String sql,
			final Object[] parameters) {
		PreparedStatement statement = prepareStatement(con, sql);
		return query(statement, parameters);
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

	public static int update(final Connection con, final String sql,
			final Object[] parameters) {
		PreparedStatement statement = prepareStatement(con, sql);
		return update(statement, parameters);
	}

	public static Collection<Map<String, Object>> select(
			final Statement statement, final String sql) {
		ResultSet rs = query(statement, sql);
		if (null == rs)
			return null;
		return convertResultSetToMap(rs);
	}

	public static Collection<Map<String, Object>> select(final Connection con,
			final String sql) {
		return select(createStatement(con), sql);
	}

	public static Collection<Map<String, Object>> select(
			final PreparedStatement statement, final Object[] parameters) {
		ResultSet rs = query(statement, parameters);
		if (null == rs)
			return null;
		return convertResultSetToMap(rs);
	}

	public static Collection<Map<String, Object>> select(final Connection con,
			final String sql, final Object[] parameters) {
		PreparedStatement statement = prepareStatement(con, sql);
		return select(statement, parameters);
	}

	private static Collection<Map<String, Object>> convertResultSetToMap(
			final ResultSet rs) {
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int colum = metaData.getColumnCount();
			Collection<Map<String, Object>> collection = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;
			while (rs.next()) {
				row = new HashMap<String, Object>();
				for (int i = 0; i < colum; i++)
					row.put(metaData.getColumnName(i), rs.getObject(i));
				collection.add(row);
			}
			return collection;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
	}

	public static <X> Collection<X> selectEntity(final Statement statement,
			final String sql, final Class<X> cls) {
		ResultSet rs = query(statement, sql);
		if (null == rs)
			return null;
		return convertResultSetToEntity(rs, cls);
	}

	public static <X> Collection<X> selectEntity(final Connection con,
			final String sql, final Class<X> cls) {
		return selectEntity(createStatement(con), sql, cls);
	}

	public static <X> Collection<X> selectEntity(
			final PreparedStatement statement, final Object[] parameters,
			final Class<X> cls) {
		ResultSet rs = query(statement, parameters);
		if (null == rs)
			return null;
		return convertResultSetToEntity(rs, cls);
	}

	public static <X> Collection<X> selectEntity(final Connection con,
			final String sql, final Object[] parameters, final Class<X> cls) {
		PreparedStatement statement = prepareStatement(con, sql);
		return selectEntity(statement, parameters, cls);
	}

	private static <X> Collection<X> convertResultSetToEntity(
			final ResultSet rs, final Class<X> cls) {
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int colum = metaData.getColumnCount();
			Collection<X> collection = new ArrayList<X>();
			X row = null;
			while (rs.next()) {
				row = cls.newInstance();
				for (int i = 0; i < colum; i++)
					BeanUtil.setFieldValue(row, metaData.getColumnName(i),
							rs.getObject(i));
				collection.add(row);
			}
			return collection;
		} catch (SQLException e) {
			return null;
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
	}

	public static Object uniqueResult(final Statement statement,
			final String sql) {
		ResultSet rs = query(statement, sql);
		if (null == rs)
			return null;
		return uniqueResult(rs);
	}

	public static Object uniqueResult(final Connection con, final String sql) {
		return uniqueResult(createStatement(con), sql);
	}

	public static Object uniqueResult(final PreparedStatement statement,
			final Object[] parameters) {
		ResultSet rs = query(statement, parameters);
		if (null == rs)
			return null;
		return uniqueResult(rs);
	}

	public static Object uniqueResult(final Connection con, final String sql,
			final Object[] parameters) {
		PreparedStatement statement = prepareStatement(con, sql);
		return uniqueResult(statement, parameters);
	}

	private static Object uniqueResult(ResultSet rs) {
		try {
			while (rs.next())
				return rs.getObject(0);
			return null;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
	}

}
