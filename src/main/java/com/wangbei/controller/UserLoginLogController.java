package com.wangbei.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.entity.UserLoginLog;
import com.wangbei.pojo.DataResponse;
import com.wangbei.pojo.Response;
import com.wangbei.service.UserLoginLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户登录日志 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/userLoginLog")
@Api(description = "用户登录日志接口列表")
public class UserLoginLogController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public UserLoginLogService userLoginLogService;

	@GetMapping("/page")
	@ApiOperation(value = "获取用户登录日志分页数据")
	public Response<Page<UserLoginLog>> userLoginLogs(int page, int limit) {
		return new Response<>((Page<UserLoginLog>) userLoginLogService.userLoginLogs(page, limit));
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取用户登录日志列表")
	public DataResponse<List<UserLoginLog>> list() {
		return new DataResponse<>(userLoginLogService.list());
	}

}
