package com.wangbei.controller.userbehavior;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.entity.UserSetting;
import com.wangbei.pojo.Response;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.service.UserSettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户设置 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/userSetting")
@Api(description = "用户设置接口列表")
public class UserSettingController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public UserSettingService userSettingService;

	@GetMapping("/page")
	@ApiOperation(value = "获取用户设置分页数据")
	public Response<Page<UserSetting>> userSettings(int page, int limit) {
		return new Response<>((Page<UserSetting>) userSettingService.userSettings(page, limit));
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取用户设置列表")
	public Response<List<UserSetting>> list() {
		return new Response<>(userSettingService.list());
	}

	@GetMapping("/getMySetting")
	@ApiOperation(value = "获取我的设置")
	public Response<UserSetting> getMySetting() {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(userSettingService.getMySetting(authUserDetails.getUserId()));
	}

	@PostMapping("/changeMySetting")
	@ApiOperation(value = "修改我的设置")
	public Response<UserSetting> changeMySetting(@RequestParam(required = false) Boolean buddhistFestivalRemind,
			@RequestParam(required = false) Boolean feastDayRemind,
			@RequestParam(required = false) Boolean dayVenerateBuddhaRemind,
			@RequestParam(required = false) Boolean sutraRemind, @RequestParam(required = false) Boolean musicRemind,
			@RequestParam(required = false) Boolean appVersionUpdate,
			@RequestParam(required = false) Boolean informationRemind,
			@RequestParam(required = false) Boolean appVoiceRemind,
			@RequestParam(required = false) Boolean appShockRemind) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(userSettingService.changeMySetting(authUserDetails.getUserId(), buddhistFestivalRemind,
				feastDayRemind, dayVenerateBuddhaRemind, sutraRemind, musicRemind, appVersionUpdate, informationRemind,
				appVoiceRemind, appShockRemind));
	}

}
