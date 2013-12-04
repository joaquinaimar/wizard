package com.wizard.util.file.resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

import com.wizard.util.common.StringUtil;
import com.wizard.util.file.FileUtil;

public class IniObject {

	private final String COMMENT_POUND = "#";

	private final String COMMENT_SEMICOLON = ";";

	private final String SECTION_PREFIX = "[";

	private final String SECTION_SUFFIX = "]";

	private final String EQUAL = "=";

	private Map<String, Map<String, String>> ini = null;

	public IniObject(final FileInputStream in) {
		load(in);
	}

	public IniObject(final File file) {
		load(file);
	}

	public IniObject(final String path) {
		load(path);
	}

	public void load(final FileInputStream in) {
		this.ini = new TreeMap<String, Map<String, String>>();
		Map<String, String> section = new TreeMap<String, String>();
		String sectionName = StringUtil.BLANK;
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String temp = null;
		String[] temps = null;
		try {
			while (null != (temp = br.readLine())) {
				temp = StringUtil.trim(temp);
				if (!StringUtil.isBlank(temp)
						&& !temp.startsWith(COMMENT_POUND)
						&& !temp.startsWith(COMMENT_SEMICOLON))
					if (temp.startsWith(SECTION_PREFIX)
							&& temp.endsWith(SECTION_SUFFIX)) {
						this.ini.put(sectionName, section);
						sectionName = temp.substring(1, temp.length() - 2);
						section = new TreeMap<String, String>();
					} else if (temp.contains(EQUAL)) {
						temps = temp.split(EQUAL, 2);
						section.put(temps[0], temps[1]);
					}
			}
			this.ini.put(sectionName, section);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void load(final File file) {
		load(FileUtil.getInputStream(file));
	}

	public void load(final String path) {
		load(FileUtil.getInputStream(path));
	}
}
