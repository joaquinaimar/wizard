package com.wizard.web.application.common.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.web.application.common.service.WizardCommonService;
import com.wizard.web.basic.io.extjs.ExtResponse;
import com.wizard.web.domain.entity.WizardCode;

@Controller
@RequestMapping("wizardframework/WizardCommon")
public class WizardCommonController {

	@Autowired
	private WizardCommonService wizardCommonService = null;

	@RequestMapping(value = "/getCodeList.do", method = RequestMethod.GET)
	@ResponseBody
	public ExtResponse<List<WizardCode>> doGetCodeList(String typeId, String flg) {

		List<WizardCode> codeList = wizardCommonService.getCodeList(typeId,
				"1".equals(flg));

		return new ExtResponse<List<WizardCode>>(true, codeList);
	}


}
