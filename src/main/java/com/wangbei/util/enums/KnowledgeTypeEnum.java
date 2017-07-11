package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuydidi 2017-07-10 21:47:03
 * @class com.wangbei.util.enums.OfferingTypeEnum
 * @description 佛学知识类型枚举
 */
public enum KnowledgeTypeEnum implements CommonalityEnum {

    INFORMATION(1, "资讯"), STORY(2, "故事"), BEGINNER(3, "入门");

    private Integer index;
    private String type;

    KnowledgeTypeEnum(Integer index, String type) {
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

    private static Map<Integer, KnowledgeTypeEnum> valueMap = new HashMap<>();

    static {
        for (KnowledgeTypeEnum _enum : KnowledgeTypeEnum.values()) {
            valueMap.put(_enum.getIndex(), _enum);
        }
    }

    public static KnowledgeTypeEnum getByIndex(Integer index){
        KnowledgeTypeEnum result = valueMap.get(index);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + index);
        }
        return result;
    }
}
