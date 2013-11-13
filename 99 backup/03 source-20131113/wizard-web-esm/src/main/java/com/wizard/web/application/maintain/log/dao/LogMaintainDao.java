package com.wizard.web.application.maintain.log.dao;

import org.springframework.stereotype.Repository;

import com.wizard.web.application.maintain.log.bean.LogInfo;
import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.basic.io.PageRequest;
import com.wizard.web.basic.io.PageResponse;

@Repository
public class LogMaintainDao extends BaseDao {

	public PageResponse<LogInfo> pageSearchLog(LogInfo logInfo,
			PageRequest request) {
		return pageList("wizard.web.framework.maintain.log.pageSearchLog",
				logInfo, request);
	}

	public int clearLog() {
		return delete("wizard.web.framework.maintain.log.clearLog");
	}
}
