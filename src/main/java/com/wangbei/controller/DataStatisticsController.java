package com.wangbei.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.pojo.DataResponse;
import com.wangbei.pojo.Response;
import com.wangbei.pojo.StaChargeInfo;
import com.wangbei.pojo.StaOrderInfo;
import com.wangbei.pojo.StaUserInfo;
import com.wangbei.service.DataStatisticsService;
import com.wangbei.util.DateTimeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 数据统计 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/dataSta")
@Api(description = "数据统计接口列表")
public class DataStatisticsController {

	@Autowired
	private DataStatisticsService dataStaService;

	/************************************* 首页统计 *********************************/

	@ApiOperation(value = "获取某天充值金额数", notes = "dayStr格式为yyyy-MM-dd")
	@GetMapping("/dayAmountOfCharge")
	public Response<Double> dayAmountOfCharge(@RequestParam(required = false) String dayStr) {
		if (dayStr == null || "".equals(dayStr.trim())) {
			dayStr = DateTimeUtil.sdfDate.format(new Date());
		}
		return new Response<>(dataStaService.dayAmountOfCharge(dayStr));
	}

	@ApiOperation(value = "获取某天新增用户数", notes = "dayStr格式为yyyy-MM-dd")
	@GetMapping("/dayCountOfUser")
	public Response<Integer> dayCountOfUser(@RequestParam(required = false) String dayStr) {
		if (dayStr == null || "".equals(dayStr.trim())) {
			dayStr = DateTimeUtil.sdfDate.format(new Date());
		}
		return new Response<>(dataStaService.dayCountOfUser(dayStr));
	}

	@ApiOperation(value = "统计日成交额（从dayStr往前推）", notes = "dayStr格式为yyyy-MM-dd")
	@GetMapping("/staDayAmountOfCharge")
	public Response<Map<String, Double>> staDayAmountOfCharge(@RequestParam(required = false) String dayStr) {
		if (dayStr == null || "".equals(dayStr.trim())) {
			dayStr = DateTimeUtil.sdfDate.format(new Date());
		}
		return new Response<>(dataStaService.staDayAmountOfCharge(dayStr, 7));
	}

	@ApiOperation(value = "统计周成交额（从dayStr往前推）", notes = "dayStr格式为yyyy-MM-dd")
	@GetMapping("/staWeekAmountOfCharge")
	public Response<Map<String, Double>> staWeekAmountOfCharge(@RequestParam(required = false) String dayStr) {
		if (dayStr == null || "".equals(dayStr.trim())) {
			dayStr = DateTimeUtil.sdfDate.format(new Date());
		}
		return new Response<>(dataStaService.staWeekAmountOfCharge(dayStr, 4));
	}

	@ApiOperation(value = "统计月成交额（从dayStr往前推）", notes = "dayStr格式为yyyy-MM-dd")
	@GetMapping("/staMonthAmountOfCharge")
	public Response<Map<String, Double>> staMonthAmountOfCharge(@RequestParam(required = false) String dayStr) {
		if (dayStr == null || "".equals(dayStr.trim())) {
			dayStr = DateTimeUtil.sdfDate.format(new Date());
		}
		return new Response<>(dataStaService.staMonthAmountOfCharge(dayStr, 4));
	}

	/************************************* 用户信息 *********************************/

	@ApiOperation(value = "用户数据统计查询")
	@GetMapping("/staUserInfo")
	public DataResponse<List<StaUserInfo>> staUserInfo(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) Integer gender, @RequestParam(required = false) String registStartTime,
			@RequestParam(required = false) String registEndTime,
			@RequestParam(required = false) Integer registStageType,
			@RequestParam(required = false) String newestLoginStartTime,
			@RequestParam(required = false) String newestLoginEndTime,
			@RequestParam(required = false) Integer newestLoginStageType) {
		return new DataResponse<>(dataStaService.staUserInfo(keyword, gender, registStartTime, registEndTime,
				registStageType, newestLoginStartTime, newestLoginEndTime, newestLoginStageType));
	}

	/************************************* 充值管理 *********************************/

	@ApiOperation(value = "充值统计查询")
	@GetMapping("/staChargeInfo")
	public DataResponse<List<StaChargeInfo>> staChargeInfo(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) Integer chargeType, @RequestParam(required = false) String registStartTime,
			@RequestParam(required = false) String registEndTime,
			@RequestParam(required = false) Integer registStageType,
			@RequestParam(required = false) String chargeStartTime,
			@RequestParam(required = false) String chargeEndTime,
			@RequestParam(required = false) Integer chargeStageType) {
		return new DataResponse<>(dataStaService.staChargeInfo(keyword, chargeType, registStartTime, registEndTime,
				registStageType, chargeStartTime, chargeEndTime, chargeStageType));
	}

	/************************************* 订单管理 *********************************/

	@ApiOperation(value = "订单统计查询（饼状图）")
	@GetMapping("/staOrderInfo")
	public Response<StaOrderInfo> staOrderInfo(@RequestParam(required = false) String registStartTime,
			@RequestParam(required = false) String registEndTime,
			@RequestParam(required = false) Integer registStageType,
			@RequestParam(required = false) String orderStartTime, @RequestParam(required = false) String orderEndTime,
			@RequestParam(required = false) Integer orderStageType) {
		return new Response<>(dataStaService.staOrderInfo(registStartTime, registEndTime, registStageType,
				orderStartTime, orderEndTime, orderStageType));
	}
	
	@ApiOperation(value = "订单统计查询（折线图）")
	@GetMapping("/staOrderInfoByStage")
	public Response<StaOrderInfo> staOrderInfo(Integer stageType) {
		return new Response<>(dataStaService.staOrderInfo(stageType));
	}

}
