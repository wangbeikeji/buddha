package com.wangbei.controller.userbehavior;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.pojo.CheckinRankingInfo;
import com.wangbei.pojo.ConsumeMeritRanking;
import com.wangbei.pojo.Response;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.service.RankingService;
import com.wangbei.util.enums.UserLikeTypeEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 排行榜 控制器
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/ranking")
@Api(description = "排行榜接口列表")
public class RankingController {

	@Autowired
	private RankingService rankingService;

	/*********************** 签到排行 ***********************/

	@GetMapping("/checkinRanking")
	@ApiOperation(value = "签到排行榜", notes = "1:日排行榜， 2:月排行榜，3:总排行榜")
	public Response<Page<CheckinRankingInfo>> checkinRanking(int rankType, int page, int limit) {
		if (page * limit >= 100) {
			Page<CheckinRankingInfo> pageData = new PageImpl<>(new ArrayList<CheckinRankingInfo>(),
					new PageRequest(page, limit), 0);
			return new Response<>(pageData);
		}
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		if (rankType == 1) {
			return new Response<>(rankingService.dayCheckinRanking(authUserDetails.getUserId(), page, limit));
		} else if (rankType == 2) {
			return new Response<>(rankingService.monthCheckinRanking(authUserDetails.getUserId(), page, limit));
		} else if (rankType == 3) {
			return new Response<>(rankingService.totalCheckinRanking(authUserDetails.getUserId(), page, limit));
		} else {
			return new Response<>(rankingService.totalCheckinRanking(authUserDetails.getUserId(), page, limit));
		}
	}

	@PostMapping("/userLikeCheckin")
	@ApiOperation(value = "签到排行点赞", notes = "1:签到日排行点赞，2:签到月排行点赞，3:签到总排行点赞")
	public Response<String> userLike(Integer checkinUserId, Integer userLikeType) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		rankingService.userLike(authUserDetails.getUserId(), checkinUserId,
				UserLikeTypeEnum.getByIndex(userLikeType + 1));
		return new Response<>("点赞成功");
	}

	@GetMapping("/currentUserCheckinRanking")
	@ApiOperation(value = "当前用户签到排名")
	public Response<CheckinRankingInfo> currentUserCheckinRanking() {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(rankingService.currentUserCheckinRanking(authUserDetails.getUserId()));
	}

	/*********************** 功德排行 ***********************/

	@GetMapping("/consumeMeritRanking")
	@ApiOperation(value = "用户消费功德排行榜", notes = "1:日排行榜， 2:月排行榜，3:总排行榜")
	public Response<Page<ConsumeMeritRanking>> consumeMeritRanking(int rankType, int page, int limit) {
		if (page * limit >= 100) {
			Page<ConsumeMeritRanking> pageData = new PageImpl<>(new ArrayList<ConsumeMeritRanking>(),
					new PageRequest(page, limit), 0);
			return new Response<>(pageData);
		}
		if (rankType == 1) {
			return new Response<>(rankingService.dayConsumeMeritRanking(page, limit));
		} else if (rankType == 2) {
			return new Response<>(rankingService.monthConsumeMeritRanking(page, limit));
		} else if (rankType == 3) {
			return new Response<>(rankingService.totalConsumeMeritRanking(page, limit));
		} else {
			return new Response<>(rankingService.totalConsumeMeritRanking(page, limit));
		}
	}

	@GetMapping("/currentUserConsumeMeritRanking")
	@ApiOperation(value = "当前用户消费功德排名")
	public Response<ConsumeMeritRanking> currentUserConsumeMeritRanking() {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(rankingService.currentUserConsumeMeritRanking(authUserDetails.getUserId()));
	}

}
