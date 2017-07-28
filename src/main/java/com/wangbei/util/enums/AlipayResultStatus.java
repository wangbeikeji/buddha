package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by yuyidi on 2017/7/27.
 * @desc
 */
public enum AlipayResultStatus{

    SUCCESS("9000", "订单支付成功"),
    PROCESSING("8000", "正在处理中"),
    FAIL("4000", "订单支付失败"),
    REPEAD("5000", "重复请求"),
    CANCEL("6001", "用户中途取消"),
    NETERROR("6002", "网络连接出错"),
    UNKNOW("6004", "支付结果未知"),
    OTHER("其他", "其它支付错误");


    private String code;
    private String status;

    AlipayResultStatus(String code, String status) {
        this.code = code;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private static Map<String, AlipayResultStatus> valueMap = new HashMap<>();

    static {
        for (AlipayResultStatus _enum : AlipayResultStatus.values()) {
            valueMap.put(_enum.getCode(), _enum);
        }
    }

    public static AlipayResultStatus getByIndex(Integer index){
        AlipayResultStatus result = valueMap.get(index);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + index);
        }
        return result;
    }
}
