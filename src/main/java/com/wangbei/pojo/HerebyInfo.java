package com.wangbei.pojo;

import java.util.Date;

/**
 * 请佛信息
 * 
 * @author luomengan
 *
 */
public class HerebyInfo {

	/**
	 * 请佛时间
	 */
	private Date jossCreateTime;
	/**
	 * 佛id
	 */
	private Integer jossId;
	/**
	 * 佛名称
	 */
	private String jossBuddhaName;
	/**
	 * 佛图片链接
	 */
	private String jossLink;
	/**
	 * 佛介绍
	 */
	private String jossIntroduction;

	public Date getJossCreateTime() {
		return jossCreateTime;
	}

	public void setJossCreateTime(Date jossCreateTime) {
		this.jossCreateTime = jossCreateTime;
	}

	public Integer getJossId() {
		return jossId;
	}

	public void setJossId(Integer jossId) {
		this.jossId = jossId;
	}

	public String getJossBuddhaName() {
		return jossBuddhaName;
	}

	public void setJossBuddhaName(String jossBuddhaName) {
		this.jossBuddhaName = jossBuddhaName;
	}

	public String getJossLink() {
		return jossLink;
	}

	public void setJossLink(String jossLink) {
		this.jossLink = jossLink;
	}

	public String getJossIntroduction() {
		return jossIntroduction;
	}

	public void setJossIntroduction(String jossIntroduction) {
		this.jossIntroduction = jossIntroduction;
	}

}
