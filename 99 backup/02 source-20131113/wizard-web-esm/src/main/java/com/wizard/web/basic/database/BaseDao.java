package com.wizard.web.basic.database;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.wizard.web.basic.io.PageRequest;
import com.wizard.web.basic.io.PageResponse;

public class BaseDao extends SqlSessionDaoSupport {

	protected SqlSession getSession() {
		return super.getSqlSession();
	}

	/**
	 * Retrieve a single row mapped from the statement key
	 * 
	 * @param <T>
	 *            the returned object type
	 * @param statement
	 * @return Mapped object
	 */
	protected <T> T selectOne(String statement) {
		return getSession().selectOne(statement);
	}

	/**
	 * Retrieve a single row mapped from the statement key and parameter.
	 * 
	 * @param <T>
	 *            the returned object type
	 * @param statement
	 *            Unique identifier matching the statement to use.
	 * @param parameter
	 *            A parameter object to pass to the statement.
	 * @return Mapped object
	 */
	protected <T> T selectOne(String statement, Object parameter) {
		return getSession().selectOne(statement, parameter);
	}

	/**
	 * Retrieve a list of mapped objects from the statement key and parameter.
	 * 
	 * @param <E>
	 *            the returned list element type
	 * @param statement
	 *            Unique identifier matching the statement to use.
	 * @return List of mapped object
	 */
	protected <E> List<E> selectList(String statement) {
		return getSession().selectList(statement);
	}

	/**
	 * Retrieve a list of mapped objects from the statement key and parameter.
	 * 
	 * @param <E>
	 *            the returned list element type
	 * @param statement
	 *            Unique identifier matching the statement to use.
	 * @param parameter
	 *            A parameter object to pass to the statement.
	 * @return List of mapped object
	 */
	protected <E> List<E> selectList(String statement, Object parameter) {
		return getSession().selectList(statement, parameter);
	}

	/**
	 * Retrieve a list of mapped objects from the statement key and parameter,
	 * within the specified row bounds.
	 * 
	 * @param <E>
	 *            the returned list element type
	 * @param statement
	 *            Unique identifier matching the statement to use.
	 * @param parameter
	 *            A parameter object to pass to the statement.
	 * @param rowBounds
	 *            Bounds to limit object retrieval
	 * @return List of mapped object
	 */
	protected <E> List<E> selectList(String statement, Object parameter,
			RowBounds rowBounds) {
		return getSession().selectList(statement, parameter, rowBounds);
	}

	/**
	 * The selectMap is a special case in that it is designed to convert a list
	 * of results into a Map based on one of the properties in the resulting
	 * objects. Eg. Return a of Map[Integer,Author] for
	 * selectMap("selectAuthors","id")
	 * 
	 * @param <K>
	 *            the returned Map keys type
	 * @param <V>
	 *            the returned Map values type
	 * @param statement
	 *            Unique identifier matching the statement to use.
	 * @param mapKey
	 *            The property to use as key for each value in the list.
	 * @return Map containing key pair data.
	 */
	protected <K, V> Map<K, V> selectMap(String statement, String mapKey) {
		return getSession().selectMap(statement, mapKey);
	}

	/**
	 * The selectMap is a special case in that it is designed to convert a list
	 * of results into a Map based on one of the properties in the resulting
	 * objects.
	 * 
	 * @param <K>
	 *            the returned Map keys type
	 * @param <V>
	 *            the returned Map values type
	 * @param statement
	 *            Unique identifier matching the statement to use.
	 * @param parameter
	 *            A parameter object to pass to the statement.
	 * @param mapKey
	 *            The property to use as key for each value in the list.
	 * @return Map containing key pair data.
	 */
	protected <K, V> Map<K, V> selectMap(String statement, Object parameter,
			String mapKey) {
		return getSession().selectMap(statement, parameter, mapKey);
	}

	/**
	 * The selectMap is a special case in that it is designed to convert a list
	 * of results into a Map based on one of the properties in the resulting
	 * objects.
	 * 
	 * @param <K>
	 *            the returned Map keys type
	 * @param <V>
	 *            the returned Map values type
	 * @param statement
	 *            Unique identifier matching the statement to use.
	 * @param parameter
	 *            A parameter object to pass to the statement.
	 * @param mapKey
	 *            The property to use as key for each value in the list.
	 * @param rowBounds
	 *            Bounds to limit object retrieval
	 * @return Map containing key pair data.
	 */
	protected <K, V> Map<K, V> selectMap(String statement, Object parameter,
			String mapKey, RowBounds rowBounds) {
		return getSession().selectMap(statement, parameter, mapKey, rowBounds);
	}

