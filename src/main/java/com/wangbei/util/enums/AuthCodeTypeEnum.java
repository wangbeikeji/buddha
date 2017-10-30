package com.wangbei.util.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AuthCodeTypeEnum implements CommonalityEnum {

	REGISTER(1, "注册"), RESETPASSWORD(2, "重置密码");

    private Integer index;
    private String type;

    AuthCodeTypeEnum(Integer index, String type) {
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

    private static Map<Integer, AuthCodeTypeEnum> valueMap = new HashMap<>();

    static {
        for (AuthCodeTypeEnum _enum : AuthCodeTypeEnum.values()) {
            valueMap.put(_enum.getIndex(), _enum);
        }
    }

    public static AuthCodeTypeEnum getByIndex(Integer index){
        AuthCodeTypeEnum result = valueMap.get(index);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + index);
        }
        return result;
    }
}
