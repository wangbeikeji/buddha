package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * banner图类型枚举
 * 
 * @author luomengan
 *
 */
public enum BannerTypeEnum implements CommonalityEnum {

	INFORMATION(1, "资讯"), STORY(2, "故事"), BEGINNER(3, "入门");

	private Integer index;
	private String type;

	BannerTypeEnum(Integer index, String type) {
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

	private static Map<Integer, BannerTypeEnum> valueMap = new HashMap<>();

	static {
		for (BannerTypeEnum _enum : BannerTypeEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static BannerTypeEnum getByIndex(Integer index) {
		BannerTypeEnum result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}
}
