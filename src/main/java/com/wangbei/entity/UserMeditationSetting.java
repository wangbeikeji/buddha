package com.wangbei.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户禅修设置
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "user_meditation_setting")
public class UserMeditationSetting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Integer userId;
	/**
	 * 背景音乐对应的素材id
	 */
	@Column(name = "bg_music_material_resource_id")
	private Integer bgMusicMaterialResourceId;
	/**
	 * 设置时间
	 */
	@Column(name = "setting_time")
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

	public Integer getBgMusicMaterialResourceId() {
		return bgMusicMaterialResourceId;
	}

	public void setBgMusicMaterialResourceId(Integer bgMusicMaterialResourceId) {
		this.bgMusicMaterialResourceId = bgMusicMaterialResourceId;
	}

	public Date getSettingTime() {
		return settingTime;
	}

	public void setSettingTime(Date settingTime) {
		this.settingTime = settingTime;
	}

}
