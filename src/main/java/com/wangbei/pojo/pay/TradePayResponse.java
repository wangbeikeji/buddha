package com.wangbei.pojo.pay;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Created by yuyidi on 2017/7/27.
 * @desc
 */
public class TradePayResponse implements Serializable {

    private String code;
    private String msg;
    private String appId;
    private String outTradeNo;
    private String tradeNo;
    private Float totalAmount;
    private String sellerId;
    private String charset;
    private Date timestamp;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAppId() {
        return appId;
    }

    @JsonProperty("app_id")
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    @JsonProperty("out_trade_no")
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    @JsonProperty("trade_no")
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    @JsonProperty("total_amount")
    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSellerId() {
        return sellerId;
    }

    @JsonProperty("seller_id")
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
