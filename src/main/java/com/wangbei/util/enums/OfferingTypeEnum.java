package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuydidi 2017-07-10 21:47:03
 * @class com.wangbei.util.enums.OfferingTypeEnum
 * @description 供品类型枚举
 */
public enum OfferingTypeEnum implements CommonalityEnum {

    SANDALWOOD(1, "檀香"), FRUIT(2, "水果"), FLOWERS(3, "鲜花"), CREATURES(4, "生灵");

    private Integer index;
    private String type;

    OfferingTypeEnum(Integer index, String type) {
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

    private static Map<Integer, OfferingTypeEnum> valueMap = new HashMap<>();

    static {
        for (OfferingTypeEnum _enum : OfferingTypeEnum.values()) {
            valueMap.put(_enum.getIndex(), _enum);
        }
    }

    public static OfferingTypeEnum getByIndex(Integer index){
        OfferingTypeEnum result = valueMap.get(index);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + index);
        }
        return result;
    }
}
