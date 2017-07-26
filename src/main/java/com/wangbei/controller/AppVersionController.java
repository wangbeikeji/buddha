package com.wangbei.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.entity.AppVersion;
import com.wangbei.pojo.Response;
import com.wangbei.service.AppVersionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * App版本 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/appVersion")
@Api(description = "App版本接口列表")
public class AppVersionController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public AppVersionService appVersionService;

	@PostMapping("/")
	@ApiOperation(value = "添加App版本")
	public Response<AppVersion> addition(AppVersion appVersion) {
		return new Response<>(appVersionService.addDataVersion(appVersion));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改App版本")
	public Response<AppVersion> modification(AppVersion appVersion) {
		return new Response<>(appVersionService.modifyDataVersion(appVersion));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除App版本")
	public Response<Integer> delete(@PathVariable Integer id) {
		appVersionService.deleteDataVersion(id);
		return new Response<Integer>(id);
	}

	@GetMapping("/getCurrent")
	@ApiOperation(value = "获取当前App版本")
	public Response<Integer> getCurrent() {
		return new Response<>(appVersionService.getCurrent());
	}
	
	@GetMapping("/isOnline")
	@ApiOperation(value = "是否上线")
	public Response<Boolean> isOnline() {
		return new Response<>(appVersionService.isOnline());
	}

}
