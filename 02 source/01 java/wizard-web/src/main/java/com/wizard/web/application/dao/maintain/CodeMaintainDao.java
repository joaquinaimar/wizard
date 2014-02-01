package com.wizard.web.application.dao.maintain;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.wizard.j2ee.dao.PageRequest;
import com.wizard.j2ee.dao.PageResponse;
import com.wizard.j2ee.dao.hibernate.HibernateBaseDao;
import com.wizard.web.application.vo.maintain.CodeInfoVo;
import com.wizard.web.domain.entity.wizard.WizardCode;

@Repository
public class CodeMaintainDao extends HibernateBaseDao {

	@SuppressWarnings("unchecked")
	public PageResponse<WizardCode> getCodeInfoList(CodeInfoVo codeInfo,
			PageRequest request) {
		Criteria criteria = super.createCriteria(WizardCode.class);
		return super.pageQueryOrder(criteria, request);
	}

}
