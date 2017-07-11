package com.wangbei.controller;

import com.wangbei.pojo.Response;
import com.wangbei.pojo.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.wangbei.pojo.SendAuthCodeResult;
import com.wangbei.service.SmsService;

/**
 * 短信服务 控制器
 * 
 * @author luomengan
 * 
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

	@Autowired
	private SmsService smsService;

	@PostMapping(value = "/sendAuthCode")
	public Response<SendAuthCodeResult> sendAuthCode(String phone) {
		return new Response<>(smsService.sendAuthCode(phone));
	}

}
