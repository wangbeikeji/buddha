package com.wangbei.util;

import com.google.gson.Gson;

public class JsonUtil {

	public static String beanToJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

}
