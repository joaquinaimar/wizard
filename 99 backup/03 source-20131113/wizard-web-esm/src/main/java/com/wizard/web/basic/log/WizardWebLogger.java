package com.wizard.web.basic.log;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.web.basic.log.dao.LoggerDao;
import com.wizard.web.domain.entity.WizardLogInfo;
import com.wizard.web.utils.WizardUtils;
import com.wizard.web.utils.WizardWebUtils;

@Service
@Transactional
public class WizardWebLogger {

	public final static String LOG_LEVEL_DEBUG = "1010";

	public final static String LOG_LEVEL_INFO = "1020";

	public final static String LOG_LEVEL_WARN = "1030";

	public final static String LOG_LEVEL_ERROR = "1040";

	public final static String LOG_LEVEL_FATAL = "1050";

	@Autowired
	private LoggerDao loggerDao = null;

	/**
	 * 
	 * @param title
	 * @param request
	 */
	public void debug(String title, HttpServletRequest request) {
		debug(title, request, WizardWebUtils.getUserName(request.getSession()));
	}

	/**
	 * 
	 * @param title
	 * @param request
	 * @param userName
	 */
	public void debug(String title, HttpServletRequest request, String userName) {

		debug(title, WizardUtils.toJson(request.getParameterMap()), userName);
	}

	/**
	 * 
	 * @param title
	 * @param detail
	 * @param userName
	 */
	public void debug(String title, String detail, String userName) {
		insertLogInfo(LOG_LEVEL_DEBUG, title, detail, userName);
	}

	/**
	 * 
	 * @param title
	 * @param request
	 */
	public void info(String title, HttpServletRequest request) {
		info(title, request, WizardWebUtils.getUserName(request.getSession()));
	}

	/**
	 * 
	 * @param title
	 * @param request
	 * @param userName
	 */
	public void info(String title, HttpServletRequest request, String userName) {

		info(title, WizardUtils.toJson(request.getParameterMap()), userName);
	}

	/**
	 * 
	 * @param title
	 * @param detail
	 * @param userName
	 */
	public void info(String title, String detail, String userName) {
		insertLogInfo(LOG_LEVEL_INFO, title, detail, userName);
	}

	/**
	 * 
	 * @param title
	 * @param request
	 */
	public void warn(String title, HttpServletRequest request) {
		info(title, request, WizardWebUtils.getUserName(request.getSession()));
	}

	/**
	 * 
	 * @param title
	 * @param request
	 * @param userName
	 */
	public void warn(String title, HttpServletRequest request, String userName) {

		warn(title, WizardUtils.toJson(request.getParameterMap()), userName);
	}

	/**
	 * 
	 * @param title
	 * @param detail
	 * @param userName
	 */
	public void warn(String title, String detail, String userName) {

		insertLogInfo(LOG_LEVEL_WARN, title, detail, userName);
	}

	/**
	 * 
	 * @param title
	 * @param request
	 */
	public void error(String title, HttpServletRequest request) {
		info(title, request, WizardWebUtils.getUserName(request.getSession()));
	}

	/**
	 * 
	 * @param title
	 * @param request
	 * @param userName
	 */
	public void error(String title, HttpServletRequest request, String userName) {

		error(title, WizardUtils.toJson(request.getParameterMap()), userName);
	}

	/**
	 * 
	 * @param title
	 * @param detail
	 * @param userName
	 */
	public void error(String title, String detail, String userName) {

		insertLogInfo(LOG_LEVEL_ERROR, title, detail, userName);
	}

	/**
	 * 
	 * @param title
	 * @param request
	 */
	public void fatal(String title, HttpServletRequest request) {
		info(title, request, WizardWebUtils.getUserName(request.getSession()));
	}

	/**
	 * 
	 * @param title
	 * @param request
	 * @param userName
	 */
	public void fatal(String title, HttpServletRequest request, String userName) {

		fatal(title, WizardUtils.toJson(request.getParameterMap()), userName);
	}

	/**
	 * 
	 * @param title
	 * @param detail
	 * @param userName
	 */
	public void fatal(String title, String detail, String userName) {

		insertLogInfo(LOG_LEVEL_FATAL, title, detail, userName);
	}

	/**
	 * 
	 * @param level
	 * @param title
	 * @param detail
	 * @param userName
	 */
	private void insertLogInfo(String level, String title, String detail,
			String userName) {

		int pkId = loggerDao.getMaxPkId();

		WizardLogInfo wizardLogInfo = new WizardLogInfo();
		wizardLogInfo.setPkId(Integer.toString(pkId + 1));
		wizardLogInfo.setLogLevel(level);
		wizardLogInfo.setLogTitle(title);
		wizardLogInfo.setLogDetail(detail);
		wizardLogInfo.setUserName(userName);

		loggerDao.insertLogger(wizardLogInfo);

	}

}
