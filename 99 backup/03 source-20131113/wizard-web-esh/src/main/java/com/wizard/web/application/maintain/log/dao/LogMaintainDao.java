package com.wizard.web.application.maintain.log.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import com.wizard.web.application.maintain.log.bean.LogInfo;
import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.basic.io.PageRequest;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.domain.entity.WizardCode;
import com.wizard.web.domain.entity.WizardLogInfo;

@Repository
@SuppressWarnings("unchecked")
public class LogMaintainDao extends BaseDao {

	public PageResponse<LogInfo> pageSearchLog(LogInfo logInfo,
			PageRequest pageRequest) {
		Map<String, Object> bean = new HashMap<String, Object>();
		String hql = "FROM WizardLogInfo log LEFT JOIN log.wizardCodeLogLevel ";
		hql += "WHERE 1 = 1 ";

		if (null != logInfo.getLogTitle() && !"".equals(logInfo.getLogTitle())) {
			hql += "AND log.logTitle LIKE :logTitle ";
			bean.put("logTitle", "%" + logInfo.getLogTitle() + "%");
		}
		if (null != logInfo.getUserName() && !"".equals(logInfo.getUserName())) {
			hql += "AND log.userName = :userName ";
			bean.put("userName", logInfo.getUserName());
		}
		if (null != logInfo.getLogLevel() && !"".equals(logInfo.getLogLevel())) {
			hql += "AND log.logLevel = :logLevel ";
			bean.put("logLevel", logInfo.getLogLevel());
		}
		if (null != logInfo.getLogTimeStart()) {
			hql += "AND log.logTime > :logTimeStart ";
			bean.put("logTimeStart", logInfo.getLogTimeStart());
		}
		if (null != logInfo.getLogTimeEnd()) {
			hql += "AND log.logTime < :logTimeEnd ";
			bean.put("logTimeEnd", logInfo.getLogTimeEnd());
		}

		hql += "ORDER BY CAST(log.pkId as int) DESC";

		Query query = super.createQuery(hql);

		query.setResultTransformer(new ResultTransformer() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				WizardLogInfo logInfo = (WizardLogInfo) tuple[0];
				WizardCode code = (WizardCode) tuple[1];
				LogInfo log = new LogInfo();
				log.setPkId(logInfo.getPkId());
				log.setLogLevel(logInfo.getLogLevel());
				log.setLogLevelValue(code.getContent());
				log.setLogTitle(logInfo.getLogTitle());
				log.setLogDetail(logInfo.getLogDetail());
				log.setLogTime(logInfo.getLogTime());
				log.setUserName(logInfo.getUserName());
				return log;
			}

			@Override
			@SuppressWarnings("rawtypes")
			public List transformList(List collection) {
				return collection;
			}

		});
		return super.pageQuery(query, bean, pageRequest);
	}
}
