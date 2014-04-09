package com.wizard.j2ee.dao;

public class PageRequest {

	public static final int DEFAULT_START = 0;
	public static final int DEFAULT_LIMIT = 20;

	protected int start = 0;
	protected int limit = 0;

	protected String[] sortCols = null;

	protected String[] sortTypes = null;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		if (start < 0) {
			this.start = DEFAULT_START;
		} else {
			this.start = start;
		}
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		if (limit <= 0) {
			this.limit = DEFAULT_LIMIT;
		} else {
			this.limit = limit;
		}
	}

	public int getEnd() {
		return start + limit;
	}

	public String[] getSortCols() {
		return sortCols;
	}

	public void setSortCols(String[] sortCols) {
		this.sortCols = sortCols;
	}

	public String[] getSortTypes() {
		return sortTypes;
	}

	public void setSortTypes(String[] sortTypes) {
		this.sortTypes = sortTypes;
	}

}
