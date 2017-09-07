package com.wangbei.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3002529560870563950L;

	private String code;
	private String message;
	private Object[] params;

	public ServiceException(ExceptionEnum exceptionEnum) {
		this.code = exceptionEnum.getCode();
		this.message = exceptionEnum.getMessage();
	}

	public ServiceException(ExceptionEnum exceptionEnum, Throwable cause) {
		super(cause);
		this.code = exceptionEnum.getCode();
		this.message = exceptionEnum.getMessage();
	}

	public ServiceException(ExceptionEnum exceptionEnum, Object[] params) {
		this.code = exceptionEnum.getCode();
		this.message = String.format(exceptionEnum.getMessage(), params);
	}

	public ServiceException(ExceptionEnum exceptionEnum, Object[] params, Throwable cause) {
		super(cause);
		this.code = exceptionEnum.getCode();
		this.message = String.format(exceptionEnum.getMessage(), params);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

}
