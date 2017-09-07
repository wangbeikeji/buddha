package com.wangbei.pojo;

import java.util.List;

import com.wangbei.entity.UserFavourite;

public class UserFavouriteGroupInfo {

	private String tabName;

	private List<UserFavourite> list;

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public List<UserFavourite> getList() {
		return list;
	}

	public void setList(List<UserFavourite> list) {
		this.list = list;
	}

}
