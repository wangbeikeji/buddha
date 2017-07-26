package com.wangbei.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.wangbei.pojo.Response;
import com.wangbei.service.AliPayService;
import com.wangbei.util.constants.AlipayConfigConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/pay")
public class AliPayController {

    @Autowired
    private AliPayService paymentService;

    @PostMapping("/payment")
    public Response<String> payment() {
        return new Response<>(paymentService.pay());
    }

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
     * @author yuyidi 2017-07-25 15:11:03
     * @method sync
     * @param
     * @return com.wangbei.pojo.Response<java.lang.String>
     * @description 主动请求返回
     */
    @GetMapping("/sync")
    public Response<String> sync() {
        return null;
    }

    @GetMapping("/auth")
    public Response<String> authorization() {
        return null;
    }

}
