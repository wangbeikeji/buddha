package com.wangbei.controller.userbehavior;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.entity.UserBuddhistNote;
import com.wangbei.pojo.Response;
import com.wangbei.pojo.UserBuddhistNoteInfo;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.service.UserBuddhistNoteService;
import com.wangbei.util.enums.BuddhistNoteTypeEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户佛录 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/userBuddhistNote")
@Api(description = "用户佛录接口列表")
public class UserBuddhistNoteController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public UserBuddhistNoteService userBuddhistNoteService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取用户佛录")
	public Response<UserBuddhistNote> fetchById(@PathVariable Integer id) {
		return new Response<>(userBuddhistNoteService.getUserBuddhistNoteInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加用户佛录", notes = "其中type传值说明如下：1祈福录，2行善录，3忏悔录")
	public Response<UserBuddhistNote> addition(String buddha, String note, int type, boolean isPublic) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(userBuddhistNoteService.addUserBuddhistNote(authUserDetails.getUserId(), buddha, note,
				BuddhistNoteTypeEnum.getByIndex(type), isPublic));
	}

	@PostMapping("/delete")
	@ApiOperation(value = "删除用户佛录")
	public Response<Integer> delete(Integer id) {
		userBuddhistNoteService.deleteUserBuddhistNote(id);
		return new Response<Integer>(id);
	}

	@GetMapping("/pagePublicByType")
	@ApiOperation(value = "根据类型获取公开的用户佛录分页数据", notes = "其中type传值说明如下：1祈福录，2行善录，3忏悔录")
	public Response<Page<UserBuddhistNoteInfo>> pagePublicByType(int type, int page, int limit) {
		return new Response<>(userBuddhistNoteService.pagePublicUserBuddhistNoteByType(SecurityAuthService.getUserId(),
				BuddhistNoteTypeEnum.getByIndex(type), page, limit));
	}

	@GetMapping("/pageUserByType")
	@ApiOperation(value = "获取用户佛录列表", notes = "其中type传值说明如下：1祈福录，2行善录，3忏悔录")
	public Response<Page<UserBuddhistNoteInfo>> pageUserByType(int type, int page, int limit) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(userBuddhistNoteService.pageUserBuddhistNoteByType(authUserDetails.getUserId(),
				BuddhistNoteTypeEnum.getByIndex(type), page, limit));
	}

	@PostMapping("/userLike")
	@ApiOperation(value = "用户点赞佛录", notes = "其中type传值说明如下：1祈福录，2行善录，3忏悔录")
	public Response<Integer> userLike(Integer resourceId, int type) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(userBuddhistNoteService.userLike(authUserDetails.getUserId(), resourceId));
	}

}
