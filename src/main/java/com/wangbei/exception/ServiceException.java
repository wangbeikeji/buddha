package com.wangbei.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3002529560870563950L;

	/**
	 * 请按照不同业务添加不同的异常并保持{@link ExceptionMap} 异常解释顺序
 	 */

	//系统相关
	public static final String UNKNOW_EXCEPTION = "1001";
	public static final String TOKEN_VALIDATE_EXCEPTION = "1003";

	//交易
	public static final String FREE_LIFE_EXCEPTION="2001";
	public static final String TRADENO_NOTEXIST_EXCEPTION = "2002";
	public static final String TRADE_NOTCOMPLETED_EXCEPTION = "2003";
	public static final String CHARGETYPE_NOTMATCH_EXCEPTION = "2004";
	public static final String MERIT_POOL="2005";
	public static final String TRADE_ERROR_EXCEPTION = "2006";

	//资源
	public static final String MENU_ADDITION_EXCEPTION="3001";
	public static final String MENU_MODIFICATION_EXCEPTION = "3002";
	//用户
	public static final String USER_REGISTER_EXIST_EXCEPTION = "4001";
	public static final String USER_PHONE_NOTEXIST_EXCEPTION = "4002";
	public static final String USER_ADDHEREBY_DUPLICATE_EXCEPTION = "4003";
	public static final String USER_ALREADY_CHECKIN_EXCEPTION = "4004";
	public static final String USER_ACCOUNT_NOT_FOUND_EXCEPTION="4005";


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
