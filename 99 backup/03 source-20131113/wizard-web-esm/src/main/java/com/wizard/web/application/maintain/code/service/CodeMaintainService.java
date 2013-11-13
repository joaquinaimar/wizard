package com.wizard.web.application.maintain.code.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.wizard.web.application.maintain.code.bean.Code;
import com.wizard.web.basic.io.PageRequest;
import com.wizard.web.basic.io.PageResponse;

public interface CodeMaintainService {

	public PageResponse<Code> searchType(PageRequest request);

	public PageResponse<Code> searchCode(String typeId, PageRequest request);

	public List<Code> searchCode();

	public boolean downloadExcel(List<Code> codeList,
			HttpServletRequest request, HttpServletResponse response);

	public int updateCodeType(String pkId, String newId, String content);

	public int updateCodeContent(String pkId, String newId, String typeId,
			String content);

	public int deleteTypeCode(String[] pkIds);

	public int deleteContentCode(String[] pkIds);
	
	public HSSFSheet uploadExcel(HttpServletRequest request) throws IOException, FileUploadException;

	public int batchUpdateCode(HSSFSheet sheet);

	public int insertCode(String pkId, String typeId, String content);

}
