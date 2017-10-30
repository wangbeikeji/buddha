package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.UserLightLamp;

/**
 * 用户点灯 Dao
 * 
 * @author luomengan
 *
 */
public interface UserLightLampDao {

	public UserLightLamp createUserLightLamp(UserLightLamp userLightLamp);

	public void deleteUserLightLampById(Integer id);

	public UserLightLamp updateUserLightLamp(UserLightLamp userLightLamp);

	public UserLightLamp retrieveUserLightLampById(Integer id);

	public Page<UserLightLamp> pageUserLightLamp(int page, int limit);
	
	public List<UserLightLamp> listUserLightLamp();

	public List<UserLightLamp> listUserLightLampByUserId(int userId);

}
