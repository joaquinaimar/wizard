package com.wizard.web.application.manage.member.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import com.wizard.web.application.manage.member.bean.UserInfo;
import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardRoleInfo;
import com.wizard.web.domain.entity.WizardUserInfo;

@Repository
@SuppressWarnings("unchecked")
public class UserManageDao extends BaseDao {

	public WizardUserInfo getUserInfo(String userName) {
		Query query = super
				.createQuery("FROM WizardUserInfo WHERE userName = :userName");
		query.setParameter("userName", userName);
		return (WizardUserInfo) query.uniqueResult();
	}

	public int modifyPassword(UserInfo userInfo) {
		Query query = super
				.createQuery("UPDATE WizardUserInfo SET password = :newPassword  WHERE pkId = :pkId AND password = :password");
		query.setProperties(userInfo);
		return query.executeUpdate();
	}

	public PageResponse<UserInfo> searchUserInfo(UserInfo userInfo,
			ExtPageRequest request) {
		String hql = "FROM WizardRoleInfo role JOIN role.wizardUserInfo user WHERE 1 = 1 ";
		if (null != userInfo.getUserName()
				&& !"".equals(userInfo.getUserName())) {
			hql += "AND user.userName = :userName ";
		}
		if (null != userInfo.getFkRoleId()
				&& !"".equals(userInfo.getFkRoleId())) {
			hql += "AND user.fkRoleId = :fkRoleId ";
		}
		Query query = super.createQuery(hql);
		query.setResultTransformer(new ResultTransformer() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				WizardRoleInfo role = (WizardRoleInfo) tuple[0];
				WizardUserInfo user = (WizardUserInfo) tuple[1];
				UserInfo userInfo = new UserInfo();
				userInfo.setPkId(user.getPkId());
				userInfo.setUserName(user.getUserName());
				userInfo.setUserDetail(user.getUserDetail());
				userInfo.setFkRoleId(user.getFkRoleId());
				userInfo.setRoleName(role.getRoleName());
				return userInfo;
			}

			@Override
			@SuppressWarnings("rawtypes")
			public List transformList(List collection) {
				return collection;
			}

		});
		return super.pageQuery(query, userInfo, request);
	}

	public Integer getMaxPkId() {
		return super.getMaxInt("WizardUserInfo", "pkId");
	}

	public int insertUserInfo(WizardUserInfo wizardUserInfo) {
		super.save(wizardUserInfo);
		return 1;
	}

	public int updateUserInfo(WizardUserInfo wizardUserInfo) {
		Query query = super
				.createQuery("UPDATE WizardUserInfo SET userName = :userName, userDetail = :userDetail, fkRoleId = :fkRoleId WHERE pkId = :pkId ");
		query.setProperties(wizardUserInfo);
		return query.executeUpdate();
	}

	public int deleteUserInfo(ParameterCollection collection) {
		Query query = super
				.createQuery("DELETE FROM WizardUserInfo WHERE pkId IN :pkIds");
		query.setParameterList("pkIds", collection.getRecords());
		return query.executeUpdate();
	}

}
