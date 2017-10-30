package com.wangbei.controller.userbehavior;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.entity.MaterialResource;
import com.wangbei.entity.UserMeditationRecord;
import com.wangbei.pojo.Response;
import com.wangbei.pojo.UserDayMeditationRecord;
import com.wangbei.pojo.UserMeditationSettingInfo;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.service.MaterialResourceService;
import com.wangbei.service.MeditationService;
import com.wangbei.util.enums.MaterialResourceTypeEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 禅修 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/meditation")
@Api(description = "禅修接口列表")
public class MeditationController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MaterialResourceService materialResourceService;

	@Autowired
	private MeditationService meditationService;

	@GetMapping("/getBgMusicList")
	@ApiOperation(value = "获取背景音乐列表")
	public Response<List<MaterialResource>> getBgMusicList() {
		return new Response<>(materialResourceService.listByType(MaterialResourceTypeEnum.MeditationBgMusic));
	}

	@GetMapping("/getUserSetting")
	@ApiOperation(value = "获取当前用户禅修设置")
	public Response<UserMeditationSettingInfo> getUserSetting() {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(meditationService.getUserSetting(authUserDetails.getUserId()));
	}

	@PostMapping("/modifyUserSetting")
	@ApiOperation(value = "修改当前用户禅修设置")
	public Response<UserMeditationSettingInfo> modifyUserSetting(Integer materialResourceId) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(meditationService.modifyUserSetting(authUserDetails.getUserId(), materialResourceId));
	}

	@PostMapping("/addUserMeditationRecord")
	@ApiOperation(value = "添加当前用户禅修记录", notes = "时间格式为yyyy-MM-dd HH:mm:ss")
	public Response<UserMeditationRecord> addUserMeditationRecord(String startTime, String endTime) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(
				meditationService.addUserMeditationRecord(authUserDetails.getUserId(), startTime, endTime));
	}

	@GetMapping("/staUserDayMeditationRecord")
	@ApiOperation(value = "统计用户日禅修记录", notes = "时间格式为yyyy-MM-dd")
	public Response<UserDayMeditationRecord> staUserDayMeditationRecord(String day) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(meditationService.staUserDayMeditationRecord(authUserDetails.getUserId(), day));
	}

}
