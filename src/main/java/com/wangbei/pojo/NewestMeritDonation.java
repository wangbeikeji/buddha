package com.wangbei.pojo;

import java.util.Date;

/**
 * 最新功德捐赠实体
 * 
 * @author luomengan
 *
 */
public class NewestMeritDonation {

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
	 * 捐赠时间
	 */
	private Date time;
	/**
	 * 统计类型
	 * <ul>
	 * <li>1捐赠功德</li>
	 * <li>2放生</li>
	 * </ul>
	 */
	private Integer type = 1;

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
