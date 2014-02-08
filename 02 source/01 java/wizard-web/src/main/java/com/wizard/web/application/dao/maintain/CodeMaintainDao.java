package com.wizard.web.application.dao.maintain;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wizard.j2ee.dao.PageRequest;
import com.wizard.j2ee.dao.PageResponse;
import com.wizard.j2ee.dao.hibernate.HibernateBaseDao;
import com.wizard.util.common.StringUtil;
import com.wizard.util.jdbc.JdbcUtil;
import com.wizard.web.application.vo.maintain.CodeInfoVo;
import com.wizard.web.domain.entity.wizard.WizardCode;

@Repository
public class CodeMaintainDao extends HibernateBaseDao {

	@SuppressWarnings("unchecked")
	public PageResponse<WizardCode> getCodeTypeList(CodeInfoVo codeInfo,
			PageRequest request) {
		Criteria criteria = super.createCriteria(WizardCode.class);
		criteria.add(Restrictions.eq("typeId", "0"));
		if (!StringUtil.isBlank(codeInfo.getContent()))
			criteria.add(Restrictions.like("content",
					JdbcUtil.like(codeInfo.getContent())));
		return super.pageQueryOrder(criteria, request);
	}

	@SuppressWarnings("unchecked")
	public PageResponse<WizardCode> getCodeInfoList(CodeInfoVo codeInfo,
			PageRequest request) {
		Criteria criteria = super.createCriteria(WizardCode.class);
		criteria.add(Restrictions.eq("typeId", codeInfo.getTypeId()));
		return super.pageQueryOrder(criteria, request);
	}

	public int updateCode(CodeInfoVo codeInfo) {
		String hql = "UPDATE WizardCode SET content = :content WHERE pkId = :pkId ";
		return super.updateByHql(hql, codeInfo);
	}

	public int deleteCodeByTypeId(String id) {
		String hql = "DELETE WizardCode WHERE typeId = :typeId ";
		Query query = super.createQuery(hql);
		query.setParameter("typeId", id);
		return query.executeUpdate();
	}

	public int deleteCodeById(String[] ids) {
		String hql = "DELETE WizardCode WHERE pkId in ( :pkId ) ";
		Query query = super.createQuery(hql);
		query.setParameterList("pkId", ids);
		return query.executeUpdate();
	}

}
