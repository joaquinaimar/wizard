package com.wizard.web.application.manage.permission.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import com.wizard.web.application.manage.permission.bean.Menu;
import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardAuthority;
import com.wizard.web.domain.entity.WizardMenu;

@Repository
@SuppressWarnings("unchecked")
public class PermissionManageDao extends BaseDao {

	public PageResponse<Menu> searchMenu(ExtPageRequest request) {
		Query query = super
				.createQuery("FROM WizardMenu p JOIN p.wizardMenu c WHERE c.display = '1' AND p.display = '1'");
		query.setResultTransformer(new ResultTransformer() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				WizardMenu p = (WizardMenu) tuple[0];
				WizardMenu c = (WizardMenu) tuple[1];
				Menu menu = new Menu();
				menu.setPkId(c.getPkId());
				menu.setMenuName(c.getMenuName());
				menu.setParentMenuName(p.getMenuName());
				return menu;
			}

			@Override
			@SuppressWarnings("rawtypes")
			public List transformList(List collection) {
				return collection;
			}

		});
		return super.pageQuery(query, request);
	}

	public List<WizardAuthority> getAuthority() {
		Criteria criteria = super.createCriteria(WizardAuthority.class);
		criteria.addOrder(Order.asc("fkRoleId"));
		return criteria.list();
	}

	public int clearAuthority() {
		return super.clear("WizardAuthority");
	}

	public int insertAuthority(WizardAuthority authority) {
		super.save(authority);
		return 1;
	}

}
