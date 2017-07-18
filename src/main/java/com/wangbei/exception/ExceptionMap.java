package com.wangbei.exception;

import java.util.HashMap;
import java.util.Map;

public class ExceptionMap {

	public static Map<String, String> exceptionMap = new HashMap<String, String>();

	static {
		exceptionMap.put(ServiceException.UNKNOW_EXCEPTION, "服务器未知异常");
		exceptionMap.put(ServiceException.MERIT_POOL, "功德值不足");
		exceptionMap.put(ServiceException.MENU_ADDITION_EXCEPTION, "菜单添加异常");
		exceptionMap.put(ServiceException.MENU_MODIFICATION_EXCEPTION, "菜单修改异常");

		exceptionMap.put(ServiceException.USER_REGISTER_EXIST_EXCEPTION, "该手机号已被注册");
		exceptionMap.put(ServiceException.USER_REGISTER_EXIST_EXCEPTION, "用户手机号不存在");
		exceptionMap.put(ServiceException.USER_ADDHEREBY_DUPLICATE_EXCEPTION, "不能重复调用初始请佛接口");
		exceptionMap.put(ServiceException.USER_ALREADY_CHECKIN_EXCEPTION, "今天已签到，不能重复签到");

		exceptionMap.put(ServiceException.TOKEN_VALIDATE_EXCEPTION, "token验证失败");
	}
}
