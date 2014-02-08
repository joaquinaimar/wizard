package com.wizard.web.application.service.maintain.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.j2ee.dao.PageRequest;
import com.wizard.j2ee.dao.PageResponse;
import com.wizard.util.common.BeanUtil;
import com.wizard.web.application.dao.maintain.CodeMaintainDao;
import com.wizard.web.application.service.maintain.ICodeMaintainService;
import com.wizard.web.application.vo.maintain.CodeInfoVo;
import com.wizard.web.domain.entity.wizard.WizardCode;

@Service
@Transactional
public class CodeMaintainServiceImpl implements ICodeMaintainService {

	@Autowired
	private CodeMaintainDao codeMaintainDao = null;

	@Override
	public PageResponse<WizardCode> getCodeTypeList(CodeInfoVo codeInfo,
			PageRequest request) {
		return codeMaintainDao.getCodeTypeList(codeInfo, request);
	}

	@Override
	public PageResponse<WizardCode> getCodeInfoList(CodeInfoVo codeInfo,
			PageRequest request) {
		return codeMaintainDao.getCodeInfoList(codeInfo, request);
	}

	@Override
	public WizardCode getCodeById(String id) {
		return codeMaintainDao.get(WizardCode.class, id);
	}

	@Override
	public int insertCode(CodeInfoVo codeInfo) {
		WizardCode code = new WizardCode();
		BeanUtil.copy(codeInfo, code);
		codeMaintainDao.save(code);
		return 1;
	}

	@Override
	public int updateCode(CodeInfoVo codeInfo) {
		return codeMaintainDao.updateCode(codeInfo);
	}

	@Override
	public int deleteTypeById(String[] ids) {

		for (String id : ids)
			codeMaintainDao.deleteCodeByTypeId(id);

		return codeMaintainDao.deleteCodeById(ids);
	}

	@Override
	public int deleteCodeById(String[] ids) {
		return codeMaintainDao.deleteCodeById(ids);
	}

}
