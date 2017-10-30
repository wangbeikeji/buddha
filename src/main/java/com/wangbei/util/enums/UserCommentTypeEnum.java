package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户评论类型
 * 
 * @author luomengan
 *
 */
public enum UserCommentTypeEnum implements CommonalityEnum {

	BuddhistTopicComment(1, "修行主题评论");

	private static Map<Integer, UserCommentTypeEnum> valueMap = new HashMap<>();

	static {
		for (UserCommentTypeEnum _enum : UserCommentTypeEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	private Integer index;
	private String trade;

	UserCommentTypeEnum(Integer index, String trade) {
		this.index = index;
		this.trade = trade;
	}

	public static UserCommentTypeEnum getByIndex(Integer index) {
		UserCommentTypeEnum result = valueMap.get(index);
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
