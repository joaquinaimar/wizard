package com.wizard.j2ee.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class FileVo extends ResponseEntity<byte[]> {

	public FileVo(HttpStatus statusCode) {
		super(statusCode);
	}

	public FileVo(byte[] body, HttpStatus statusCode) {
		super(body, statusCode);
	}

	public FileVo(MultiValueMap<String, String> headers, HttpStatus statusCode) {
		super(headers, statusCode);
	}

	public FileVo(byte[] body, MultiValueMap<String, String> headers,
			HttpStatus statusCode) {
		super(body, headers, statusCode);
	}

}
