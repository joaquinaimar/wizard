package com.wizard.ocr.bean;

public class Response<T> {

	private boolean success = false;

	private String message = null;

	private T data = null;

	public Response(boolean success) {
		this.success = success;
	}

	public Response(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public Response(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
