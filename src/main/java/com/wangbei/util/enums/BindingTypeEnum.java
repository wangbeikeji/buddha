package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户绑定类型枚举
 * 
 * @author luomengan
 *
 */
public enum BindingTypeEnum implements CommonalityEnum {

	JiguangPush(1, "极光推送"),

	IosIdfv(2, "IOS设备唯一标识");

	private Integer index;
	private String type;

	BindingTypeEnum(Integer index, String type) {
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

	private static Map<Integer, BindingTypeEnum> valueMap = new HashMap<>();

	static {
		for (BindingTypeEnum _enum : BindingTypeEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static BindingTypeEnum getByIndex(Integer index) {
		BindingTypeEnum result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}
}
