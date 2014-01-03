package com.wizard.web.application.dao.manage;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wizard.j2ee.dao.hibernate.HibernateBaseDao;
import com.wizard.util.common.CommonUtil;

@Repository
public class UserManagerDao extends HibernateBaseDao {

	public int updateUser(Map<String, Object> params) {
		String hql = "UPDATE WizardUserInfo SET userDetail = :userDetail ";
		if (!CommonUtil.isNull(params.get("newPassword"))) 
			hql += ", password = :newPassword ";
		hql += "WHERE pkId = :id AND password = :oldPassword";
		return super.updateByHql(hql, params);
	}
}
