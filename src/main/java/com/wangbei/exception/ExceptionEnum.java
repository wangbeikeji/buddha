package com.wangbei.exception;

public enum ExceptionEnum {

	// 系统相关
	UNKNOW_EXCEPTION("1001", "服务器未知异常：%s"),
	TOKEN_VALIDATE_EXCEPTION("1002", "token验证失败"),
	VALIDATECODE_REPEAD_EXCEPTION("1003", "请勿重复发送验证码"),
	VALIDATECODE_CHECK_EXCEPTION("1004", "验证码校检失败"),
	
	// 交易
	TRADENO_NOTEXIST_EXCEPTION("2001", "交易流水号不存在"),
	TRADE_NOTCOMPLETED_EXCEPTION("2002", "交易尚未完成，不能执行该操作"),
	USER_NOTMATCH_EXCEPTION("2003", "当前用户信息不匹配"),
	MERIT_POOL("2004", "功德值不足"),
	TRADE_ERROR_EXCEPTION("2005", "交易创建失败"),
	CHARGETYPE_NOTMATCH_EXCEPTION("2006", "充值类型不匹配"),
	
	// 资源
	MENU_ADDITION_EXCEPTION("3001", "菜单添加异常"),
	MENU_MODIFICATION_EXCEPTION("3002", "菜单修改异常"),
	
	// 用户
	USER_REGISTER_EXIST_EXCEPTION("4001", "该手机号已被注册"),
	USER_PHONE_NOTEXIST_EXCEPTION("4002", "用户手机号不存在"),
	USER_ADDHEREBY_DUPLICATE_EXCEPTION("4003", "不能重复调用初始请佛接口"),
	USER_ALREADY_CHECKIN_EXCEPTION("4004", "今天已签到，不能重复签到"),
	USER_ACCOUNT_NOT_FOUND_EXCEPTION("4005", "用户账户异常"),
	PHONEAUTHCODE_SENDFAILED_EXCEPTION("4006", "短信验证码发送失败"),
	LOGIN_ACCOUNT_OCCUPY_EXCEPTION("4007", "登陆账号已被占用"),
	USERNAME_OR_PASSWORD_ERROR_EXCEPTION("4008", "用户名或者密码错误"),
	USER_PHONE_CANNOT_BENULL_EXCEPTION("4009", "注册手机号不能为空"),
	USER_PHONE_INVALID_EXCEPTION("4010", "发送失败，请检查手机号码是否有效或者稍后再试"),
	
	// 请佛、请符、求签、供品、佛音、点灯
	JOSSID_INVALID_EXCEPTION("6001", "佛像ID无效"),
	BUDDHISTMUSICCATEGORY_NOTEXIST_EXCEPTION("6002", "佛音类别不存在"),
	BUDDHISTTOPIC_ISOVER_EXCEPTION("6003", "共修主题已结束"),
	ALREADY_LIGHTLAMP_EXCEPTION("6004", "已经点灯且灯油尚未燃尽，不能进行点灯操作"),
	ALREADY_LIKE_EXCEPTION("6005", "已点赞，不能重复点赞"),
	
	// 后台管理
	DATE_FORMAT_ERROR_EXCEPTION("7001", "日期格式错误"),
	PARSE_STRING_TO_DATE_EXCEPTION("7002", "解析时间异常");

	private ExceptionEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	private String code;

	private String message;

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

}
