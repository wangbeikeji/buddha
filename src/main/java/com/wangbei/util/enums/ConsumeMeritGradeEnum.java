package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 功德累计消耗等级
 * 
 * @author luomengan
 *
 */
public enum ConsumeMeritGradeEnum implements CommonalityEnum {

	Grade1(1, 2000, "古道热肠"),

	Grade2(2, 7000, "良师益友"),

	Grade3(3, 12000, "悔人不倦"),

	Grade4(4, 32000, "为人师表"),

	Grade5(5, 60000, "桃李满天下");

	private Integer index;
	private Integer meritValue;
	private String name;

	ConsumeMeritGradeEnum(Integer index, Integer meritValue, String name) {
		this.index = index;
		this.meritValue = meritValue;
		this.name = name;
	}

	@Override
	public Integer getIndex() {
		return index;
	}

	public Integer getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(Integer meritValue) {
		this.meritValue = meritValue;
	}

	@JsonValue
	public String getName() {
		return name;
	}

	private static Map<Integer, ConsumeMeritGradeEnum> valueMap = new HashMap<>();

	static {
		for (ConsumeMeritGradeEnum _enum : ConsumeMeritGradeEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static ConsumeMeritGradeEnum getByIndex(Integer index) {
		ConsumeMeritGradeEnum result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}
}
