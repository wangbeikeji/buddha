package com.wangbei.exception;

public class ServiceException extends RuntimeException {

	public static final String UNKNOW_EXCEPTION = "1001";
	public static final String MERIT_POOL="1002";
	public static final String FREE_LIFE_EXCEPTION="1003";

	public static final String MENU_ADDITION_EXCEPTION="2001";
	public static final String MENU_MODIFICATION_EXCEPTION = "2002";
	public static final String USER_REGISTER_EXIST_EXCEPTION = "3001";
	public static final String USER_PHONE_NOTEXIST_EXCEPTION = "3002";

	public static final String USER_ADDHEREBY_DUPLICATE_EXCEPTION = "3003";

	public static final String USER_ALREADY_CHECKIN_EXCEPTION = "3004";

	public static final String TOKEN_VALIDATE_EXCEPTION = "5001";



	private String type;
	private String message;
	public ServiceException(String type) {
		super(type);
		this.type = type;
	}

	public ServiceException(String type, Throwable cause) {
		super(type, cause);
		this.type = type;
	}

	//提示错误信息
	public String getMessage() {
		if (ExceptionMap.exceptionMap.containsKey(type)) {
			message = ExceptionMap.exceptionMap.get(type);
		}
		return message;
	}

	public String getType() {
		return type;
	}
}
