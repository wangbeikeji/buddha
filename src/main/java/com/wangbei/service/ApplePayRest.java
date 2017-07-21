package com.wangbei.service;

import org.springframework.stereotype.Component;

import com.wangbei.dao.impl.http.HttpRest;
import com.wangbei.pojo.ApplePayResponse;

@Component
public class ApplePayRest extends HttpRest<Object> {

	public static String validateUrl = "https://sandbox.itunes.apple.com/verifyReceipt";
	// public static String validateUrl = "https://buy.itunes.apple.com/verifyReceipt";

	public ApplePayResponse validate(String receiptData) {
		// System.out.println(receiptData);
		// String str = HttpClientUtil.sendPost(validateUrl, "{\"receipt-data\": \"" + receiptData + "\"}");
		// System.out.println("str:" + str);
		return null;
	}

}
