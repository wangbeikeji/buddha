package com.wangbei.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wangbei.awre.converter.UserLikeTypeEnumConverter;
import com.wangbei.util.enums.UserLikeTypeEnum;

/**
 * 用户点赞
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "user_like")
public class UserLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Integer userId;
	/**
	 * 点赞时间
	 */
	@Column(name = "like_time")
	private Date likeTime;
	/**
	 * 用户点赞类型
	 */
	@Column(name = "type")
	@Convert(converter = UserLikeTypeEnumConverter.class)
	private UserLikeTypeEnum type;
	/**
	 * 点赞资源id
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

	public UserLikeTypeEnum getType() {
		return type;
	}

	public void setType(UserLikeTypeEnum type) {
		this.type = type;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Date getLikeTime() {
		return likeTime;
	}

	public void setLikeTime(Date likeTime) {
		this.likeTime = likeTime;
	}

}
