package com.wangbei.pojo.pay;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author Created by yuyidi on 2017/7/27.
 * @desc
 */
public class TradePayResult implements Serializable{

    private TradePayResponse alipayTradeAppPayResponse;
    private String sign;
    private String signType;


    public TradePayResponse getAlipayTradeAppPayResponse() {
        return alipayTradeAppPayResponse;
    }
    @JsonProperty("alipay_trade_app_pay_response")
    public void setAlipayTradeAppPayResponse(TradePayResponse alipayTradeAppPayResponse) {
        this.alipayTradeAppPayResponse = alipayTradeAppPayResponse;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }
    @JsonProperty("sign_type")
    public void setSignType(String signType) {
        this.signType = signType;
    }
}
