package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.UserMeditationSettingDao;
import com.wangbei.dao.impl.jpa.UserMeditationSettingRepository;
import com.wangbei.entity.UserMeditationSetting;

/**
 * 用户禅修设置 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class UserMeditationSettingDaoImpl implements UserMeditationSettingDao {

	@Autowired
	private UserMeditationSettingRepository userMeditationSettingRepository;

	@Override
	public UserMeditationSetting createUserMeditationSetting(UserMeditationSetting userMeditationSetting) {
		return userMeditationSettingRepository.save(userMeditationSetting);
	}

	@Override
	public void deleteUserMeditationSettingById(Integer id) {
		userMeditationSettingRepository.delete(id);
	}

	@Override
	public UserMeditationSetting updateUserMeditationSetting(UserMeditationSetting userMeditationSetting) {
		return userMeditationSettingRepository.save(userMeditationSetting);
	}

	@Override
	public UserMeditationSetting retrieveUserMeditationSettingById(Integer id) {
		return userMeditationSettingRepository.findById(id);
	}

	@Override
	public Page<UserMeditationSetting> pageUserMeditationSetting(int page, int limit) {
		return userMeditationSettingRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<UserMeditationSetting> listUserMeditationSetting() {
		return userMeditationSettingRepository.findAll();
	}

	@Override
	public List<UserMeditationSetting> listUserMeditationSettingByUserId(int userId) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "settingTime"));
		return userMeditationSettingRepository.findByUserId(userId, sort);
	}

}
