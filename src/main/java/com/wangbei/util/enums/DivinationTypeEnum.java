package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuyidi 2017-07-10 20:32:00
 * @class com.wangbei.util.enums.DivinationTypeEnum
 * @description 灵签类型枚举
 */
public enum DivinationTypeEnum implements CommonalityEnum {

    UPUP(1, "上上签"), UP(2, "上签"), MIDDLE(3, "中签"), DOWN(4, "下签"), DOWNDOWN(5, "下下签");

    private Integer index;
    private String type;

    DivinationTypeEnum(Integer index, String type) {
        this.index = index;
        this.type = type;
    }

    @Override
    public Integer getIndex() {
        return index;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    private static Map<Integer, DivinationTypeEnum> valueMap = new HashMap<>();

    static {
        for (DivinationTypeEnum _enum : DivinationTypeEnum.values()) {
            valueMap.put(_enum.getIndex(), _enum);
        }
    }

    public static DivinationTypeEnum getByIndex(Integer index){
        DivinationTypeEnum result = valueMap.get(index);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + index);
        }
        return result;
    }
}
