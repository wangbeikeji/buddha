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

import com.wangbei.entity.UserBinding;
import com.wangbei.pojo.Response;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.service.JiguangService;
import com.wangbei.service.UserBindingService;
import com.wangbei.util.enums.BindingTypeEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户绑定 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/userBinding")
@Api(description = "用户绑定接口列表")
public class UserBindingController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserBindingService userBindingService;

	@Autowired
	private JiguangService jiguangService;

	@GetMapping("/page")
	@ApiOperation(value = "获取用户绑定分页数据")
	public Response<Page<UserBinding>> userBindings(int page, int limit) {
		return new Response<>((Page<UserBinding>) userBindingService.userBindings(page, limit));
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取用户绑定列表")
	public Response<List<UserBinding>> list() {
		return new Response<>(userBindingService.list());
	}

	@GetMapping("/testJiguang")
	@ApiOperation(value = "测试极光推送")
	public Response<String> testJiguang(@RequestParam(required = false) Integer userId) {
		if (userId == null) {
			AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
			userId = authUserDetails.getUserId();
		}
		jiguangService.pushSingleUser(userId);
		return new Response<>("测试极光推送完成");
	}

	@GetMapping("/testJiguangAll")
	@ApiOperation(value = "测试极光推送所有设备")
	public Response<String> testJiguangAll() {
		jiguangService.pushAllDevice();
		return new Response<>("测试极光推送完成");
	}
	
	@PostMapping("/bindJiguang")
	@ApiOperation(value = "绑定极光设备id")
	public Response<UserBinding> bindJiguang(String registrationId) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(userBindingService.bindAccount(authUserDetails.getUserId(), BindingTypeEnum.JiguangPush,
				registrationId));
	}

}
