package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.wangbei.pojo.VirtualGoodsInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuyidi 2017-07-06 17:02:43
 * @class com.wangbei.util.enums.GenderEnum
 * @description 交易类型枚举
 */
public enum TradeTypeEnum implements CommonalityEnum {
    CHARGE(0, "充值"),
    SANDALWOOD(1, "请香"),
    FRUIT(2, "供果"),
    FLOWERS(3, "供花"),
    RUNE(4, "求符"),
    DIVINATION(5, "求签"),
    CHECKIN(6, "签到"),
    FREELIFE(7, "放生"),
    MERIT(8, "功德");

    private static Map<TradeTypeEnum, String> virtualGoodsInfoMap = new HashMap<>();
    private static Map<Integer, TradeTypeEnum> valueMap = new HashMap<>();

    static {
        virtualGoodsInfoMap.put(CHARGE, "功德充值");
        virtualGoodsInfoMap.put(FREELIFE,"生灵放生");
        virtualGoodsInfoMap.put(MERIT, "随喜功德");
    }

    static {
        for (TradeTypeEnum _enum : TradeTypeEnum.values()) {
            valueMap.put(_enum.getIndex(), _enum);
        }
    }


    private Integer index;
    private String trade;

    TradeTypeEnum(Integer index, String trade) {
        this.index = index;
        this.trade = trade;
    }

    public static TradeTypeEnum getByIndex(Integer index) {
        TradeTypeEnum result = valueMap.get(index);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + index);
        }
        return result;
    }

    public static String getByTradeType(TradeTypeEnum tradeTypeEnum) {
        return virtualGoodsInfoMap.get(tradeTypeEnum);
    }

    @Override
    public Integer getIndex() {
        return index;
    }

    @JsonValue
    public String getTrade() {
        return trade;
    }
}
