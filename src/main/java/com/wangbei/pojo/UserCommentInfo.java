package com.wangbei.pojo;

import java.util.Date;

public class UserCommentInfo {

	private Integer id;

	private Integer userId;

	private String name;

	private String phone;

	private String headPortraitLink;

	private String comment;

	private Date commentTime;

	private Integer userLikeCount;

	private boolean currentUserIsLike = false;

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Integer getUserLikeCount() {
		return userLikeCount;
	}

	public void setUserLikeCount(Integer userLikeCount) {
		this.userLikeCount = userLikeCount;
	}

	public boolean isCurrentUserIsLike() {
		return currentUserIsLike;
	}

	public void setCurrentUserIsLike(boolean currentUserIsLike) {
		this.currentUserIsLike = currentUserIsLike;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
