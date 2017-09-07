package com.wangbei.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wangbei.awre.converter.FavouriteTypeEnumConverter;
import com.wangbei.util.enums.FavouriteTypeEnum;

/**
 * 用户收藏
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "user_favourite")
public class UserFavourite implements Serializable {

	private static final long serialVersionUID = 4775992635241425417L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 收藏类型
	 */
	@Column(name = "type")
	@Convert(converter = FavouriteTypeEnumConverter.class)
	private FavouriteTypeEnum type;

	/**
	 * 收藏时间
	 */
	@Column(name = "favourite_time")
	private Date favouriteTime;

	/**
	 * 收藏资源id
	 */
	@Column(name = "resource_id")
	private Integer resourceId;

	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 缩略图完整链接
	 */
	@Transient
	private String fullLink;

	/**
	 * 标题
	 */
	@Transient
	private String title;

	public UserFavourite(Integer id, FavouriteTypeEnum type, Date favouriteTime, Integer resourceId, Integer userId,
			String fullLink, String title) {
		super();
		this.id = id;
		this.type = type;
		this.favouriteTime = favouriteTime;
		this.resourceId = resourceId;
		this.userId = userId;
		this.fullLink = fullLink;
		this.title = title;
	}

	public UserFavourite() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FavouriteTypeEnum getType() {
		return type;
	}

	public void setType(FavouriteTypeEnum type) {
		this.type = type;
	}

	public Date getFavouriteTime() {
		return favouriteTime;
	}

	public void setFavouriteTime(Date favouriteTime) {
		this.favouriteTime = favouriteTime;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFullLink() {
		return fullLink;
	}

	public void setFullLink(String fullLink) {
		this.fullLink = fullLink;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
