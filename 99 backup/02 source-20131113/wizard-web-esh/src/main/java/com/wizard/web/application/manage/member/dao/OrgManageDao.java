package com.wizard.web.application.manage.member.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wizard.web.basic.database.BaseDao;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.domain.entity.WizardOrgInfo;

@Repository
@SuppressWarnings("unchecked")
public class OrgManageDao extends BaseDao {

	public PageResponse<WizardOrgInfo> searchOrgInfo(WizardOrgInfo orgInfo,
			ExtPageRequest request) {
		String hql = "FROM WizardOrgInfo WHERE 1 = 1 ";
		if (null != orgInfo.getOrgName() && !"".equals(orgInfo.getOrgName())) {
			hql += " AND orgName = :orgName";
		}
		return super.pageQuery(super.createQuery(hql), orgInfo, request);
	}

	public List<WizardOrgInfo> getOrgInfoList() {
		Query query = super.createQuery("FROM WizardOrgInfo");
		return query.list();
	}

	public Integer getMaxPkId() {
		return super.getMaxInt("WizardOrgInfo", "pkId");
	}

	public int insertOrgInfo(WizardOrgInfo wizardOrgInfo) {
		super.save(wizardOrgInfo);
		return 1;
	}

	public int updateOrgInfo(WizardOrgInfo wizardOrgInfo) {
		super.update(wizardOrgInfo);
		return 1;
	}

	public int deleteOrgInfo(ParameterCollection collection) {
		Query query = super
				.createSQLQuery("DELETE org, role, user FROM wizard_org_info org LEFT JOIN wizard_role_info role ON org.PK_ID = role.FK_ORG_ID LEFT JOIN wizard_user_info user ON role.PK_ID = user.FK_ROLE_ID WHERE org.PK_ID IN :pkIds");
		query.setParameterList("pkIds", collection.getRecords());
		return query.executeUpdate();
	}

}
