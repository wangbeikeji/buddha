package com.wangbei.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.wangbei.util.constants.AlipayConfigConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

    public String pay() {
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo("1234567890123");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("http://susuhx.ngrok.cc/buddha/pay/callback");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            return response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String callback(Map<String, String> params) throws AlipayApiException {
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfigConstant.ALIPAY_PUBLIC_KEY,
                AlipayConfigConstant.CHARSET, AlipayConfigConstant.SIGNTYPE);
        if (flag) {
            //商户订单号
            String outTradeNo = params.get("out_trade_no");
            //支付宝交易号
            String tradeNo = params.get("trade_no");
            //支付交易状态
            String status = params.get("trade_status");
            logger.info("商户订单号：{},支付宝交易号:{},交易状态为:{}",outTradeNo,tradeNo,status);
        }
        return "fail";
    }
}
