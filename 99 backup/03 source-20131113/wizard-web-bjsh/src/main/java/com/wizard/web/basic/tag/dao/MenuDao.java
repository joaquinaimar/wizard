package com.wizard.web.basic.tag.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.domain.entity.WizardMenu;

@Repository
@SuppressWarnings("unchecked")
public class MenuDao extends BaseDao {

	public List<WizardMenu> getParentNodes(String roleName) {
		String hql = "FROM WizardMenu m WHERE (m.pkId IN ( ";
		hql += "SELECT DISTINCT menu.pPkId FROM WizardMenu menu ";
		hql += "INNER JOIN menu.wizardAuthority authority ";
		hql += "INNER JOIN authority.wizardRoleInfo role ";
		hql += "WHERE menu.pPkId  <>  '0' AND role.roleName = :roleName AND menu.display = '1' ";
		hql += ") OR pkId = '00' ) AND m.display = '1' ORDER BY m.pkId ";
		Query query = super.createQuery(hql);
		query.setString("roleName", roleName);
		return query.list();
	}

	public List<WizardMenu> getChildNodes(String pid, String roleName) {
		String hql = "SELECT menu FROM WizardMenu menu ";
		hql += "INNER JOIN menu.wizardAuthority authority ";
		hql += "INNER JOIN authority.wizardRoleInfo role ";
		hql += "WHERE menu.pPkId = :pid AND role.roleName = :roleName AND menu.display = '1' ";
		hql += "ORDER BY menu.pkId ";
		Query query = super.createQuery(hql);
		query.setString("pid", pid);
		query.setString("roleName", roleName);
		return query.list();
	}
}
