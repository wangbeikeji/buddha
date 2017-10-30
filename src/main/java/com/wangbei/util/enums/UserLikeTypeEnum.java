package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户点赞类型
 * 
 * @author luomengan
 *
 */
public enum UserLikeTypeEnum implements CommonalityEnum {

	BuddhistTopicLike(1, "修行主题点赞"),

	DayCheckinRankingLike(2, "签到日排行点赞"),

	MonthCheckinRankingLike(3, "签到月排行点赞"),

	TotalCheckinRankingLike(4, "签到总排行点赞"),

	BuddhistNoteLike(5, "佛录点赞"),
	
	BuddhistNoteCommentLike(6, "佛录评论点赞");

	private static Map<Integer, UserLikeTypeEnum> valueMap = new HashMap<>();

	static {
		for (UserLikeTypeEnum _enum : UserLikeTypeEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	private Integer index;
	private String trade;

	UserLikeTypeEnum(Integer index, String trade) {
		this.index = index;
		this.trade = trade;
	}

	public static UserLikeTypeEnum getByIndex(Integer index) {
		UserLikeTypeEnum result = valueMap.get(index);
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
