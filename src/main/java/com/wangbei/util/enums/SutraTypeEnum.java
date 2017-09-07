package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 经书类型枚举
 * 
 * @author luomengan
 *
 */
public enum SutraTypeEnum implements CommonalityEnum {

	OTHER(0, "其他"), LECTION(1, "经文"), INCANTATION(2, "咒语"), ZEN(3, "禅宗"), TRANSLATION(4, "译文");

	private Integer index;
	private String type;

	SutraTypeEnum(Integer index, String type) {
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

	private static Map<Integer, SutraTypeEnum> valueMap = new HashMap<>();

	static {
		for (SutraTypeEnum _enum : SutraTypeEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static SutraTypeEnum getByIndex(Integer index) {
		SutraTypeEnum result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}
}
