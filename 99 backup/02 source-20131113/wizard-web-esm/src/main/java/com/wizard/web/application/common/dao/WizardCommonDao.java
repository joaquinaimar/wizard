package com.wizard.web.application.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.domain.entity.WizardCode;

@Repository
public class WizardCommonDao extends BaseDao {

	public List<WizardCode> getCodeList(String typeId) {
		return selectList("wizard.web.framework.common.getCodeList", typeId);
	}

}
