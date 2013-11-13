package com.wizard.web.application.maintain.code.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import com.wizard.web.application.maintain.code.bean.Code;
import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.basic.io.PageRequest;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.domain.entity.WizardCode;

@Repository
@SuppressWarnings("unchecked")
public class CodeMaintainDao extends BaseDao {

	public PageResponse<Code> searchType(PageRequest request) {
		Query query = super.createQuery("FROM WizardCode WHERE typeId = '0'");
		query.setResultTransformer(new ResultTransformer() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				WizardCode wizardCode = (WizardCode) tuple[0];
				Code code = new Code();
				code.setPkId(wizardCode.getPkId());
				code.setNewId(wizardCode.getPkId());
				code.setTypeId(wizardCode.getTypeId());
				code.setContent(wizardCode.getContent());
				return code;
			}

			@Override
			@SuppressWarnings("rawtypes")
			public List transformList(List collection) {
				return collection;
			}

		});
		return super.pageQuery(query, request);
	}

	public PageResponse<Code> searchCode(String typeId, PageRequest request) {
		WizardCode wizardCode = new WizardCode();
		wizardCode.setTypeId(typeId);
		return super.pageQuery(searchCode(typeId), wizardCode, request);
	}

	public List<Code> searchCode() {
		return searchCode(null).list();
	}

	private Query searchCode(String typeId) {

		String hql = "FROM WizardCode type ";
		if (null == typeId) {
			hql += " RIGHT ";
		}
		hql += " JOIN type.wizardCodeTypeCode code ";
		if (null != typeId) {
			hql += "WHERE code.typeId = :typeId ";
		}
		hql += "ORDER BY code.typeId ASC, code.pkId ASC ";

		Query query = super.createQuery(hql);
		query.setResultTransformer(new ResultTransformer() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				WizardCode wizardCodeType = (WizardCode) tuple[0];
				WizardCode wizardCode = (WizardCode) tuple[1];
				Code code = new Code();
				code.setPkId(wizardCode.getPkId());
				code.setNewId(wizardCode.getPkId());
				code.setTypeId(wizardCode.getTypeId());
				code.setContent(wizardCode.getContent());
				if (null != wizardCodeType)
					code.setType(wizardCodeType.getContent());
				return code;
			}

			@Override
			@SuppressWarnings("rawtypes")
			public List transformList(List collection) {
				return collection;
			}

		});
		return query;

	}

	public int updateCode(Code code) {
		String hql = "UPDATE WizardCode SET pkId = :newId";
		if (null != code.getTypeId() && !"".equals(code.getTypeId())) {
			hql += ", typeId = :typeId";
		}
		hql += ", content = :content WHERE pkId = :pkId";
		return super.updateByHql(hql, code);
	}

	public int batchUpdateCode(Code code) {
		return super.updateByHql(
				"UPDATE WizardCode SET pkId = :newId WHERE pkId = :pkId", code);
	}

	public int deleteCode(ParameterCollection collection) {
		Query query = super
				.createQuery("DELETE WizardCode WHERE pkId IN :pkIds");
		query.setParameterList("pkIds", collection.getRecords());
		return query.executeUpdate();
	}

	public int deleteCodeContent(ParameterCollection collection) {
		Query query = super
				.createQuery("DELETE WizardCode WHERE typeId IN :typeIds");
		query.setParameterList("typeIds", collection.getRecords());
		return query.executeUpdate();
	}

	public int clearCode() {
		return super.clear("WizardCode");
	}

	public int insertCode(WizardCode wizardCode) {
		super.save(wizardCode);
		return 1;
	}

}
