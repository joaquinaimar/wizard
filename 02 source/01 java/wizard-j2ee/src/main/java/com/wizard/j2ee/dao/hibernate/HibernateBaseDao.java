package com.wizard.j2ee.dao.hibernate;

import java.io.Serializable;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wizard.j2ee.dao.EntityTemplate;
import com.wizard.j2ee.dao.PageRequest;
import com.wizard.j2ee.dao.PageResponse;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class HibernateBaseDao {

	@Autowired
	private SessionFactory sessionFactory = null;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Integer getMaxInt(String entityName, String column) {
		Query query = createQuery("SELECT MAX(CAST(entity." + column
				+ " AS int)) AS CNT FROM " + entityName + "  entity");
		Integer cnt = (Integer) query.uniqueResult();
		return (null == cnt) ? 0 : cnt;
	}

	public int clear(String entityName) {
		Query query = createQuery("DELETE FROM " + entityName);
		return query.executeUpdate();
	}

	public <X> X get(Class<X> clazz, Serializable id) {
		return (X) getSession().get(clazz, id);
	}

	public void save(EntityTemplate entity) {
		getSession().save(entity);
	}

	public void delete(EntityTemplate entity) {
		getSession().delete(entity);
	}

	public void update(EntityTemplate entity) {
		getSession().update(entity);
	}

	public int updateByHql(String queryString) {
		Query query = createQuery(queryString);
		return query.executeUpdate();
	}

	public int updateByHql(String queryString, Object obj) {
		Query query = createQuery(queryString);
		query.setProperties(obj);
		return query.executeUpdate();
	}

	public int updateByHql(String queryString, Map<String, Object> map) {
		Query query = createQuery(queryString);
		query.setProperties(map);
		return query.executeUpdate();
	}

	public Query createQuery(String queryString) {
		return getSession().createQuery(queryString);
	}

	public SQLQuery createSQLQuery(String queryString) {
		return getSession().createSQLQuery(queryString);
	}

	public Criteria createCriteria(String entityName) {
		return getSession().createCriteria(entityName);
	}

	public Criteria createCriteria(String entityName, String alias) {
		return getSession().createCriteria(entityName, alias);
	}

	public Criteria createCriteria(Class<?> persistentClass) {
		return getSession().createCriteria(persistentClass);
	}

	public Criteria createCriteria(Class<?> persistentClass, String alias) {
		return getSession().createCriteria(persistentClass, alias);
	}

	public PageResponse pageQuery(Criteria criteria, PageRequest request) {
		criteria.setFirstResult(request.getStart());
		criteria.setMaxResults(request.getLimit());
		return new PageResponse(criteria.list(), request, getSize(criteria));
	}

	public PageResponse pageQueryOrder(Criteria criteria, PageRequest request) {
		SetOrderBy(criteria, request);
		return pageQuery(criteria, request);
	}

	private void SetOrderBy(Criteria criteria, PageRequest request) {
		if (null != request.getSortCols() && null != request.getSortTypes()) {
			for (int i = 0; i < request.getSortCols().length; i++) {
				String type = request.getSortTypes()[i].toLowerCase().trim();
				if ("".equals(type) || "acs".equals(type))
					criteria.addOrder(Order.asc(request.getSortCols()[i]));
				else if ("desc".equals(type))
					criteria.addOrder(Order.desc(request.getSortCols()[i]));
			}
		}
	}

	private long getSize(Criteria count) {
		count.setFirstResult(0);
		count.setMaxResults(0);
		count.setProjection(Projections.rowCount());
		return (long) count.uniqueResult();
	}

	public PageResponse pageQuery(Query query, PageRequest request) {
		return pageQuery(query, null, request);
	}

	public PageResponse pageQueryOrder(Query query, PageRequest request) {
		setOrderBy(query, request);
		return pageQuery(query, request);
	}

	public PageResponse pageQuery(Query query, Object obj, PageRequest request) {
		query.setProperties(obj);
		query.setFirstResult(request.getStart());
		query.setMaxResults(request.getLimit());
		return new PageResponse(query.list(), request, getSize(query, obj));
	}

	public PageResponse pageQueryOrder(Query query, Object obj,
			PageRequest request) {
		setOrderBy(query, request);
		return pageQuery(query, obj, request);
	}

	private long getSize(Query query, Object obj) {
		String queryString = query.getQueryString();
		int fromIndex = queryString.toLowerCase().indexOf("from");
		String countString = "SELECT COUNT(*) AS CNT "
				+ queryString.substring(fromIndex);
		Query countQuery = createQuery(countString);
		countQuery.setProperties(obj);
		return (long) countQuery.uniqueResult();
	}

	public PageResponse pageQuery(Query query, Map<String, Object> bean,
			PageRequest request) {
		query.setProperties(bean);
		query.setFirstResult(request.getStart());
		query.setMaxResults(request.getLimit());
		return new PageResponse(query.list(), request, getSize(query, bean));
	}

	public PageResponse pageQueryOrder(Query query, Map<String, Object> bean,
			PageRequest request) {
		setOrderBy(query, request);
		return pageQuery(query, bean, request);
	}

	private long getSize(Query query, Map<String, Object> bean) {
		String queryString = query.getQueryString();
		int fromIndex = queryString.toLowerCase().indexOf("from");
		String countString = "SELECT COUNT(*) AS CNT "
				+ queryString.substring(fromIndex);
		Query countQuery = createQuery(countString);
		countQuery.setProperties(bean);
		return (long) countQuery.uniqueResult();
	}

	private void setOrderBy(Query query, PageRequest request) {
		if (null != request.getSortCols() && null != request.getSortTypes()) {
			String queryString = query.getQueryString().toLowerCase();
			if (queryString.indexOf("order") < 0)
				queryString += " order by ";
			for (int i = 0; i < request.getSortCols().length; i++) {
				queryString += request.getSortCols()[i] + " "
						+ request.getSortTypes()[i] + ", ";
			}
			queryString.substring(0, queryString.length() - 2);
			query = createQuery(queryString);

		}
	}

	public String createInStr(String propertie, Object[] objs) {
		if (null != objs && 0 != objs.length) {
			StringBuffer sb = new StringBuffer(propertie);
			sb.append(" IN ( ");
			for (Object obj : objs) {
				sb.append(", ");
				sb.append(obj);
			}
			sb.append(" )");
			return sb.toString().replaceFirst(", ", "");
		} else {
			return "1 = 1";
		}
	}

}
