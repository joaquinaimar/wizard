package com.wizard.web.application.service.maintain;

import com.wizard.j2ee.dao.PageRequest;
import com.wizard.j2ee.dao.PageResponse;
import com.wizard.web.application.vo.maintain.CodeInfoVo;
import com.wizard.web.domain.entity.wizard.WizardCode;

public interface ICodeMaintainService {

	PageResponse<WizardCode> getCodeTypeList(CodeInfoVo codeInfo,
			PageRequest request);

	PageResponse<WizardCode> getCodeInfoList(CodeInfoVo codeInfo,
			PageRequest request);

	WizardCode getCodeById(String id);

	int insertCode(CodeInfoVo codeInfo);

	int updateCode(CodeInfoVo codeInfo);

	int deleteTypeById(String[] ids);

	int deleteCodeById(String[] ids);

}
