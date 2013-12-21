package com.wizard.web.application.dao.system;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wizard.j2ee.dao.hibernate.HibernateBaseDao;
import com.wizard.web.application.vo.system.LoginVo;
import com.wizard.web.domain.entity.wizard.WizardUserInfo;

@Repository
public class LoginDao extends HibernateBaseDao {

	public WizardUserInfo getLoginInfo(LoginVo loginVo) {
		Criteria criteria = super.createCriteria(WizardUserInfo.class);
		criteria.add(Restrictions.eq("username", loginVo.getUsername()));
		criteria.add(Restrictions.eq("password", loginVo.getPassword()));
		return (WizardUserInfo) criteria.uniqueResult();
	}
}
