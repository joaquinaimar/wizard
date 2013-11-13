package com.wizard.web.application.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.domain.entity.WizardCode;

@Repository
@SuppressWarnings("unchecked")
public class WizardCommonDao extends BaseDao {

	public List<WizardCode> getCodeList(String typeId) {
		Query query = super
				.createQuery("FROM WizardCode WHERE typeId = :typeId ORDER BY pkId");
		query.setParameter("typeId", typeId);
		return query.list();
	}
}
