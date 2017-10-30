package com.wangbei.controller.userbehavior;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.pojo.AddOfferingsResultInfo;
import com.wangbei.pojo.HerebyInfo;
import com.wangbei.pojo.Response;
import com.wangbei.pojo.RuneWithBegInfo;
import com.wangbei.pojo.WorshipBuddhaInfo;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.service.WorshipBuddhaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 拜佛 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/worshipBuddha")
@Api(description = "拜佛相关接口列表")
public class WorshipBuddhaController {

	@Autowired
	private WorshipBuddhaService service;

	@ApiOperation(value = "请佛2.0（一个用户可以供多个佛）", notes = "添加新佛newJossId为大于0的值，oldJossId为0；替换佛newJossId和oldJossId都为大于0的值；删除佛newJossId为0，oldJossId为大约0的值。")
	@PostMapping("/pleaseBuddha")
	public Response<HerebyInfo> pleaseBuddha(Integer newJossId, Integer oldJossId) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(service.pleaseBuddha(authUserDetails.getUserId(), newJossId, oldJossId));
	}

	@ApiOperation(value = "获取用户拜佛信息，包括佛、花、香、果、符文")
	@GetMapping("/getWorshipBuddhaInfo")
	public Response<List<WorshipBuddhaInfo>> getWorshipBuddhaInfo() {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(service.getWorshipBuddhaInfo(authUserDetails.getUserId()));
	}

	@ApiOperation(value = "添加供品")
	@PostMapping("/addOfferings")
	public Response<AddOfferingsResultInfo> addOfferings(Integer jossId, Integer offeringsId) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(service.addOfferings(authUserDetails.getUserId(), jossId, offeringsId));
	}

	@ApiOperation(value = "请符", notes = "isSelf为true表示为自己请符，为false表示为他人请符。otherGender为0表示男，为1表示女。")
	@PostMapping("/pleaseRune")
	public Response<RuneWithBegInfo> pleaseRune(Integer jossId, Integer runeId,
			@RequestParam(defaultValue = "1") boolean isSelf, @RequestParam(required = false) String otherName,
			@RequestParam(required = false) Integer otherGender, @RequestParam(required = false) String otherBirthday) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(service.pleaseRune(authUserDetails.getUserId(), jossId, runeId, isSelf, otherName,
				otherGender, otherBirthday));
	}

}
