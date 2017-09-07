package com.wangbei.pojo;

import java.util.Date;

/**
 * 最新放生
 * 
 * @author luomengan
 *
 */
public class NewestFreeLife {

	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 用户手机号
	 */
	private String phone;
	/**
	 * 功德数
	 */
	private Integer meritValue;
	/**
	 * 生灵名称
	 */
	private String creatureName;
	/**
	 * 放生时间
	 */
	private Date time;
	/**
	 * 统计类型
	 * <ul>
	 * <li>1捐赠功德</li>
	 * <li>2放生</li>
	 * </ul>
	 */
	private Integer type = 2;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPhone() {
		if (phone != null && phone.length() >= 8) {
			return phone.substring(0, 3) + "****" + phone.substring(7);
		}
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(Integer meritValue) {
		this.meritValue = meritValue;
	}

	public String getCreatureName() {
		return creatureName;
	}

	public void setCreatureName(String creatureName) {
		this.creatureName = creatureName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
