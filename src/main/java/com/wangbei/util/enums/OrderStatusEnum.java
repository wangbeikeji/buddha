package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 交易状态 枚举
 * 
 * @author luomengan
 *
 */
public enum OrderStatusEnum implements CommonalityEnum {

	UNPAY(0, "未付款"),PAYCLOSE(1,"交易关闭"),SUCCESS(2, "支付成功"),FINISHED(3,"支付结束");

	private static Map<Integer, OrderStatusEnum> valueMap = new HashMap<>();

	static {
		for (OrderStatusEnum _enum : OrderStatusEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	private Integer index;
	private String status;

	OrderStatusEnum(Integer index, String status) {
		this.index = index;
		this.status = status;
	}

	public static OrderStatusEnum getByIndex(Integer index) {
		OrderStatusEnum result = valueMap.get(index);
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
