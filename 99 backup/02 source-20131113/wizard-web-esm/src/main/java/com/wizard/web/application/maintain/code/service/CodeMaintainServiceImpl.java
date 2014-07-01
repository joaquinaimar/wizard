package com.wizard.web.application.maintain.code.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizard.web.application.maintain.code.bean.Code;
import com.wizard.web.application.maintain.code.dao.CodeMaintainDao;
import com.wizard.web.basic.io.PageRequest;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.ParameterCollection;
import com.wizard.web.domain.entity.WizardCode;
import com.wizard.web.domain.template.ColumnBean;
import com.wizard.web.domain.template.TemplateBean;
import com.wizard.web.utils.WizardUtils;
import com.wizard.web.utils.WizardWebFileUtils;

@Service
@Transactional
public class CodeMaintainServiceImpl implements CodeMaintainService {

	@Autowired
	private CodeMaintainDao codeMaintainDao = null;

	@Override
	public PageResponse<Code> searchType(PageRequest request) {
		return codeMaintainDao.searchType(request);
	}

	@Override
	public PageResponse<Code> searchCode(String typeId, PageRequest request) {
		return codeMaintainDao.searchCode(typeId, request);
	}

	@Override
	public List<Code> searchCode() {
		return codeMaintainDao.searchCode();
	}

	@Override
	public boolean downloadExcel(List<Code> codeList,
			HttpServletRequest request, HttpServletResponse response) {

		TemplateBean template = WizardWebFileUtils
				.getXmlTemplate("template/wizard/WizardCode.xml");

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(template.getName());
		HSSFCell cell = null;
		HSSFRow rowHead = sheet.createRow(0);
		cell = rowHead.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(template.getId());
		cell = rowHead.createCell(1);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(template.getName());

		HSSFRow rowTitle = sheet.createRow(1);

		List<Method> methods = new ArrayList<Method>();
		Method method = null;

		try {
			for (ColumnBean column : template.getColumns()) {
				cell = rowTitle.createCell(column.getIndex());
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(column.getName());

				method = Code.class.getMethod("get"
						+ StringUtils.capitalize((column.getField())));
				methods.add(method);
			}
		} catch (NoSuchMethodException | SecurityException e) {
			return false;
		}

		try {
			int i = 2;
			HSSFRow rowContent = null;
			for (Code code : codeList) {
				rowContent = sheet.createRow(i++);
				int j = 0;
				for (ColumnBean column : template.getColumns()) {
					cell = rowContent.createCell(column.getIndex());
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue((String) methods.get(j++).invoke(code));
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			return false;
		}

		try {
			OutputStream out = WizardWebFileUtils.getDownloadOutputStream(
					request, response, "代码表.xls");
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			return false;
		}

		return true;
	}

	@Override
	public int updateCodeType(String pkId, String newId, String content) {
		Code code = new Code();
		code.setPkId(pkId);
		code.setNewId(newId);
		code.setContent(content);

		if (!pkId.equals(newId)) {
			codeMaintainDao.batchUpdateCode(code);
		}

		return codeMaintainDao.updateCode(code);
	}

	@Override
	public int updateCodeContent(String pkId, String newId, String typeId,
			String content) {
		Code code = new Code();
		code.setPkId(pkId);
		code.setNewId(newId);
		code.setTypeId(typeId);
		code.setContent(content);
		return codeMaintainDao.updateCode(code);
	}

	@Override
	public int deleteTypeCode(String[] pkIds) {
		codeMaintainDao.deleteCodeContent(new ParameterCollection(pkIds));
		return codeMaintainDao.deleteCode(new ParameterCollection(pkIds));
	}

	@Override
	public int deleteContentCode(String[] pkIds) {
		return codeMaintainDao.deleteCode(new ParameterCollection(pkIds));
	}

	@Override
	public HSSFSheet uploadExcel(HttpServletRequest request)
			throws IOException, FileUploadException {

		ServletFileUpload fileupload = new ServletFileUpload(
				new DiskFileItemFactory());
		fileupload.setSizeMax(1024 * 1024 * 10);
		@SuppressWarnings("unchecked")
		List<FileItem> fileitems = fileupload.parseRequest(request);

		InputStream inputStream = null;

		for (FileItem fileitem : fileitems) {
			if (!fileitem.isFormField()) {
				inputStream = fileitem.getInputStream();
				break;
			}
		}

		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		return workbook.getSheetAt(0);

	}

	@Override
	public int batchUpdateCode(HSSFSheet sheet) {

		TemplateBean template = WizardWebFileUtils
				.getXmlTemplate("template/wizard/WizardCode.xml");

		List<Method> methods = new ArrayList<Method>();

		Class<WizardCode> WizardCodeCls = WizardCode.class;
		HSSFRow row = null;
		HSSFCell cell = null;
		WizardCode wizardCode = null;
		Method method = null;

		try {
			for (ColumnBean column : template.getColumns()) {
				try {
					method = WizardCodeCls
							.getMethod(
									"set"
											+ StringUtils.capitalize((column
													.getField())),
									Class.forName(column.getType()));
					methods.add(method);
				} catch (NoSuchMethodException | SecurityException e) {
					methods.add(null);
				}
			}
		} catch (ClassNotFoundException e) {
			return -1;
		}

		codeMaintainDao.clearCode();

		int i = 0;
		int j = 0;
		try {
			for (;; i++) {
				row = sheet.getRow(i + 2);
				if (null == row) {
					break;
				}
				j = 0;
				wizardCode = new WizardCode();
				for (ColumnBean column : template.getColumns()) {
					method = methods.get(j++);
					if (null == method) {
						continue;
					}
					cell = row.getCell(column.getIndex());
					if (null != cell) {
						method.invoke(wizardCode, cell.getStringCellValue());
					}
				}

				if (WizardUtils.isBlank(wizardCode.getPkId())
						|| WizardUtils.isBlank(wizardCode.getTypeId())) {
					break;
				}

				codeMaintainDao.insertCode(wizardCode);
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			return -2;
		}

		return i;
	}

	@Override
	public int insertCode(String pkId, String typeId, String content) {
		WizardCode wizardCode = new WizardCode();
		wizardCode.setPkId(pkId);
		wizardCode.setTypeId(typeId);
		wizardCode.setContent(content);
		return codeMaintainDao.insertCode(wizardCode);
	}

}
