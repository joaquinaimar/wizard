package com.wizard.web.application.web.maintain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.j2ee.dao.PageRequest;
import com.wizard.j2ee.dao.PageResponse;
import com.wizard.j2ee.domain.PageResponseVo;
import com.wizard.web.application.service.maintain.ICodeMaintainService;
import com.wizard.web.application.vo.maintain.CodeInfoVo;
import com.wizard.web.domain.entity.wizard.WizardCode;

@Controller
@RequestMapping("wizard/maintain/code-maintain")
public class CodeMaintainController {

	@Autowired
	private ICodeMaintainService codeMaintainService = null;

	@RequestMapping(value = "/getCodeInfoList.do", method = RequestMethod.GET)
	@ResponseBody
	public PageResponseVo<WizardCode> getCodeInfoList(CodeInfoVo codeInfo,
			PageRequest request) {
		PageResponse<WizardCode> page = codeMaintainService.getCodeInfoList(
				codeInfo, request);
		return new PageResponseVo<WizardCode>(true, page);
	}
}
