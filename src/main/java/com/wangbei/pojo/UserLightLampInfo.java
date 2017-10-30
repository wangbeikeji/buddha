package com.wangbei.pojo;

import java.util.Date;

public class UserLightLampInfo {

	/**
	 * 用户
	 */
	private Integer userId;
	/**
	 * 祈福明灯id
	 */
	private Integer lampId;
	/**
	 * 名称
	 */
	private String lampName;
	/**
	 * 图片链接
	 */
	private String lampLink;
	/**
	 * 图片链接缩略图
	 */
	private String lampSmallLink;
	/**
	 * 寓意
	 */
	private String lampMeaning;
	/**
	 * 功效
	 */
	private String lampEffect;
	/**
	 * 灯油id
	 */
	private Integer oilId;
	/**
	 * 灯油描述
	 */
	private String oilDesc;
	/**
	 * 灯油可燃烧天数
	 */
	private Integer oilFlameDays;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 过期时间
	 */
	private Date expireTime;
	/**
	 * 消耗的功德
	 */
	private int meritValue;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLampId() {
		return lampId;
	}

	public void setLampId(Integer lampId) {
		this.lampId = lampId;
	}

	public String getLampName() {
		return lampName;
	}

	public void setLampName(String lampName) {
		this.lampName = lampName;
	}

	public String getLampLink() {
		return lampLink;
	}

	public void setLampLink(String lampLink) {
		this.lampLink = lampLink;
	}

	public String getLampSmallLink() {
		return lampSmallLink;
	}

	public void setLampSmallLink(String lampSmallLink) {
		this.lampSmallLink = lampSmallLink;
	}

	public String getLampMeaning() {
		return lampMeaning;
	}

	public void setLampMeaning(String lampMeaning) {
		this.lampMeaning = lampMeaning;
	}

	public String getLampEffect() {
		return lampEffect;
	}

	public void setLampEffect(String lampEffect) {
		this.lampEffect = lampEffect;
	}

	public Integer getOilId() {
		return oilId;
	}

	public void setOilId(Integer oilId) {
		this.oilId = oilId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public int getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(int meritValue) {
		this.meritValue = meritValue;
	}

	public String getOilDesc() {
		return oilDesc;
	}

	public void setOilDesc(String oilDesc) {
		this.oilDesc = oilDesc;
	}

	public Integer getOilFlameDays() {
		return oilFlameDays;
	}

	public void setOilFlameDays(Integer oilFlameDays) {
		this.oilFlameDays = oilFlameDays;
	}

}
