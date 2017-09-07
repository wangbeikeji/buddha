package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 佛录类型枚举
 * 
 * @author luomengan
 *
 */
public enum BuddhistNoteTypeEnum implements CommonalityEnum {

	PRAY(1, "祈福录"), GOODNESS(2, "行善录"), CONFESSION(3, "忏悔录");

	private Integer index;
	private String type;

	BuddhistNoteTypeEnum(Integer index, String type) {
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

	private static Map<Integer, BuddhistNoteTypeEnum> valueMap = new HashMap<>();

	static {
		for (BuddhistNoteTypeEnum _enum : BuddhistNoteTypeEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static BuddhistNoteTypeEnum getByIndex(Integer index) {
		BuddhistNoteTypeEnum result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}
}
