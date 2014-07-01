package com.wizard.web.application.maintain.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.web.application.maintain.log.bean.LogInfo;
import com.wizard.web.application.maintain.log.dao.LogMaintainDao;
import com.wizard.web.basic.io.PageRequest;
import com.wizard.web.basic.io.PageResponse;

@Service
@Transactional
public class LogMaintainServiceImpl implements LogMaintainService {

	@Autowired
	private LogMaintainDao logMaintainDao = null;

	@Override
	public PageResponse<LogInfo> searchLog(PageRequest request, LogInfo logInfo) {
		return logMaintainDao.pageSearchLog(logInfo, request);
	}

	@Override
	public int clearLog() {
		return logMaintainDao.clear("WizardLogInfo");
	}

}
