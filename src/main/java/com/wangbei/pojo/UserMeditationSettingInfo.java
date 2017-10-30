package com.wangbei.pojo;

import java.util.Date;

/**
 * 用户禅修设置信息
 * 
 * @author luomengan
 *
 */
public class UserMeditationSettingInfo {

	/**
	 * 用户禅修设置id
	 */
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 背景音乐对应的素材id
	 */
	private Integer materialResourceId;
	/**
	 * 素材名称
	 */
	private String materialResourceName;
	/**
	 * 素材链接
	 */
	private String materialResourceLink;
	/**
	 * 设置时间
	 */
	private Date settingTime;

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

	public Integer getMaterialResourceId() {
		return materialResourceId;
	}

	public void setMaterialResourceId(Integer materialResourceId) {
		this.materialResourceId = materialResourceId;
	}

	public String getMaterialResourceName() {
		return materialResourceName;
	}

	public void setMaterialResourceName(String materialResourceName) {
		this.materialResourceName = materialResourceName;
	}

	public String getMaterialResourceLink() {
		return materialResourceLink;
	}

	public void setMaterialResourceLink(String materialResourceLink) {
		this.materialResourceLink = materialResourceLink;
	}

	public Date getSettingTime() {
		return settingTime;
	}

	public void setSettingTime(Date settingTime) {
		this.settingTime = settingTime;
	}

}
