package com.wangbei.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.pojo.Response;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.service.JiguangService;
import com.wangbei.util.enums.PushTypeEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户绑定 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/messagePush")
@Api(description = "用户绑定接口列表")
public class MessagePushController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JiguangService jiguangService;

	@GetMapping("/testJiguang")
	@ApiOperation(value = "测试极光推送")
	public Response<String> testJiguang(@RequestParam(required = false) Integer userId) {
		if (userId == null) {
			AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
			userId = authUserDetails.getUserId();
		}
		jiguangService.pushSingleUser(userId);
		return new Response<>("测试极光推送完成");
	}

	@GetMapping("/testJiguangAll")
	@ApiOperation(value = "测试极光推送所有设备")
	public Response<String> testJiguangAll() {
		jiguangService.pushAllDevice();
		return new Response<>("测试极光推送完成");
	}

	@PostMapping("/adminManualPush")
	@ApiOperation(value = "后台管理手动推送")
	public Response<String> adminManualPush(Integer resourceId, @RequestParam(required = false) String title,
			@RequestParam(required = false) String alert, Integer type) {
		jiguangService.adminManualPush(resourceId, title, alert, PushTypeEnum.getByIndex(type));
		return new Response<>("推送成功!");
	}

}
