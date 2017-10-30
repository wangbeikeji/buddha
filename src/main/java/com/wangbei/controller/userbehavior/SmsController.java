package com.wangbei.controller.userbehavior;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.pojo.Response;
import com.wangbei.pojo.SendAuthCodeResult;
import com.wangbei.service.SmsService;
import com.wangbei.util.enums.AuthCodeTypeEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 短信服务 控制器
 * 
 * @author luomengan
 * 
 */
@RestController
@RequestMapping("/sms")
@Api(description = "短信服务接口列表")
public class SmsController {

	@Autowired
	private SmsService smsService;

	@PostMapping(value = "/sendAuthCode")
	@ApiOperation(value = "发送短信验证码")
	public Response<SendAuthCodeResult> sendAuthCode(String phone) {
		return new Response<>(smsService.sendAuthCode(phone));
	}
	
	@PostMapping(value = "/sendAuthCodeWithType")
	@ApiOperation(value = "发送短信验证码", notes="type(1:注册,2:重置密码)")
	public Response<SendAuthCodeResult> sendAuthCode(String phone, int type) {
		return new Response<>(smsService.sendAuthCode(phone, AuthCodeTypeEnum.getByIndex(type)));
	}

}
