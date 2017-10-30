package com.wangbei.pojo;

public class UserDayMeditationRecord {

	/**
	 * 日禅修次数
	 */
	private Integer times = 0;
	/**
	 * 日最近一次禅修时长（秒）
	 */
	private Integer newestDuration = 0;
	/**
	 * 日总禅修时长（秒）
	 */
	private Integer totalDuration = 0;

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getNewestDuration() {
		return newestDuration;
	}

	public void setNewestDuration(Integer newestDuration) {
		this.newestDuration = newestDuration;
	}

	public Integer getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(Integer totalDuration) {
		this.totalDuration = totalDuration;
	}

}
