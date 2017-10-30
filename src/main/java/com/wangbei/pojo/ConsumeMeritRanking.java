package com.wangbei.pojo;

import com.wangbei.util.enums.ConsumeMeritGradeEnum;

public class ConsumeMeritRanking {

	private Integer userId;

	private String name;

	private String phone;

	private String headPortraitLink;

	private Integer ranking;

	private Integer meritValue;

	private Integer consumeMeritValue;

	private Integer grade = 0;

	private Integer rankType;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		if (phone != null && phone.length() >= 8) {
			return phone.substring(0, 3) + "****" + phone.substring(7);
		}
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHeadPortraitLink() {
		return headPortraitLink;
	}

	public void setHeadPortraitLink(String headPortraitLink) {
		this.headPortraitLink = headPortraitLink;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public Integer getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(Integer meritValue) {
		this.meritValue = meritValue;
	}

	public Integer getConsumeMeritValue() {
		return consumeMeritValue;
	}

	public void setConsumeMeritValue(Integer consumeMeritValue) {
		this.consumeMeritValue = consumeMeritValue;
	}

	public Integer getGrade() {
		if (consumeMeritValue != null) {
			if (consumeMeritValue >= ConsumeMeritGradeEnum.Grade5.getMeritValue()) {
				return ConsumeMeritGradeEnum.Grade5.getIndex();
			} else if (consumeMeritValue >= ConsumeMeritGradeEnum.Grade4.getMeritValue()) {
				return ConsumeMeritGradeEnum.Grade4.getIndex();
			} else if (consumeMeritValue >= ConsumeMeritGradeEnum.Grade3.getMeritValue()) {
				return ConsumeMeritGradeEnum.Grade3.getIndex();
			} else if (consumeMeritValue >= ConsumeMeritGradeEnum.Grade2.getMeritValue()) {
				return ConsumeMeritGradeEnum.Grade2.getIndex();
			} else if (consumeMeritValue >= ConsumeMeritGradeEnum.Grade1.getMeritValue()) {
				return ConsumeMeritGradeEnum.Grade1.getIndex();
			}
		}
		return grade;
	}

	public Integer getRankType() {
		return rankType;
	}

	public void setRankType(Integer rankType) {
		this.rankType = rankType;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

}
