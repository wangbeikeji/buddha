package com.wangbei.pojo;

import com.wangbei.entity.UserBuddhistNote;
import com.wangbei.util.enums.BuddhistNoteTypeEnum;

public class UserBuddhistNoteInfo extends UserBuddhistNote {

	private static final long serialVersionUID = -5627364195614192796L;

	/**
	 * 用户点赞总数
	 */
	private Integer userLikeCount;

	/**
	 * 当前用户是否点过赞
	 */
	private boolean currentUserIsLike = false;

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

	public void setTypeByIndex(Integer typeIndex) {
		setType(BuddhistNoteTypeEnum.getByIndex(typeIndex));
	}
	
}
