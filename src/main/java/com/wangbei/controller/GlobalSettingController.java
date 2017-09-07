package com.wangbei.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.entity.GlobalSetting;
import com.wangbei.pojo.Response;
import com.wangbei.service.GlobalSettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 全局设置 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/globalSetting")
@Api(description = "全局设置接口列表")
public class GlobalSettingController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public GlobalSettingService globalSettingService;

	@GetMapping("/getCurrentSetting")
	@ApiOperation(value = "获取全局设置")
	public Response<GlobalSetting> getCurrentSetting() {
		return new Response<>(globalSettingService.getCurrentSetting());
	}
	
	@PostMapping("/updateBuddhistFestivalRemindTime")
	@ApiOperation(value = "修改佛教节日提醒时间")
	public Response<GlobalSetting> updateBuddhistFestivalRemindTime(String buddhistFestivalRemindTime) {
		return new Response<>(
				globalSettingService.updateBuddhistFestivalRemindTime(buddhistFestivalRemindTime));
	}
	
	@PostMapping("/updateFeastDayRemindTime")
	@ApiOperation(value = "修改斋日提醒时间")
	public Response<GlobalSetting> updateFeastDayRemindTime(String feastDayRemindTime) {
		return new Response<>(
				globalSettingService.updateFeastDayRemindTime(feastDayRemindTime));
	}

}
