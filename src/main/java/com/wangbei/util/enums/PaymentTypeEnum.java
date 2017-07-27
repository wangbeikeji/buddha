package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付类型
 * 
 * @author luomengan
 *
 */
public enum PaymentTypeEnum implements CommonalityEnum {

	ApplePay(1, "苹果支付"),

	WxPay(2, "微信支付"),

	AliPay(3, "阿里支付"),
	
	MeritPay(4, "功德支付");

	private static Map<Integer, PaymentTypeEnum> valueMap = new HashMap<>();

	static {
		for (PaymentTypeEnum _enum : PaymentTypeEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	private Integer index;
	private String trade;

	PaymentTypeEnum(Integer index, String trade) {
		this.index = index;
		this.trade = trade;
	}

	public static PaymentTypeEnum getByIndex(Integer index) {
		PaymentTypeEnum result = valueMap.get(index);
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
	public String getTrade() {
		return trade;
	}
}
