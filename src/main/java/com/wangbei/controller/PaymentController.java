package com.wangbei.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.entity.Trade;
import com.wangbei.pojo.Response;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.service.wxpay.WxPayService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 支付 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/payment")
@Api(description = "支付接口列表")
public class PaymentController {

	@Autowired
	private WxPayService wxPayService;

	@PostMapping("/wxUnifiedOrder")
	@ApiOperation(value = "微信支付统一下单接口（type：0充值 7放生 8功德）")
	public Response<Map<String, Object>> wxUnifiedOrder(int type, int meritValue, int totalFee, String goodsDesc) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(
				wxPayService.unifiedOrder(authUserDetails.getUserId(), type, meritValue, totalFee, goodsDesc));
	}

	@PostMapping("/wxOrderQuery")
	@ApiOperation(value = "微信支付查询订单接口")
	public Response<Trade> wxOrderQuery(String tradeNo) {
		return new Response<>(wxPayService.orderQuery(tradeNo));
	}
	
	@GetMapping("/wxNotify")
	@ApiOperation(value = "微信支付回调通知")
	public String wxNotify(HttpServletRequest request) {
		return wxPayService.receiveNotify(request);
	}
	
}
