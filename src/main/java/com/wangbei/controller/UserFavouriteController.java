package com.wangbei.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.entity.UserFavourite;
import com.wangbei.pojo.Response;
import com.wangbei.pojo.UserFavouriteGroupInfo;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.service.UserFavouriteService;
import com.wangbei.util.enums.FavouriteTypeEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户收藏 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/userFavourite")
@Api(description = "用户收藏接口列表")
public class UserFavouriteController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public UserFavouriteService userFavouriteService;

	@PostMapping("/")
	@ApiOperation(value = "添加收藏", notes = "其中type传值说明如下：1其他，2资讯，3故事，4入门，5经书，6佛音，7养生")
	public Response<UserFavourite> addition(int type, int resourceId) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(userFavouriteService.addUserFavourite(authUserDetails.getUserId(),
				FavouriteTypeEnum.getByIndex(type), resourceId));
	}

	@PostMapping("/cancelByResourceId")
	@ApiOperation(value = "根据资源id取消收藏", notes = "其中type传值说明如下：1其他，2资讯，3故事，4入门，5经书，6佛音，7养生")
	public Response<String> cancelByResourceId(int type, int resourceId) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		userFavouriteService.cancelByResourceId(authUserDetails.getUserId(), FavouriteTypeEnum.getByIndex(type),
				resourceId);
		return new Response<>("取消收藏成功");
	}

	@PostMapping("/cancelById")
	@ApiOperation(value = "根据收藏id取消收藏")
	public Response<String> cancelById(int id) {
		userFavouriteService.deleteUserFavourite(id);
		return new Response<String>("取消收藏成功");
	}

	@GetMapping("/getAllLimit")
	@ApiOperation(value = "获取首页全部收藏列表（有数量限制）")
	public Response<List<UserFavouriteGroupInfo>> getAllLimit() {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(userFavouriteService.getAllUserFavouritesLimit(authUserDetails.getUserId(), 10));
	}

	@GetMapping("/getByType")
	@ApiOperation(value = "根据收藏类型获取用户收藏列表", notes = "其中type传值说明如下：1其他，2资讯，3故事，4入门，5经书，6佛音，7养生")
	public Response<Page<UserFavourite>> getByType(int type, int page, int limit) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(userFavouriteService.getUserFavouritesByType(authUserDetails.getUserId(),
				FavouriteTypeEnum.getByIndex(type), page, limit));
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取所有用户收藏列表")
	public Response<List<UserFavourite>> list() {
		return new Response<>(userFavouriteService.list());
	}

}
