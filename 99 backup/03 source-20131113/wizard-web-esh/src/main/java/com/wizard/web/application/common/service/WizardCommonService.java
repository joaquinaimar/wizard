package com.wizard.web.application.common.service;

import java.util.List;

import com.wizard.web.domain.entity.WizardCode;

public interface WizardCommonService {

	public List<WizardCode> getCodeList(String typeId, boolean flg);

}
