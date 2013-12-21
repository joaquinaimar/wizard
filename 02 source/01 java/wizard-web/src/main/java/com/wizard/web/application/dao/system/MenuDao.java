package com.wizard.web.application.dao.system;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.wizard.j2ee.dao.hibernate.HibernateBaseDao;
import com.wizard.web.application.po.system.MenuPo;
import com.wizard.web.domain.entity.wizard.WizardAuthority;
import com.wizard.web.domain.entity.wizard.WizardMenu;
import com.wizard.web.domain.entity.wizard.WizardUserRole;

@Repository
public class MenuDao extends HibernateBaseDao {

	public WizardMenu getHomePage() {
		return super.get(WizardMenu.class, "00");
	}

	@SuppressWarnings("unchecked")
	public List<MenuPo> getMenu(String id) {

		DetachedCriteria role = DetachedCriteria.forClass(WizardUserRole.class);
		role.setProjection(Property.forName("roleId"));
		role.add(Restrictions.eq("userId", id));

		DetachedCriteria authority = DetachedCriteria
				.forClass(WizardAuthority.class);
		authority.setProjection(Property.forName("menuId"));
		authority.add(Subqueries.propertyIn("roleId", role));

		Criteria criteria = super.createCriteria(WizardMenu.class, "parents")
				.createCriteria("parents.child", "child",
						JoinType.LEFT_OUTER_JOIN);

		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("parents.pkId").as("pid"));
		pList.add(Projections.property("parents.menuName").as("pname"));
		pList.add(Projections.property("parents.menuPath").as("purl"));
		pList.add(Projections.property("child.pkId").as("cid"));
		pList.add(Projections.property("child.menuName").as("cname"));
		pList.add(Projections.property("child.menuPath").as("curl"));
		criteria.setProjection(pList);

		criteria.add(Restrictions.eq("parents.pPkId", "0"));
		criteria.add(Restrictions.eq("parents.display", "1"));

		Restrictions.or(Restrictions.and(Restrictions.eq("child.display", "1"),
				Subqueries.propertyIn("child.pkId", authority)), Restrictions
				.isNull("child.pkId"));

		criteria.addOrder(Order.asc("parents.pkId"));
		criteria.addOrder(Order.asc("child.pkId"));

		criteria.setResultTransformer(Transformers.aliasToBean(MenuPo.class));

		return criteria.list();
	}
}
