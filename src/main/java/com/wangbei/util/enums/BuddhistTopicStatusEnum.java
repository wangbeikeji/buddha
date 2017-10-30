package com.wangbei.util.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 修行主题状态枚举
 * 
 * @author luomengan
 *
 */
public enum BuddhistTopicStatusEnum implements CommonalityEnum {

	InProgress(1, "共修中"), IsOver(2, "已结束");

	private Integer index;
	private String type;

	BuddhistTopicStatusEnum(Integer index, String type) {
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

	private static Map<Integer, BuddhistTopicStatusEnum> valueMap = new HashMap<>();

	static {
		for (BuddhistTopicStatusEnum _enum : BuddhistTopicStatusEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static BuddhistTopicStatusEnum getByIndex(Integer index) {
		BuddhistTopicStatusEnum result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}

}
