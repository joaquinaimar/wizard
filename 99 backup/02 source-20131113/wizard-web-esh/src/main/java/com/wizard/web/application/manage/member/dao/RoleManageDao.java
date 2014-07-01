package com.wizard.web.application.manage.member.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import com.wizard.web.application.manage.member.bean.RoleInfo;
import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardOrgInfo;
import com.wizard.web.domain.entity.WizardRoleInfo;

@Repository
@SuppressWarnings("unchecked")
public class RoleManageDao extends BaseDao {

	public PageResponse<RoleInfo> searchRoleInfo(RoleInfo roleInfo,
			ExtPageRequest request) {
		String hql = "FROM WizardOrgInfo org JOIN org.wizardRoleInfo role WHERE 1 = 1 ";
		if (null != roleInfo.getRoleName()
				&& !"".equals(roleInfo.getRoleName())) {
			hql += "AND role.roleName = :roleName ";
		}
		if (null != roleInfo.getFkOrgId() && !"".equals(roleInfo.getFkOrgId())) {
			hql += "AND role.fkOrgId = :fkOrgId ";
		}
		Query query = super.createQuery(hql);
		query.setResultTransformer(new ResultTransformer() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				WizardOrgInfo org = (WizardOrgInfo) tuple[0];
				WizardRoleInfo role = (WizardRoleInfo) tuple[1];
				RoleInfo roleInfo = new RoleInfo();
				roleInfo.setPkId(role.getPkId());
				roleInfo.setRoleName(role.getRoleName());
				roleInfo.setRoleDetail(role.getRoleDetail());
				roleInfo.setFkOrgId(role.getFkOrgId());
				roleInfo.setOrgName(org.getOrgName());
				return roleInfo;
			}

			@Override
			@SuppressWarnings("rawtypes")
			public List transformList(List collection) {
				return collection;
			}

		});
		return super.pageQuery(query, roleInfo, request);
	}

	public List<WizardRoleInfo> getRoleInfoList() {
		Query query = super.createQuery("FROM WizardRoleInfo");
		return query.list();
	}

	public Integer getMaxPkId() {
		return super.getMaxInt("WizardRoleInfo", "pkId");
	}

	public int insertRoleInfo(WizardRoleInfo wizardRoleInfo) {
		super.save(wizardRoleInfo);
		return 1;
	}

	public int updateRoleInfo(WizardRoleInfo wizardRoleInfo) {
		super.update(wizardRoleInfo);
		return 1;
	}

	public int deleteRoleInfo(ParameterCollection collection) {
		Query query = super
				.createSQLQuery("DELETE role, user FROM wizard_role_info role LEFT JOIN wizard_user_info user ON role.PK_ID = user.FK_ROLE_ID WHERE role.PK_ID IN :pkIds");
		query.setParameterList("pkIds", collection.getRecords());
		return query.executeUpdate();
	}

}
