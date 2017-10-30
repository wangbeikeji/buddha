package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 收藏类型枚举
 * 
 * @author luomengan
 *
 */
public enum FavouriteTypeEnum implements CommonalityEnum {

	OTHER(1, "其他"), INFORMATION(2, "资讯"), STORY(3, "故事"), BEGINNER(4, "入门"), SUTRA(5, "经文"), BUDDHISTMUSIC(6,
			"佛音"), HEALTH(7, "养生"), TEMPLE(8, "名刹");

	private Integer index;
	private String type;

	FavouriteTypeEnum(Integer index, String type) {
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

	private static Map<Integer, FavouriteTypeEnum> valueMap = new HashMap<>();

	static {
		for (FavouriteTypeEnum _enum : FavouriteTypeEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static FavouriteTypeEnum getByIndex(Integer index) {
		FavouriteTypeEnum result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}
}
