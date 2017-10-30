package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 图片素材类型枚举
 * 
 * @author luomengan
 *
 */
public enum MaterialResourceTypeEnum implements CommonalityEnum {

	BuddhistTopic(1, "修行主题图片素材"),
	
	MeditationBgMusic(2, "禅修背景音乐素材");

	private Integer index;
	private String type;

	MaterialResourceTypeEnum(Integer index, String type) {
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

	private static Map<Integer, MaterialResourceTypeEnum> valueMap = new HashMap<>();

	static {
		for (MaterialResourceTypeEnum _enum : MaterialResourceTypeEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static MaterialResourceTypeEnum getByIndex(Integer index) {
		MaterialResourceTypeEnum result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}
	
}
