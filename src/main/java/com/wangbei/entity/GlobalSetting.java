package com.wangbei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 全局设置
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "global_setting")
public class GlobalSetting implements Serializable {

	private static final long serialVersionUID = 2755735832655183892L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 佛教节日提醒时间，格式为00:00:00
	 */
	@Column(name = "buddhist_festival_remind_time")
	private String buddhistFestivalRemindTime;
	/**
	 * 斋日提醒时间，格式为00:00:00
	 */
	@Column(name = "feast_day_remind_time")
	private String feastDayRemindTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBuddhistFestivalRemindTime() {
		return buddhistFestivalRemindTime;
	}

	public void setBuddhistFestivalRemindTime(String buddhistFestivalRemindTime) {
		this.buddhistFestivalRemindTime = buddhistFestivalRemindTime;
	}

	public String getFeastDayRemindTime() {
		return feastDayRemindTime;
	}

	public void setFeastDayRemindTime(String feastDayRemindTime) {
		this.feastDayRemindTime = feastDayRemindTime;
	}

}
