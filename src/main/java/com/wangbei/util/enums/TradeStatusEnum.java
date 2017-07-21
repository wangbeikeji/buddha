package com.wangbei.util.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 交易状态 枚举
 * 
 * @author luomengan
 *
 */
public enum TradeStatusEnum implements CommonalityEnum {

	PENDING(0, "待完成"), COMPLETED(1, "已完成");

	private static Map<Integer, TradeStatusEnum> valueMap = new HashMap<>();

	static {
		for (TradeStatusEnum _enum : TradeStatusEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	private Integer index;
	private String status;

	TradeStatusEnum(Integer index, String status) {
		this.index = index;
		this.status = status;
	}

	public static TradeStatusEnum getByIndex(Integer index) {
		TradeStatusEnum result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}

	@Override
	public Integer getIndex() {
		return index;
	}

	@JsonValue
	public String getStatus() {
		return status;
	}
}
