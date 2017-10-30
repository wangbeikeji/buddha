package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.UserMeditationSetting;

/**
 * 用户禅修设置 Repository
 * 
 * @author luomengan
 *
 */
public interface UserMeditationSettingRepository extends Repository<UserMeditationSetting, Integer> {

	UserMeditationSetting save(UserMeditationSetting userMeditationSetting);

	void delete(Integer id);

	Page<UserMeditationSetting> findAll(Pageable pageable);
	
	List<UserMeditationSetting> findAll();

	UserMeditationSetting findById(Integer id);

	List<UserMeditationSetting> findByUserId(int userId, Sort sort);
	
}
