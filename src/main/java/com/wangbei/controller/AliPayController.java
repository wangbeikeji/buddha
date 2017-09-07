package com.wangbei.controller;

import com.alipay.api.AlipayApiException;
import com.wangbei.entity.Orders;
import com.wangbei.pojo.Response;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.service.AliPayService;
import com.wangbei.util.enums.OrderStatusEnum;
import com.wangbei.util.enums.TradeTypeEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yuyidi 2017-07-25 11:15:08
 * @class com.wangbei.controller.PaymentController
 * @description 支付控制器
 */
@RestController
@RequestMapping("/alipay")
@Api(description = "阿里支付接口列表")
public class AliPayController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AliPayService paymentService;

    /**
     * @param type   交易的对象类型  0 充值,7 放生,8 功德箱随喜
     * @param amount
     * @return com.wangbei.pojo.Response<java.lang.String>
     * @author yuyidi 2017-07-28 15:00:32
     * @method payment
     * @description
     */
    @ApiOperation(value = "支付宝APP支付（type：0充值 7放生 8功德）")
    @PostMapping("/payment")
    public Response<String> payment(int type, Double amount) {
        AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
        Integer user = authUserDetails.getUserId();
        return new Response<>(paymentService.pay(user, TradeTypeEnum.getByIndex(type), amount));
    }
    
    @ApiOperation(value = "支付宝H5支付（type：0充值 7放生 8功德）")
    @PostMapping("/h5Payment")
    public Response<String> h5Payment(int type, Double amount) {
        AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
        Integer user = authUserDetails.getUserId();
        return new Response<>(paymentService.h5Pay(user, TradeTypeEnum.getByIndex(type), amount));
    }

    /**
     * @param request
     * @return java.lang.String
     * @author yuyidi 2017-07-27 14:28:27
     * @method callback
     * @description 支付宝服务器异步通知支付结果
     */
    @SuppressWarnings("rawtypes")
	@PostMapping("/callback")
    public String callback(HttpServletRequest request) throws AlipayApiException {
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return paymentService.callback(params);
    }

    /**
     * @param
     * @return com.wangbei.pojo.Response<java.lang.String>
     * @author yuyidi 2017-07-25 15:11:03
     * @method sync
     * @description 客户端同步支付结果返回，服务器端验签并解析支付结果，并返回最终支付结果给客户端
     */
    @PostMapping("/sync")
    public Response<String> sync(String orderNo) throws AlipayApiException {
        String result = "fail";
        //判断交易状态
//        if (AlipayResultStatus.getByIndex(paymentInfo.getResultStatus()).equals(AlipayResultStatus
//                .SUCCESS)) {
        //若客户端交易成功 获取商户订单并验证订单状态
//            TradePayResponse alipayTradeAppPayResponse = paymentInfo.getResult().getAlipayTradeAppPayResponse();
//            String orderNo = alipayTradeAppPayResponse.getOutTradeNo();
//            String aliOrderNo = alipayTradeAppPayResponse.getTradeNo();
        Orders order = paymentService.fetchOrderByOrderNo(orderNo);
        if (order != null) {
            if (order.getStatus().equals(OrderStatusEnum.SUCCESS)) {
                //当前订单支付成功
                result = "success";
            }
        } else {
            result = paymentService.orderQuery(orderNo);
        }
        return new Response<>(result);
    }

    @GetMapping
    public String syncReturn() {
        logger.info("支付完成后，自动执行跳转页面");
        return "success";
    }

    @GetMapping("/auth")
    public Response<String> authorization() {
        return null;
    }

}
