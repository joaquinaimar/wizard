package com.wizard.util.file.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.wizard.util.common.CommonUtil;
import com.wizard.util.file.FileUtil;

public class CsvObject {

	private String path = null;

	private char separative = ',';

	private Charset charset = Charset.defaultCharset();

	private CsvReader reader = null;

	private CsvWriter writer = null;

	public CsvObject(String path, char separative, Charset charset) {
		this.path = path;
		this.separative = separative;
		this.charset = charset;
		this.load();
		this.writer = new CsvWriter(path, separative, charset);
	}

	public CsvObject(String path, char separative) {
		this.path = path;
		this.separative = separative;
		this.load();
		this.writer = new CsvWriter(path, separative, charset);
	}

	public CsvObject(String path) {
		this.path = path;
		this.load();
		this.writer = new CsvWriter(path, separative, charset);
	}

	public void load() {
		if (FileUtil.exists(path))
			try {
				this.reader = new CsvReader(path, separative, charset);
			} catch (FileNotFoundException e) {
			}
	}

	public String[] readRow() {
		try {
			if (!CommonUtil.isNull(this.reader) && this.reader.readRecord())
				return this.reader.getValues();
		} catch (IOException e) {
		}
		return null;
	}

	public Collection<String[]> readAllData() {
		if (!CommonUtil.isNull(this.reader)) {
			this.load();
			List<String[]> data = new ArrayList<String[]>();
			String[] row = null;
			while (!CommonUtil.isNull(row = readRow()))
				data.add(row);
			return data;
		}
		return null;
	}

	public void writeRow(String[] data) {
		try {
			this.writer.writeRecord(data);
		} catch (IOException e) {
		}
	}

	public void close() {
		if (!CommonUtil.isNull(this.reader))
			this.reader.close();
		this.writer.close();
	}
}
