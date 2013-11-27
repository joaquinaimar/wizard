package com.wizard.util.file.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.wizard.util.file.FileUtil;

public class PropertiesObject extends Properties {

	private static final long serialVersionUID = 326346556486683129L;

	public PropertiesObject() {
		super();
	}

	public PropertiesObject(final Properties properties) {
		super(properties);
	}

	public PropertiesObject(final InputStream inStream) {
		this();
		load(inStream);
	}

	public PropertiesObject(final File file) {
		this();
		load(file);
	}

	public PropertiesObject(final String path) {
		this();
		load(path);
	}

	public void load(final InputStream inStream) {
		try {
			super.load(inStream);
		} catch (IOException e) {
		}
	}

	public void load(final File file) {
		load(FileUtil.getInputStream(file));
	}

	public void load(final String path) {
		load(FileUtil.getInputStream(path));
	}

	public String get(final String key) {
		return super.getProperty(key);
	}

	public String get(final String key, final String defaultValue) {
		return super.getProperty(key, defaultValue);
	}

}
