package com.wizard.web.application.maintain.code.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wizard.web.application.maintain.code.bean.Code;
import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.basic.io.PageRequest;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.domain.entity.WizardCode;

@Repository
public class CodeMaintainDao extends BaseDao {

	public PageResponse<Code> searchType(PageRequest request) {
		return pageList("wizard.web.framework.maintain.code.searchType",
				request);
	}

	public PageResponse<Code> searchCode(String typeId, PageRequest request) {
		return pageList("wizard.web.framework.maintain.code.searchCode",
				typeId, request);
	}

	public List<Code> searchCode() {
		return selectList("wizard.web.framework.maintain.code.downCode");
	}

	public int updateCode(Code code) {
		return update("wizard.web.framework.maintain.code.updateCode", code);
	}

	public int batchUpdateCode(Code code) {
		return update("wizard.web.framework.maintain.code.batchUpdateCode",
				code);
	}

	public int deleteCode(ParameterCollection collection) {
		return delete("wizard.web.framework.maintain.code.deleteCode",
				collection);
	}

	public int deleteCodeContent(ParameterCollection collection) {
		return delete("wizard.web.framework.maintain.code.deleteCodeContent",
				collection);
	}

	public int clearCode() {
		return delete("wizard.web.framework.maintain.code.clearCode");
	}

	public int insertCode(WizardCode wizardCode) {
		return insert("wizard.web.framework.maintain.code.insertCode",
				wizardCode);
	}

}
