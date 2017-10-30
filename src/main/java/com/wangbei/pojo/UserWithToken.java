package com.wangbei.pojo;

import com.wangbei.entity.User;

public class UserWithToken extends User {

	private static final long serialVersionUID = 1055754150263834640L;

	/**
	 * token
	 */
	private String token;
	/**
	 * 用户功德数
	 */
	private Integer meritValue;
	/**
	 * 用户功德消耗等级
	 */
	private Integer grade = 0;

	public UserWithToken(User u) {
		if (u != null) {
			this.setBirthday(u.getBirthday());
			this.setCreatTime(u.getCreatTime());
			this.setGender(u.getGender());
			this.setId(u.getId());
			this.setName(u.getName());
			this.setPhone(u.getPhone());
			this.setAddress(u.getAddress());
			this.setIsTest(u.getIsTest());
			this.setIsAnonymous(u.getIsAnonymous());
			this.setHeadPortraitLink(u.getHeadPortraitLink() == null ? "" : u.getHeadPortraitLink());
		}
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(Integer meritValue) {
		this.meritValue = meritValue;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

}
