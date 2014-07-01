package com.wizard.web.basic.security.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.domain.entity.WizardOrgInfo;
import com.wizard.web.domain.entity.WizardRoleInfo;
import com.wizard.web.domain.entity.WizardUserInfo;

@Repository
@SuppressWarnings("unchecked")
public class SecurityDao extends BaseDao {

	public WizardUserInfo authenticationQuery(WizardUserInfo userInfo) {
		Criteria criteria = super.createCriteria(WizardUserInfo.class);
		criteria.add(Restrictions.eq("userName", userInfo.getUserName()));
		criteria.add(Restrictions.eq("password", userInfo.getPassword()));
		return (WizardUserInfo) criteria.uniqueResult();
	}

	public List<WizardRoleInfo> userRolesQuery(String userName) {
		Criteria criteria = super.createCriteria(WizardRoleInfo.class, "role");
		Criteria addcriteria = criteria.createCriteria("wizardUserInfo",
				"user", JoinType.INNER_JOIN);
		addcriteria.add(Restrictions.eq("user.userName", userName));
		return addcriteria.list();
	}

	public List<WizardOrgInfo> permissionsQuery(String roleName) {
		Criteria criteria = super.createCriteria(WizardOrgInfo.class, "org");
		Criteria addcriteria = criteria.createCriteria("wizardRoleInfo",
				"role", JoinType.INNER_JOIN);
		addcriteria.add(Restrictions.eq("role.roleName", roleName));
		return addcriteria.list();
	}

}