	/**
	 * Retrieve a single row mapped from the statement key and parameter using a
	 * {@code ResultHandler}.
	 * 
	 * @param statement
	 *            Unique identifier matching the statement to use.
	 * @param parameter
	 *            A parameter object to pass to the statement.
	 * @param handler
	 *            ResultHandler that will handle each retrieved row
	 * @return Mapped object
	 */
	protected void select(String statement, Object parameter,
			ResultHandler handler) {
		getSession().select(statement, parameter, handler);
	}

	/**
	 * Retrieve a single row mapped from the statement using a
	 * {@code ResultHandler}.
	 * 
	 * @param statement
	 *            Unique identifier matching the statement to use.
	 * @param handler
	 *            ResultHandler that will handle each retrieved row
	 * @return Mapped object
	 */
	protected void select(String statement, ResultHandler handler) {
		getSession().select(statement, handler);
	}

	/**
	 * Retrieve a single row mapped from the statement key and parameter using a
	 * {@code ResultHandler} and {@code RowBounds}
	 * 
	 * @param statement
	 *            Unique identifier matching the statement to use.
	 * @param rowBounds
	 *            RowBound instance to limit the query results
	 * @param handler
	 *            ResultHandler that will handle each retrieved row
	 * @return Mapped object
	 */
	protected void select(String statement, Object parameter,
			RowBounds rowBounds, ResultHandler handler) {
		getSession().select(statement, parameter, rowBounds, handler);
	}

	/**
	 * Execute an insert statement.
	 * 
	 * @param statement
	 *            Unique identifier matching the statement to execute.
	 * @return int The number of rows affected by the insert.
	 */
	protected int insert(String statement) {
		return getSession().insert(statement);
	}

	/**
	 * Execute an insert statement with the given parameter object. Any
	 * generated autoincrement values or selectKey entries will modify the given
	 * parameter object properties. Only the number of rows affected will be
	 * returned.
	 * 
	 * @param statement
	 *            Unique identifier matching the statement to execute.
	 * @param parameter
	 *            A parameter object to pass to the statement.
	 * @return int The number of rows affected by the insert.
	 */
	protected int insert(String statement, Object parameter) {
		return getSession().insert(statement, parameter);
	}

	/**
	 * Execute an update statement. The number of rows affected will be
	 * returned.
	 * 
	 * @param statement
	 *            Unique identifier matching the statement to execute.
	 * @return int The number of rows affected by the update.
	 */
	protected int update(String statement) {
		return getSession().update(statement);
	}

	/**
	 * Execute an update statement. The number of rows affected will be
	 * returned.
	 * 
	 * @param statement
	 *            Unique identifier matching the statement to execute.
	 * @param parameter
	 *            A parameter object to pass to the statement.
	 * @return int The number of rows affected by the update.
	 */
	protected int update(String statement, Object parameter) {
		return getSession().update(statement, parameter);
	}

	/**
	 * Execute a delete statement. The number of rows affected will be returned.
	 * 
	 * @param statement
	 *            Unique identifier matching the statement to execute.
	 * @return int The number of rows affected by the delete.
	 */
	protected int delete(String statement) {
		return getSession().delete(statement);
	}

	/**
	 * Execute a delete statement. The number of rows affected will be returned.
	 * 
	 * @param statement
	 *            Unique identifier matching the statement to execute.
	 * @param parameter
	 *            A parameter object to pass to the statement.
	 * @return int The number of rows affected by the delete.
	 */
	protected int delete(String statement, Object parameter) {
		return getSession().delete(statement, parameter);
	}

	protected <E> PageResponse<E> pageList(String statement, PageRequest request) {
		List<E> list = getSession().selectList(statement);
		return new PageResponse<E>(list, request);
	}

	protected <E> PageResponse<E> pageList(String statement, Object parameter,
			PageRequest request) {
		List<E> list = getSession().selectList(statement, parameter);
		return new PageResponse<E>(list, request);
	}

}
