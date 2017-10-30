package com.wangbei.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.wangbei.awre.converter.UserCommentTypeEnumConverter;
import com.wangbei.util.enums.UserCommentTypeEnum;

/**
 * 用户评论
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "user_comment")
public class UserComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Integer userId;
	/**
	 * 评论
	 */
	@Type(type = "text")
	@Column(name = "comment")
	private String comment;
	/**
	 * 评论时间
	 */
	@Column(name = "comment_time")
	private Date commentTime;
	/**
	 * 用户评论类型
	 */
	@Column(name = "type")
	@Convert(converter = UserCommentTypeEnumConverter.class)
	private UserCommentTypeEnum type;
	/**
	 * 评论资源id
	 */
	@Column(name = "resource_id")
	private Integer resourceId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public UserCommentTypeEnum getType() {
		return type;
	}

	public void setType(UserCommentTypeEnum type) {
		this.type = type;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

}
