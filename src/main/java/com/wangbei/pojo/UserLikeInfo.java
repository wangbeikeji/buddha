package com.wangbei.pojo;

import java.util.Date;

public class UserLikeInfo {

	private Integer userId;

	private String phone;

	private String headPortraitLink;

	private String text = "南无阿弥陀佛！";

	private Date likeTime;

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getLikeTime() {
		return likeTime;
	}

	public void setLikeTime(Date likeTime) {
		this.likeTime = likeTime;
	}

}
