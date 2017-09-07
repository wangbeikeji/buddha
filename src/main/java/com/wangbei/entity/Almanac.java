package com.wangbei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * 黄历+佛历
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "almanac")
public class Almanac implements Serializable {

	private static final long serialVersionUID = -3134119135723936174L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 公历（格式为yyyyMMdd）
	 */
	@Column(name = "gregorian_calendar", unique = true)
	private String gregorianCalendar;

	/**
	 * 农历
	 */
	private String lunarCalendar;

	/**
	 * 岁次
	 */
	private String yearName;

	/**
	 * 每日胎神占方
	 */
	private String taishenSide;

	/**
	 * 五行
	 */
	private String fiveElements;

	/**
	 * 冲
	 */
	private String punching;

	/**
	 * 彭祖百忌
	 */
	private String pengzuBogey;

	/**
	 * 吉神宜趋
	 */
	private String jishenShouldTrend;

	/**
	 * 凶神宜忌
	 */
	private String xiongshenShouldBogey;

	/**
	 * 宜
	 */
	private String should;

	/**
	 * 忌
	 */
	private String bogey;

	/**
	 * 佛历
	 */
	private String buddhistCalendar;

	/**
	 * 总览
	 */
	@Type(type = "text")
	private String overview;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGregorianCalendar() {
		return gregorianCalendar;
	}

	public void setGregorianCalendar(String gregorianCalendar) {
		this.gregorianCalendar = gregorianCalendar;
	}

	public String getLunarCalendar() {
		return lunarCalendar;
	}

	public void setLunarCalendar(String lunarCalendar) {
		this.lunarCalendar = lunarCalendar;
	}

	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public String getTaishenSide() {
		return taishenSide;
	}

	public void setTaishenSide(String taishenSide) {
		this.taishenSide = taishenSide;
	}

	public String getFiveElements() {
		return fiveElements;
	}

	public void setFiveElements(String fiveElements) {
		this.fiveElements = fiveElements;
	}

	public String getPunching() {
		return punching;
	}

	public void setPunching(String punching) {
		this.punching = punching;
	}

	public String getPengzuBogey() {
		return pengzuBogey;
	}

	public void setPengzuBogey(String pengzuBogey) {
		this.pengzuBogey = pengzuBogey;
	}

	public String getJishenShouldTrend() {
		return jishenShouldTrend;
	}

	public void setJishenShouldTrend(String jishenShouldTrend) {
		this.jishenShouldTrend = jishenShouldTrend;
	}

	public String getXiongshenShouldBogey() {
		return xiongshenShouldBogey;
	}

	public void setXiongshenShouldBogey(String xiongshenShouldBogey) {
		this.xiongshenShouldBogey = xiongshenShouldBogey;
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

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getBuddhistCalendar() {
		return buddhistCalendar;
	}

	public void setBuddhistCalendar(String buddhistCalendar) {
		this.buddhistCalendar = buddhistCalendar;
	}

	@Override
	public String toString() {
		return "Almanac [id=" + id + ", gregorianCalendar=" + gregorianCalendar + ", lunarCalendar=" + lunarCalendar
				+ ", yearName=" + yearName + ", taishenSide=" + taishenSide + ", fiveElements=" + fiveElements
				+ ", punching=" + punching + ", pengzuBogey=" + pengzuBogey + ", jishenShouldTrend=" + jishenShouldTrend
				+ ", xiongshenShouldBogey=" + xiongshenShouldBogey + ", should=" + should + ", bogey=" + bogey
				+ ", buddhistCalendar=" + buddhistCalendar + ", overview=" + overview + "]";
	}

}
