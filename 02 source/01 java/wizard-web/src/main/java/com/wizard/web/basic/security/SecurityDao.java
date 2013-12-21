package com.wizard.web.basic.security;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.wizard.j2ee.dao.hibernate.HibernateBaseDao;
import com.wizard.web.domain.entity.wizard.WizardMenu;
import com.wizard.web.domain.entity.wizard.WizardRoleInfo;
import com.wizard.web.domain.entity.wizard.WizardUserInfo;

@Repository
public class SecurityDao extends HibernateBaseDao {

	public WizardUserInfo getLoginUserInfo(String user, String password) {
		Criteria criteria = super.createCriteria(WizardUserInfo.class);
		criteria.add(Restrictions.eq("username", user));
		criteria.add(Restrictions.eq("password", password));
		return (WizardUserInfo) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<WizardRoleInfo> getRoles(String user) {
		Criteria criteria = super
				.createCriteria(WizardRoleInfo.class, "roleInfo")
				.createCriteria("roleInfo.users", "userRole",
						JoinType.INNER_JOIN)
				.createCriteria("userRole.user", "userInfo",
						JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("userInfo.username", user));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<WizardMenu> getPermissions(String roleName) {
		Criteria criteria = super
				.createCriteria(WizardMenu.class, "menu")
				.createCriteria("menu.authoritys", "authority",
						JoinType.INNER_JOIN)
				.createCriteria("authority.role", "roleInfo",
						JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("roleInfo.pkId", roleName));
		return criteria.list();
	}

}
