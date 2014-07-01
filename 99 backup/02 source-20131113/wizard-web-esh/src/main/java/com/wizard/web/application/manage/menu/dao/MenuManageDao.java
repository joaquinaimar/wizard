package com.wizard.web.application.manage.menu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import com.wizard.web.application.manage.menu.bean.Menu;
import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.basic.io.PageRequest;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.domain.entity.WizardMenu;

@Repository
@SuppressWarnings("unchecked")
public class MenuManageDao extends BaseDao {

	private ResultTransformer menuResultTransformer = new ResultTransformer() {

		private static final long serialVersionUID = 1L;

		@Override
		public Object transformTuple(Object[] tuple, String[] aliases) {
			WizardMenu wizardMenu = (WizardMenu) tuple[0];
			Menu menu = new Menu();
			menu.setPkId(wizardMenu.getPkId());
			menu.setNewId(wizardMenu.getPkId());
			menu.setPPkId(wizardMenu.getPPkId());
			menu.setMenuName(wizardMenu.getMenuName());
			menu.setMenuPath(wizardMenu.getMenuPath());
			menu.setDisplay(wizardMenu.getDisplay());
			return menu;
		}

		@Override
		@SuppressWarnings("rawtypes")
		public List transformList(List collection) {
			return collection;
		}

	};

	public String getFirstPage() {
		Criteria criteria = super.createCriteria(WizardMenu.class);
		criteria.add(Restrictions.eq("pkId", "00"));
		criteria.add(Restrictions.eq("pPkId", "0"));
		WizardMenu menu = (WizardMenu) criteria.uniqueResult();
		return menu.getMenuPath();
	}

	public int updateFirstPage(String menuPath) {
		WizardMenu menu = new WizardMenu();
		menu.setMenuPath(menuPath);
		return super
				.updateByHql(
						"UPDATE WizardMenu SET menuPath = :menuPath WHERE pkId = '00' AND pPkId = '0'",
						menu);
	}

	public PageResponse<Menu> getParentMenu(PageRequest request) {

		Query query = super
				.createQuery("FROM WizardMenu WHERE pPkId = '0' AND (menuPath is null OR menuPath = '')");
		query.setResultTransformer(menuResultTransformer);
		return super.pageQuery(query, request);
	}

	public int updateMenu(Menu menu) {
		String hql = "UPDATE WizardMenu SET pkId = :newId , menuName = :menuName";
		if (null != menu.getMenuPath() && !"".equals(menu.getMenuPath())) {
			hql += ", menuPath = :menuPath";
		}
		hql += ", display = :display WHERE pkId = :pkId";
		return super.updateByHql(hql, menu);
	}

	public int batchUpdateMenu(Menu menu) {
		return super.updateByHql(
				"UPDATE WizardMenu SET pPkId = :newId WHERE pPkId = :pkId", menu);
	}

	public PageResponse<Menu> getChildMenu(String pPkId, PageRequest request) {
		Map<String, Object> bean = new HashMap<String, Object>();
		bean.put("pPkId", pPkId);
		Query query = super.createQuery("FROM WizardMenu WHERE pPkId = :pPkId");
		query.setResultTransformer(menuResultTransformer);
		return super.pageQuery(query, bean, request);
	}

	public int insertMenu(WizardMenu wizardMenu) {
		super.save(wizardMenu);
		return 1;
	}

	public int deleteMenu(ParameterCollection collection) {
		Query query = super
				.createQuery("DELETE FROM WizardMenu WHERE pkId IN :pkIds");
		query.setParameterList("pkIds", collection.getRecords());
		return query.executeUpdate();
	}

	public int deleteChildMenu(ParameterCollection collection) {
		Query query = super
				.createQuery("DELETE FROM WizardMenu WHERE  pPkId IN :pPkIds");
		query.setParameterList("pPkIds", collection.getRecords());
		return query.executeUpdate();
	}

}
