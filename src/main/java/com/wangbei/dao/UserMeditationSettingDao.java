package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.UserMeditationSetting;

/**
 * 用户禅修设置 Dao
 * 
 * @author luomengan
 *
 */
public interface UserMeditationSettingDao {

	public UserMeditationSetting createUserMeditationSetting(UserMeditationSetting userMeditationSetting);

	public void deleteUserMeditationSettingById(Integer id);

	public UserMeditationSetting updateUserMeditationSetting(UserMeditationSetting userMeditationSetting);

	public UserMeditationSetting retrieveUserMeditationSettingById(Integer id);

	public Page<UserMeditationSetting> pageUserMeditationSetting(int page, int limit);
	
	public List<UserMeditationSetting> listUserMeditationSetting();

	public List<UserMeditationSetting> listUserMeditationSettingByUserId(int userId);

}
