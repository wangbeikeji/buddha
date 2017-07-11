package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuyidi 2017-07-06 17:02:43
 * @class com.wangbei.util.enums.GenderEnum
 * @description 性别枚举
 */
public enum GenderEnum implements CommonalityEnum {
    MAN(1, "男"), FEMALE(2, "女");

    private Integer index;
    private String gender;

    GenderEnum(Integer index, String gender) {
        this.index = index;
        this.gender = gender;
    }

    @Override
    public Integer getIndex() {
        return index;
    }

    @JsonValue
    public String getGender() {
        return gender;
    }


    private static Map<Integer, GenderEnum> valueMap = new HashMap<>();

    static {
        for (GenderEnum _enum : GenderEnum.values()) {
            valueMap.put(_enum.getIndex(), _enum);
        }
    }

    public static GenderEnum getByIndex(Integer index){
        GenderEnum result = valueMap.get(index);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + index);
        }
        return result;
    }
}