package com.wangbei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户设置
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "user_setting")
public class UserSetting implements Serializable {

	private static final long serialVersionUID = 2755735832655183892L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 用户id
	 */
	@Column(name = "user_id", unique = true)
	private Integer userId;
	/**
	 * 是否佛教节日提醒
	 */
	@Column(name = "buddhist_festival_remind")
	private Boolean buddhistFestivalRemind;
	/**
	 * 是否斋日提醒
	 */
	@Column(name = "feast_day_remind")
	private Boolean feastDayRemind;
	/**
	 * 是否每日礼佛提醒
	 */
	@Column(name = "day_venerate_buddha_remind")
	private Boolean dayVenerateBuddhaRemind;
	/**
	 * 是否经书推送
	 */
	@Column(name = "sutra_remind")
	private Boolean sutraRemind;
	/**
	 * 是否佛音推送
	 */
	@Column(name = "music_remind")
	private Boolean musicRemind;
	/**
	 * 是否开启App版本更新
	 */
	@Column(name = "app_version_update")
	private Boolean appVersionUpdate;
	/**
	 * 是否佛学资讯推送
	 */
	@Column(name = "information_remind")
	private Boolean informationRemind;
	/**
	 * 是否开启App声音
	 */
	@Column(name = "app_voice_remind")
	private Boolean appVoiceRemind;
	/**
	 * 是否开启App震动
	 */
	@Column(name = "app_shock_remind")
	private Boolean appShockRemind;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getBuddhistFestivalRemind() {
		return buddhistFestivalRemind;
	}

	public void setBuddhistFestivalRemind(Boolean buddhistFestivalRemind) {
		this.buddhistFestivalRemind = buddhistFestivalRemind;
	}

	public Boolean getFeastDayRemind() {
		return feastDayRemind;
	}

	public void setFeastDayRemind(Boolean feastDayRemind) {
		this.feastDayRemind = feastDayRemind;
	}

	public Boolean getDayVenerateBuddhaRemind() {
		return dayVenerateBuddhaRemind;
	}

	public void setDayVenerateBuddhaRemind(Boolean dayVenerateBuddhaRemind) {
		this.dayVenerateBuddhaRemind = dayVenerateBuddhaRemind;
	}

	public Boolean getSutraRemind() {
		return sutraRemind;
	}

	public void setSutraRemind(Boolean sutraRemind) {
		this.sutraRemind = sutraRemind;
	}

	public Boolean getMusicRemind() {
		return musicRemind;
	}

	public void setMusicRemind(Boolean musicRemind) {
		this.musicRemind = musicRemind;
	}

	public Boolean getAppVersionUpdate() {
		return appVersionUpdate;
	}

	public void setAppVersionUpdate(Boolean appVersionUpdate) {
		this.appVersionUpdate = appVersionUpdate;
	}

	public Boolean getInformationRemind() {
		return informationRemind;
	}

	public void setInformationRemind(Boolean informationRemind) {
		this.informationRemind = informationRemind;
	}

	public Boolean getAppVoiceRemind() {
		return appVoiceRemind;
	}

	public void setAppVoiceRemind(Boolean appVoiceRemind) {
		this.appVoiceRemind = appVoiceRemind;
	}

	public Boolean getAppShockRemind() {
		return appShockRemind;
	}

	public void setAppShockRemind(Boolean appShockRemind) {
		this.appShockRemind = appShockRemind;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
