package com.wangbei.pojo.pay;

import com.wangbei.util.enums.AlipayResultStatus;

import java.io.Serializable;

/**
 * @author Created by yuyidi on 2017/7/25.
 * @desc
 */
public class AlipayPaymentInfo implements Serializable {
    private String memo;
    private String resultStatus;
    private TradePayResult result;


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public TradePayResult getResult() {
        return result;
    }

    public void setResult(TradePayResult result) {
        this.result = result;
    }
}
