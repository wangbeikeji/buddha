package com.wangbei.pojo;

import com.wangbei.entity.User;

public class UserWithToken extends User {

	private static final long serialVersionUID = 1055754150263834640L;
	
	private String token;
	
	public UserWithToken(User u) {
		if(u != null) {
			this.setBirthday(u.getBirthday());
			this.setCreatTime(u.getCreatTime());
			this.setGender(u.getGender());
			this.setId(u.getId());
			this.setName(u.getName());
			this.setPhone(u.getPhone());
			this.setAddress(u.getAddress());
		}
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
