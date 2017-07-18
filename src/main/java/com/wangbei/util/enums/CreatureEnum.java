package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuydidi 2017-07-10 21:47:03
 * @class com.wangbei.util.enums.OfferingTypeEnum
 * @description 生灵枚举
 */
public enum CreatureEnum implements CommonalityEnum {

    TORTOISE(1, "千年龟"), BIRD(2, "百灵鸟"), FISH(3, "锦鲤");

    private Integer index;
    private String type;

    CreatureEnum(Integer index, String type) {
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

    private static Map<Integer, CreatureEnum> valueMap = new HashMap<>();

    static {
        for (CreatureEnum _enum : CreatureEnum.values()) {
            valueMap.put(_enum.getIndex(), _enum);
        }
    }

    public static CreatureEnum getByIndex(Integer index){
        CreatureEnum result = valueMap.get(index);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + index);
        }
        return result;
    }
}
