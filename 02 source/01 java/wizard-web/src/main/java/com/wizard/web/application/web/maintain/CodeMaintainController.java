package com.wizard.web.application.web.maintain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.j2ee.dao.PageRequest;
import com.wizard.j2ee.dao.PageResponse;
import com.wizard.j2ee.domain.PageResponseVo;
import com.wizard.j2ee.domain.ResponseVo;
import com.wizard.web.application.service.maintain.ICodeMaintainService;
import com.wizard.web.application.vo.maintain.CodeInfoVo;
import com.wizard.web.domain.entity.wizard.WizardCode;

@Controller
@RequestMapping("wizard/maintain/code-maintain")
public class CodeMaintainController {

	@Autowired
	private ICodeMaintainService codeMaintainService = null;

	@RequestMapping(value = "/getCodeTypeList.do", method = RequestMethod.GET)
	@ResponseBody
	public PageResponseVo<WizardCode> getCodeTypeList(CodeInfoVo codeInfo,
			PageRequest request) {
		PageResponse<WizardCode> page = codeMaintainService.getCodeTypeList(
				codeInfo, request);
		return new PageResponseVo<WizardCode>(true, page);
	}

	@RequestMapping(value = "/getCodeInfoList.do", method = RequestMethod.GET)
	@ResponseBody
	public PageResponseVo<WizardCode> getCodeInfoList(CodeInfoVo codeInfo,
			PageRequest request) {
		PageResponse<WizardCode> page = codeMaintainService.getCodeInfoList(
				codeInfo, request);
		return new PageResponseVo<WizardCode>(true, page);
	}

	@RequestMapping(value = "/getCodeById.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<WizardCode> getCodeById(@RequestParam String id) {
		WizardCode code = codeMaintainService.getCodeById(id);
		return new ResponseVo<WizardCode>(true, code);
	}

	@RequestMapping(value = "/insertCode.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Integer> insertCode(@ModelAttribute CodeInfoVo codeInfo) {
		int result = codeMaintainService.insertCode(codeInfo);
		return new ResponseVo<>(true, result);
	}

	@RequestMapping(value = "/updateCode.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Integer> updateCode(@ModelAttribute CodeInfoVo codeInfo) {
		int result = codeMaintainService.updateCode(codeInfo);
		return new ResponseVo<>(true, result);
	}
	
	@RequestMapping(value = "/deleteTypeById.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Integer> deleteTypeById(@RequestParam String[] ids) {
		int result = codeMaintainService.deleteTypeById(ids);
		return new ResponseVo<>(true, result);
	}

	@RequestMapping(value = "/deleteCodeById.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Integer> deleteCodeById(@RequestParam String[] ids) {
		int result = codeMaintainService.deleteCodeById(ids);
		return new ResponseVo<>(true, result);
	}

}
