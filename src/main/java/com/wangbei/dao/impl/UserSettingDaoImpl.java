package com.wangbei.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.UserSettingDao;
import com.wangbei.dao.impl.jpa.UserSettingRepository;
import com.wangbei.entity.UserSetting;
import com.wangbei.util.enums.PushTypeEnum;

/**
 * 用户设置 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class UserSettingDaoImpl implements UserSettingDao {

	@Autowired
	private UserSettingRepository userSettingRepository;

	@Override
	public UserSetting createUserSetting(UserSetting userSetting) {
		return userSettingRepository.save(userSetting);
	}

	@Override
	public void deleteUserSettingById(Integer id) {
		userSettingRepository.delete(id);
	}

	@Override
	public UserSetting updateUserSetting(UserSetting userSetting) {
		return userSettingRepository.save(userSetting);
	}

	@Override
	public UserSetting retrieveUserSettingById(Integer id) {
		return userSettingRepository.findById(id);
	}

	@Override
	public Page<UserSetting> pageUserSetting(int page, int limit) {
		return userSettingRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<UserSetting> listUserSetting() {
		return userSettingRepository.findAll();
	}

	@Override
	public UserSetting retrieveUserSettingByUserId(Integer userId) {
		return userSettingRepository.findByUserId(userId);
	}

	@Override
	public Map<Integer, String> getRemindUserByPushType(PushTypeEnum type) {
		Map<Integer, String> result = new HashMap<>();
		List<Object[]> objArrList = null;
		if (type == PushTypeEnum.BuddhistFestivalRemind) {
			objArrList = userSettingRepository.getBuddhistFestivalRemindUser();
		} else if (type == PushTypeEnum.FeastDayRemind) {
			objArrList = userSettingRepository.getFeastDayRemindUser();
		} else if (type == PushTypeEnum.SutraRemind) {
			objArrList = userSettingRepository.getSutraRemindUser();
		} else if (type == PushTypeEnum.MusicRemind) {
			objArrList = userSettingRepository.getMusicRemindUser();
		} else if (type == PushTypeEnum.InformationRemind) {
			objArrList = userSettingRepository.getInformationRemindUser();
		}
		if (objArrList != null && objArrList.size() > 0) {
			for (Object[] objArr : objArrList) {
				Integer userId = (Integer) objArr[0];
				String registrationId = (String) objArr[1];
				result.put(userId, registrationId);
			}
		}
		return result;
	}

}
