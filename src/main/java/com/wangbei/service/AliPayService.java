package com.wangbei.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.wangbei.entity.Orders;
import com.wangbei.exception.ServiceException;
import com.wangbei.util.JacksonUtil;
import com.wangbei.util.constants.AlipayConfigConstant;
import com.wangbei.util.enums.OrderStatusEnum;
import com.wangbei.util.enums.PaymentTypeEnum;
import com.wangbei.util.enums.TradeTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by yuyidi on 2017/7/25.
 * @desc
 */
@Service
public class AliPayService {

    Logger logger = LoggerFactory.getLogger(getClass());
    //实例化客户端
    AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfigConstant.URL, AlipayConfigConstant.APPID,
            AlipayConfigConstant.APP_PRIVATE_KEY, AlipayConfigConstant.FORMAT, AlipayConfigConstant.CHARSET,
            AlipayConfigConstant.ALIPAY_PUBLIC_KEY, AlipayConfigConstant.SIGNTYPE);
    @Autowired
    private OrderService orderService;

    @Transactional
    public String pay(Integer user, TradeTypeEnum tradeTypeEnum, Double amount) {
        //创建订单 且订单状态为未支付
        Orders order = orderService.generateOrder(user, PaymentTypeEnum.AliPay, amount, OrderService.generateOrderNo());
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        String amountStr = String.valueOf(amount);
        String subject = TradeTypeEnum.getByTradeType(tradeTypeEnum);
        model.setSubject(subject);
        model.setOutTradeNo(order.getOrderNo());
        model.setTimeoutExpress("30m");
        model.setTotalAmount(amountStr);
        model.setProductCode("QUICK_MSECURITY_PAY");
        model.setGoodsType("0");//虚拟类商品
        request.setBizModel(model);
        request.setNotifyUrl(AlipayConfigConstant.NOTIFY_URL);
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            String result = URLDecoder.decode(response.getBody(), "UTF-8");
            logger.info("订单支付请求完成:{}", result);
            return result;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ServiceException(ServiceException.UNKNOW_EXCEPTION);
        }
        return null;
    }

    public String callback(Map<String, String> params) throws AlipayApiException {
        boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfigConstant.ALIPAY_PUBLIC_KEY,
                AlipayConfigConstant.CHARSET, AlipayConfigConstant.SIGNTYPE);
        if (flag) {
            //商户订单号
            String outTradeNo = params.get("out_trade_no");
            //支付宝交易号
            String tradeNo = params.get("trade_no");
            //支付交易状态
            String status = params.get("trade_status");
            String timestamp = params.get("timestamp");
            logger.info("商户订单号：{},支付宝交易号:{},交易状态为:{},支付时间:{}", outTradeNo, tradeNo, status, timestamp);
            //交易成功后，需要判断当前商户订单是否已经处理 并处理当前订单状态
            if (status.equals("TRADE_SUCCESS")) {
                orderService.updateOrderStatus(outTradeNo, tradeNo, OrderStatusEnum.SUCCESS, new Date());
            }
            return "success";
        }
        return "fail";
    }

    public String orderQuery(String aliOrderNo) throws AlipayApiException {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        Map<String, String> bizModel = new HashMap<>();
        bizModel.put("trade_no",aliOrderNo);
        request.setBizContent(JacksonUtil.encode(bizModel));
        AlipayTradeQueryResponse response = alipayClient.sdkExecute(request);
        try {
            String result = URLDecoder.decode(response.getBody(), "UTF-8");
            if (response.getTradeStatus().equals("TRADE_SUCCESS")) {
                //若支付成功
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    public Orders fetchOrderByOrderNo(String orderNo) {
        return orderService.getOrderByOrderNo(orderNo);
    }
}
