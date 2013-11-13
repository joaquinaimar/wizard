package com.wizard.web.application.system.login.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.domain.entity.WizardUserInfo;

@Repository
public class LoginDao extends BaseDao {

	public WizardUserInfo login(WizardUserInfo userInfo) {
		Criteria criteria = super.createCriteria(WizardUserInfo.class);
		criteria.add(Restrictions.eq("userName", userInfo.getUserName()));
		criteria.add(Restrictions.eq("password", userInfo.getPassword()));
		return (WizardUserInfo) criteria.uniqueResult();
	}

}
