package com.wizard.web.basic.io;

import java.util.List;

public class PageResponse<T> {

	private List<T> result = null;

	private int totalCount = 0;

	private int start = 0;

	private int limit = 0;

	public PageResponse(int start, int limit) {
		this.start = start;
		this.limit = limit;
	}

	public PageResponse(PageRequest pageRequest) {
		this(pageRequest.getStart(), pageRequest.getLimit());
	}

	public PageResponse(List<T> result, int start, int limit) {
		this(start, limit);
		if (null == result) {
			return;
		}
		this.result = result;
		this.totalCount = result.size();
	}

	public PageResponse(List<T> result, PageRequest pageRequest) {
		this(result, pageRequest.getStart(), pageRequest.getLimit());
	}

	public List<T> getResult() {
		if (null == result) {
			return null;
		}

		int start = 0;

		if (this.start < 0) {
			start = 0;
		} else if (this.totalCount < this.start) {
			start = this.totalCount;
		} else {
			start = this.start;
		}

		int end = this.start + this.limit;

		if (this.totalCount < end) {
			end = this.totalCount;
		}

		return result.subList(start, end);
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStart() {
		return start;
	}

	public int getLimit() {
		return limit;
	}

}
