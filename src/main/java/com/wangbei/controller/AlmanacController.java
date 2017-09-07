package com.wangbei.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.pojo.AlmanacInfo;
import com.wangbei.pojo.Response;
import com.wangbei.service.AlmanacService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 黄历+佛历 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/almanac")
@Api(description = "黄历+佛历接口列表")
public class AlmanacController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public AlmanacService almanacService;

	@PostMapping("/collectData")
	@ApiOperation(value = "采集黄历+佛历数据")
	public Response<String> collectData(int startYear, int endYear) {
		almanacService.collectAlmanacData(startYear, endYear);
		return new Response<>("采集数据成功");
	}
	
	@GetMapping("/getRecentYearData")
	@ApiOperation(value = "获取最近年份的佛历数据")
	public Response<Map<String, AlmanacInfo>> getRecentYearData() {
		return new Response<>(almanacService.getRecentYearData());
	}

}
