package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 推送类型枚举
 * 
 * @author luomengan
 *
 */
public enum PushTypeEnum implements CommonalityEnum {

	BuddhistFestivalRemind(1, "佛教节日提醒"), 
	
	FeastDayRemind(2, "斋日提醒"), 
	
	DayVenerateBuddhaRemind(3, "每日礼佛提醒"),
	
	SutraRemind(4, "经书推送"),
	
	MusicRemind(5, "佛音推送"),
	
	InformationRemind(6, "资讯推送"),
	
	AnonymousUserInit(7, "匿名用户初始化");

	private Integer index;
	private String type;

	PushTypeEnum(Integer index, String type) {
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

	private static Map<Integer, PushTypeEnum> valueMap = new HashMap<>();

	static {
		for (PushTypeEnum _enum : PushTypeEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static PushTypeEnum getByIndex(Integer index) {
		PushTypeEnum result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}
}
