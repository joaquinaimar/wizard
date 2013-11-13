package com.wizard.web.basic.security.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.domain.entity.WizardUserInfo;

@Repository
public class SecurityDao extends BaseDao {

	public WizardUserInfo authenticationQuery(WizardUserInfo userInfo) {
		return selectOne("wizard.web.core.security.authenticationQuery",
				userInfo);
	}

	public List<String> userRolesQuery(String userName) {
		return selectList("wizard.web.core.security.userRolesQuery", userName);
	}

	public List<String> permissionsQuery(String roleName) {
		return selectList("wizard.web.core.security.permissionsQuery", roleName);
	}

}
