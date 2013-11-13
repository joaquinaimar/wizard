package com.wizard.web.application.maintain.log.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.web.application.maintain.log.bean.LogInfo;
import com.wizard.web.application.maintain.log.service.LogMaintainService;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.basic.io.extjs.ExtPageResponse;
import com.wizard.web.basic.io.extjs.ExtResponse;
import com.wizard.web.basic.log.WizardWebLogger;

@Controller
@RequestMapping("wizardframework/LogMaintain")
public class LogMaintainController {

    @Autowired
    private WizardWebLogger wizardLogger = null;

    @Autowired
    private LogMaintainService logMaintainService = null;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

    @RequestMapping(value = "/searchLog.do", method = RequestMethod.GET)
    @ResponseBody
    public ExtPageResponse<LogInfo> doSearchLog(HttpServletRequest request,
            LogInfo logInfo, ExtPageRequest pageRequest) {
        PageResponse<LogInfo> page = logMaintainService.searchLog(pageRequest,
                logInfo);
        return new ExtPageResponse<LogInfo>(true, page);
    }

    @RequestMapping(value = "/clearLog.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doClearLog(HttpServletRequest request) {
        int result = logMaintainService.clearLog();
        String message = "清空日志表：" + result + "条";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

}
