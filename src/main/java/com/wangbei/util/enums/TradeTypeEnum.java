package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuyidi 2017-07-06 17:02:43
 * @class com.wangbei.util.enums.GenderEnum
 * @description 交易类型枚举
 */
public enum TradeTypeEnum implements CommonalityEnum {
    CHARGE(1, "充值"),SANDALWOOD(2, "清香"), FRUIT(3, "供果"), FLOWERS(4, "供花");

    private Integer index;
    private String trade;

    TradeTypeEnum(Integer index, String trade) {
        this.index = index;
        this.trade = trade;
    }

    @Override
    public Integer getIndex() {
        return index;
    }

    @JsonValue
    public String getTrade() {
        return trade;
    }


    private static Map<Integer, TradeTypeEnum> valueMap = new HashMap<>();

    static {
        for (TradeTypeEnum _enum : TradeTypeEnum.values()) {
            valueMap.put(_enum.getIndex(), _enum);
        }
    }

    public static TradeTypeEnum getByIndex(Integer index){
        TradeTypeEnum result = valueMap.get(index);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + index);
        }
        return result;
    }
}
