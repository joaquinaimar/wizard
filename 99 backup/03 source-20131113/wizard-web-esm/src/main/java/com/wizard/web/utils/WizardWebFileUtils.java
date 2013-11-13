package com.wizard.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wizard.web.domain.template.ColumnBean;
import com.wizard.web.domain.template.TemplateBean;

public final class WizardWebFileUtils {

	public static TemplateBean getXmlTemplate(String path) {

		InputStream inputStream = WizardWebFileUtils.class.getClassLoader()
				.getResourceAsStream(path);

		TemplateBean templateBean = new TemplateBean();

		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			saxReader.setEncoding("UTF-8");
			doc = saxReader.read(inputStream);
		} catch (DocumentException e) {
		}

		Element root = doc.getRootElement();

		templateBean.setId(root.attributeValue("id"));
		templateBean.setName(root.attributeValue("name"));

		List<ColumnBean> columns = new ArrayList<ColumnBean>();

		@SuppressWarnings("unchecked")
		Iterator<Element> it = root.elementIterator("column");
		Element column = null;
		ColumnBean columnBean = new ColumnBean();
		while (it.hasNext()) {
			column = it.next();
			columnBean = new ColumnBean();
			columnBean.setField(column.attributeValue("field"));
			columnBean.setType(column.attributeValue("type"));
			columnBean.setName(column.attributeValue("name"));
			columnBean
					.setIndex(Integer.parseInt(column.attributeValue("index")));
			columns.add(columnBean);
		}

		templateBean.setColumns(columns);

		return templateBean;
	}

	public static OutputStream getDownloadOutputStream(
			HttpServletRequest request, HttpServletResponse response,
			String fileName) throws IOException {

		fileName = getFileName(request, fileName);

		response.reset();
		response.setContentType("application/vnd.ms-excel;utf-8");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName);

		return response.getOutputStream();

	}

	public static String getFileName(HttpServletRequest request, String fileName)
			throws UnsupportedEncodingException {
		String userAgent = request.getHeader("User-Agent").toLowerCase();

		if (userAgent.indexOf("firefox") > 0) {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		} else {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		return fileName;
	}

}
