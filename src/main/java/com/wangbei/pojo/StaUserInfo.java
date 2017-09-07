package com.wangbei.pojo;

import java.util.Date;

/**
 * 后台用户信息统计实体
 * 
 * @author luomengan
 *
 */
public class StaUserInfo {

	private Integer id;

	private String name;

	private String phone;

	private Integer gender;

	private String birthday;

	private String address;

	private Date registTime;

	private Date loginTime;

	private Double userCharge;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Double getUserCharge() {
		return userCharge;
	}

	public void setUserCharge(Double userCharge) {
		this.userCharge = userCharge;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

}
