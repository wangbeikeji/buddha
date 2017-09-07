package com.wangbei.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.UserSettingDao;
import com.wangbei.entity.UserSetting;
import com.wangbei.util.enums.PushTypeEnum;

/**
 * 用户设置 Service
 * 
 * @author luomengan
 *
 */
@Service
public class UserSettingService {

	@Autowired
	private UserSettingDao userSettingDao;

	public UserSetting getUserSettingInfo(Integer id) {
		return userSettingDao.retrieveUserSettingById(id);
	}

	@Transactional
	public UserSetting addUserSetting(UserSetting userSetting) {
		return userSettingDao.createUserSetting(userSetting);
	}

	@Transactional
	public UserSetting modifyUserSetting(UserSetting userSetting) {
		return userSettingDao.updateUserSetting(userSetting);
	}

	@Transactional
	public void deleteUserSetting(Integer id) {
		userSettingDao.deleteUserSettingById(id);
	}

	@Transactional
	public void deleteUserSettings(String ids) {
		if (ids != null) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				if (!"".equals(id.trim())) {
					userSettingDao.deleteUserSettingById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<UserSetting> userSettings(int page, int limit) {
		return userSettingDao.pageUserSetting(page, limit);
	}

	public List<UserSetting> list() {
		return userSettingDao.listUserSetting();
	}

	public UserSetting getMySetting(Integer userId) {
		UserSetting userSetting = userSettingDao.retrieveUserSettingByUserId(userId);
		if (userSetting == null) {
			userSetting = new UserSetting();
			userSetting.setAppShockRemind(true);
			userSetting.setAppVersionUpdate(true);
			userSetting.setAppVoiceRemind(true);
			userSetting.setBuddhistFestivalRemind(false);
			userSetting.setDayVenerateBuddhaRemind(false);
			userSetting.setFeastDayRemind(false);
			userSetting.setInformationRemind(true);
			userSetting.setMusicRemind(true);
			userSetting.setSutraRemind(true);
			userSetting.setUserId(userId);

			userSettingDao.createUserSetting(userSetting);
		}
		return userSetting;
	}

	public UserSetting changeMySetting(Integer userId, Boolean buddhistFestivalRemind, Boolean feastDayRemind,
			Boolean dayVenerateBuddhaRemind, Boolean sutraRemind, Boolean musicRemind, Boolean appVersionUpdate,
			Boolean informationRemind, Boolean appVoiceRemind, Boolean appShockRemind) {
		UserSetting userSetting = userSettingDao.retrieveUserSettingByUserId(userId);
		if (userSetting == null) {
			userSetting = new UserSetting();
			userSetting.setAppShockRemind(appShockRemind != null ? appShockRemind : true);
			userSetting.setAppVersionUpdate(appVersionUpdate != null ? appVersionUpdate : true);
			userSetting.setAppVoiceRemind(appVoiceRemind != null ? appVoiceRemind : true);
			userSetting.setBuddhistFestivalRemind(buddhistFestivalRemind != null ? buddhistFestivalRemind : false);
			userSetting.setDayVenerateBuddhaRemind(dayVenerateBuddhaRemind != null ? dayVenerateBuddhaRemind : false);
			userSetting.setFeastDayRemind(feastDayRemind != null ? feastDayRemind : false);
			userSetting.setInformationRemind(informationRemind != null ? informationRemind : true);
			userSetting.setMusicRemind(musicRemind != null ? musicRemind : true);
			userSetting.setSutraRemind(sutraRemind != null ? sutraRemind : true);
			userSetting.setUserId(userId);

			userSettingDao.createUserSetting(userSetting);
		} else {
			if (appShockRemind != null)
				userSetting.setAppShockRemind(appShockRemind);
			if (appVersionUpdate != null)
				userSetting.setAppVersionUpdate(appVersionUpdate);
			if (appVoiceRemind != null)
				userSetting.setAppVoiceRemind(appVoiceRemind);
			if (buddhistFestivalRemind != null)
				userSetting.setBuddhistFestivalRemind(buddhistFestivalRemind);
			if (dayVenerateBuddhaRemind != null)
				userSetting.setDayVenerateBuddhaRemind(dayVenerateBuddhaRemind);
			if (feastDayRemind != null)
				userSetting.setFeastDayRemind(feastDayRemind);
			if (informationRemind != null)
				userSetting.setInformationRemind(informationRemind);
			if (musicRemind != null)
				userSetting.setMusicRemind(musicRemind);
			if (sutraRemind != null)
				userSetting.setSutraRemind(sutraRemind);
			userSettingDao.updateUserSetting(userSetting);
		}
		return userSetting;
	}
	
	public Map<Integer, String> getRemindUserByPushType(PushTypeEnum type) {
		return userSettingDao.getRemindUserByPushType(type);
	} 

}
