package com.wangbei.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.pojo.Response;

import io.swagger.annotations.Api;

/**
 * 
 * http错误码 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/httpError")
@Api(description = "用户相关接口列表")
public class HttpErrorController {

	@RequestMapping("/403")
	public Response<String> httpError403() {
		return new Response<String>("403", null, "权限不足!");
	}

	@RequestMapping("/404")
	public Response<String> httpError404(HttpServletRequest request) {
		return new Response<String>("404", null, "无效请求!");
	}

}
