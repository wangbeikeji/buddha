package com.wangbei.controller.userbehavior;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.entity.UserMessage;
import com.wangbei.pojo.Response;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.service.UserMessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户消息 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/userMessage")
@Api(description = "用户消息接口列表")
public class UserMessageController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public UserMessageService userMessageService;

	@PostMapping("/delete")
	@ApiOperation(value = "删除用户消息")
	public Response<Integer> delete(Integer id) {
		userMessageService.deleteUserMessage(id);
		return new Response<Integer>(id);
	}
	
	@PostMapping("/deleteUserAll")
	@ApiOperation(value = "删除当前用户所有消息")
	public Response<String> deleteUserAll() {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		userMessageService.deleteUserAll(authUserDetails.getUserId());
		return new Response<String>("删除成功");
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取所有用户消息分页数据")
	public Response<Page<UserMessage>> userMessages(int page, int limit) {
		return new Response<>((Page<UserMessage>) userMessageService.userMessages(page, limit));
	}

	@GetMapping("/currentUserPage")
	@ApiOperation(value = "获取当前用户消息分页数据")
	public Response<Page<UserMessage>> currentUserPage(int page, int limit) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(
				(Page<UserMessage>) userMessageService.currentUserPage(authUserDetails.getUserId(), page, limit));
	}

}
