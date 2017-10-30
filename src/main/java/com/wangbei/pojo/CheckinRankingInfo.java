package com.wangbei.pojo;

import java.util.Date;

import com.wangbei.util.enums.ConsumeMeritGradeEnum;

public class CheckinRankingInfo {

	private Integer userId;

	private String phone;

	private String headPortraitLink;

	private Integer meritValue;

	private Integer consumeMeritValue;

	private Integer ranking;

	private Date checkinTime;

	private Integer checkinCount;

	private Integer likeCount;

	private boolean currentUserIsLike;

	private Integer grade = 0;

	private Integer rankType;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Date getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(Date checkinTime) {
		this.checkinTime = checkinTime;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getCheckinCount() {
		return checkinCount;
	}

	public void setCheckinCount(Integer checkinCount) {
		this.checkinCount = checkinCount;
	}

	public Integer getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(Integer meritValue) {
		this.meritValue = meritValue;
	}

	public boolean isCurrentUserIsLike() {
		return currentUserIsLike;
	}

	public void setCurrentUserIsLike(boolean currentUserIsLike) {
		this.currentUserIsLike = currentUserIsLike;
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

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getConsumeMeritValue() {
		return consumeMeritValue;
	}

	public void setConsumeMeritValue(Integer consumeMeritValue) {
		this.consumeMeritValue = consumeMeritValue;
	}

	public Integer getRankType() {
		return rankType;
	}

	public void setRankType(Integer rankType) {
		this.rankType = rankType;
	}

}
