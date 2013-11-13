package com.wizard.web.application.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.web.application.common.dao.WizardCommonDao;
import com.wizard.web.domain.entity.WizardCode;

@Service
@Transactional
public class WizardCommonServiceImpl implements WizardCommonService {

	@Autowired
	private WizardCommonDao wizardCommonDao = null;

	@Override
	public List<WizardCode> getCodeList(String typeId, boolean flg) {
		List<WizardCode> list = new ArrayList<WizardCode>();
		if (flg) {
			WizardCode all = new WizardCode();
			all.setPkId("");
			all.setContent("ALL");
			list.add(all);
		}
		list.addAll(wizardCommonDao.getCodeList(typeId));
		return list;
	}
}
