package com.wangbei.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.wangbei.entity.UserSetting;
import com.wangbei.util.enums.PushTypeEnum;

/**
 * 用户设置 Dao
 * 
 * @author luomengan
 *
 */
public interface UserSettingDao {

	public UserSetting createUserSetting(UserSetting userSetting);

	public void deleteUserSettingById(Integer id);

	public UserSetting updateUserSetting(UserSetting userSetting);

	public UserSetting retrieveUserSettingById(Integer id);

	public Page<UserSetting> pageUserSetting(int page, int limit);
	
	public List<UserSetting> listUserSetting();

	public UserSetting retrieveUserSettingByUserId(Integer userId);
	
	public Map<Integer, String> getRemindUserByPushType(PushTypeEnum type);

}
