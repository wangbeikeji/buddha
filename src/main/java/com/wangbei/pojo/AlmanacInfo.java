package com.wangbei.pojo;

import com.wangbei.entity.Almanac;

public class AlmanacInfo {

	/**
	 * 日期，格式为：2017-07-08 农历六月十五 星期六
	 */
	private String calendarStr;

	/**
	 * 岁次，格式为：辛亥年 （猪年） 庚子月 丁丑日
	 */
	private String yearName;

	/**
	 * 佛教节日，格式为：观音斋，太阳日宫诞，犯者得奇祸
	 */
	private String buddhistFestival;

	/**
	 * 佛教节日短名称，格式为：观音斋
	 */
	private String buddhistFestivalShortName;

	/**
	 * 宜
	 */
	private String should;

	/**
	 * 忌
	 */
	private String bogey;

	public AlmanacInfo() {
		super();
	}

	public AlmanacInfo(Almanac almanac) {
		StringBuilder calendarStrBuidler = new StringBuilder(almanac.getGregorianCalendar());
		calendarStrBuidler.insert(4, "-");
		calendarStrBuidler.insert(7, "-");
		if (almanac.getLunarCalendar() != null && !"".equals(almanac.getLunarCalendar().trim())) {
			calendarStrBuidler.append(" ");
			calendarStrBuidler.append(almanac.getLunarCalendar().substring(0, 5));
			calendarStrBuidler.append(almanac.getLunarCalendar().substring(8, 11));
			calendarStrBuidler.append(" ");
			calendarStrBuidler.append(almanac.getLunarCalendar().substring(14, 17));
		}
		this.setCalendarStr(calendarStrBuidler.toString());

		if (almanac.getYearName() != null && !"".equals(almanac.getYearName())) {
			StringBuilder yearNameBuilder = new StringBuilder(almanac.getYearName().replaceAll("、", " "));
			yearNameBuilder.replace(4, 6, "(");
			yearNameBuilder.replace(6, 7, ") ");
			this.setYearName(yearNameBuilder.toString());
		}

		if (almanac.getBuddhistCalendar() != null && !"".equals(almanac.getBuddhistCalendar())) {
			this.setBuddhistFestival(almanac.getBuddhistCalendar());
		} else {
			this.setBuddhistFestival("");
		}

		if (almanac.getBuddhistCalendar() != null && !"".equals(almanac.getBuddhistCalendar())) {
			String festivalShortName = almanac.getBuddhistCalendar().split("，")[0];
			if (festivalShortName.length() > 2) {
				festivalShortName = festivalShortName.substring(0, 2);
			}
			this.setBuddhistFestivalShortName(festivalShortName);
		} else {
			this.setBuddhistFestivalShortName("");
		}

		if (almanac.getShould() != null && !"".equals(almanac.getShould().trim())
				&& !"&nbsp;".equals(almanac.getShould().trim())) {
			this.setShould(almanac.getShould().trim());
		}

		if (almanac.getBogey() != null && !"".equals(almanac.getBogey().trim())
				&& !"&nbsp;".equals(almanac.getBogey().trim())) {
			this.setBogey(almanac.getBogey().trim());
		}

		// 把所有为null的字段赋值为空字符串
		if (this.getCalendarStr() == null) {
			this.setCalendarStr("");
		}
		if (this.getYearName() == null) {
			this.setYearName("");
		}
		if (this.getBuddhistFestival() == null) {
			this.setBuddhistFestival("");
		}
		if (this.getBuddhistFestivalShortName() == null) {
			this.setBuddhistFestivalShortName("");
		}
		if (this.getShould() == null) {
			this.setShould("");
		}
		if (this.getBogey() == null) {
			this.setBogey("");
		}
	}

	public String getCalendarStr() {
		return calendarStr;
	}

	public void setCalendarStr(String calendarStr) {
		this.calendarStr = calendarStr;
	}

	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public String getBuddhistFestival() {
		return buddhistFestival;
	}

	public void setBuddhistFestival(String buddhistFestival) {
		this.buddhistFestival = buddhistFestival;
	}

	public String getBuddhistFestivalShortName() {
		return buddhistFestivalShortName;
	}

	public void setBuddhistFestivalShortName(String buddhistFestivalShortName) {
		this.buddhistFestivalShortName = buddhistFestivalShortName;
	}

	public String getShould() {
		return should;
	}

	public void setShould(String should) {
		this.should = should;
	}

	public String getBogey() {
		return bogey;
	}

	public void setBogey(String bogey) {
		this.bogey = bogey;
	}

}
