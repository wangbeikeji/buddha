package com.wangbei.controller.userbehavior;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.pojo.NewestFreeLife;
import com.wangbei.pojo.NewestMeritDonation;
import com.wangbei.pojo.Response;
import com.wangbei.service.DynamicStatisticsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 动态统计 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/dynamicSta")
@Api(description = "动态统计接口列表")
public class DynamicStatisticsController {
	
	@Autowired
	private DynamicStatisticsService service;

	@GetMapping("/newestFreeLife")
	@ApiOperation(value = "获取最新放生记录")
	public Response<List<NewestFreeLife>> newestFreeLife() {
		return new Response<>(service.newestFreeLife(10));
	}
	
	@GetMapping("/newestMeritDonation")
	@ApiOperation(value = "获取最新功德捐赠记录")
	public Response<List<NewestMeritDonation>> newestMeritDonation() {
		return new Response<>(service.newestMeritDonation(10));
	}

}
