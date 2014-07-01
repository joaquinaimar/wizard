package com.wizard.web.basic.log.dao;

import org.springframework.stereotype.Repository;

import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.domain.entity.WizardLogInfo;

@Repository
public class LoggerDao extends BaseDao {

	public Integer getMaxPkId() {
		Integer result = selectOne("wizard.web.core.logger.getMaxPkId");
		return (null == result) ? 0 : result;
	}

	public int insertLogger(WizardLogInfo wizardLogInfo) {
		return insert("wizard.web.core.logger.insertLogger", wizardLogInfo);
	}

}
