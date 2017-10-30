package com.wangbei.pojo;

import java.util.Date;
import java.util.List;

/**
 * 修行主题详情页全部信息
 * 
 * @author luomengan
 *
 */
public class BuddhistTopicFullInfo {

	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 当前用户是否点过赞
	 */
	private boolean currentUserIsLike = false;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 主题详情
	 */
	private String topicDetail;
	/**
	 * 图片链接
	 */
	private String link;
	/**
	 * 缩略图链接
	 */
	private String smallLink;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 主题时间，例如主题为地震同胞祈祷平安，则主题时间为地震发生日期
	 */
	private Date topicTime;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	/**
	 * 用户点赞总数
	 */
	private Integer userLikeCount;
	/**
	 * 用户点赞信息列表
	 */
	private List<UserLikeInfo> userLikeInfoList;
	/**
	 * 用户评论信息列表
	 */
	private List<UserCommentInfo> userCommentInfoList;
	/**
	 * 当前页码
	 */
	private Integer number;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTopicDetail() {
		return topicDetail;
	}

	public void setTopicDetail(String topicDetail) {
		this.topicDetail = topicDetail;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSmallLink() {
		return smallLink;
	}

	public void setSmallLink(String smallLink) {
		this.smallLink = smallLink;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getTopicTime() {
		return topicTime;
	}

	public void setTopicTime(Date topicTime) {
		this.topicTime = topicTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getUserLikeCount() {
		return userLikeCount;
	}

	public void setUserLikeCount(Integer userLikeCount) {
		this.userLikeCount = userLikeCount;
	}

	public List<UserLikeInfo> getUserLikeInfoList() {
		return userLikeInfoList;
	}

	public void setUserLikeInfoList(List<UserLikeInfo> userLikeInfoList) {
		this.userLikeInfoList = userLikeInfoList;
	}

	public boolean isCurrentUserIsLike() {
		return currentUserIsLike;
	}

	public void setCurrentUserIsLike(boolean currentUserIsLike) {
		this.currentUserIsLike = currentUserIsLike;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public List<UserCommentInfo> getUserCommentInfoList() {
		return userCommentInfoList;
	}

	public void setUserCommentInfoList(List<UserCommentInfo> userCommentInfoList) {
		this.userCommentInfoList = userCommentInfoList;
	}

}
