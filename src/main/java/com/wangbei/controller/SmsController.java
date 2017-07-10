package com.wangbei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping("/sendVerificationCode")
	public SendAuthCodeResult sendVerificationCode(@RequestBody String[] phoneNumbers) {
		return smsService.sendAuthCode(phoneNumbers);
	}

}
