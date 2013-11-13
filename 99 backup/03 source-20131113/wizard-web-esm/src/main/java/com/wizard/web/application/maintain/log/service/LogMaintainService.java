package com.wizard.web.application.maintain.log.service;

import com.wizard.web.application.maintain.log.bean.LogInfo;
import com.wizard.web.basic.io.PageRequest;
import com.wizard.web.basic.io.PageResponse;

public interface LogMaintainService {

    public PageResponse<LogInfo> searchLog(PageRequest request, LogInfo logInfo);

    public int clearLog();

}
