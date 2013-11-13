package com.wizard.web.basic.io.extjs;

import com.wizard.web.basic.io.PageRequest;

public class ExtPageRequest extends PageRequest {

	public int getPageNumber() {
		return start / limit;
	}

	public int getPageSize() {
		return limit;
	}
}
