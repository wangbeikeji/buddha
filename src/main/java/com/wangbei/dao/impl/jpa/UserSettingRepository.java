package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.UserSetting;

/**
 * 用户设置 Repository
 * 
 * @author luomengan
 *
 */
public interface UserSettingRepository extends Repository<UserSetting, Integer> {

	UserSetting save(UserSetting userSetting);

	void delete(Integer id);

	Page<UserSetting> findAll(Pageable pageable);
	
	List<UserSetting> findAll();

	UserSetting findById(Integer id);

	UserSetting findByUserId(Integer userId);

	@Query(value = "select t1.user_id, t2.binding_account from user_setting t1, user_binding t2 where t1.buddhist_festival_remind=1 and t2.type=1 and t2.binding_account is not null and t2.binding_account != '' and t1.user_id=t2.user_id;", nativeQuery = true)
	List<Object[]> getBuddhistFestivalRemindUser();

	@Query(value = "select t1.user_id, t2.binding_account from user_setting t1, user_binding t2 where t1.feast_day_remind=1 and t2.type=1 and t2.binding_account is not null and t2.binding_account != '' and t1.user_id=t2.user_id;", nativeQuery = true)
	List<Object[]> getFeastDayRemindUser();

	@Query(value = "select t1.user_id, t2.binding_account from user_setting t1, user_binding t2 where t1.sutra_remind=1 and t2.type=1 and t2.binding_account is not null and t2.binding_account != '' and t1.user_id=t2.user_id;", nativeQuery = true)
	List<Object[]> getSutraRemindUser();

	@Query(value = "select t1.user_id, t2.binding_account from user_setting t1, user_binding t2 where t1.music_remind=1 and t2.type=1 and t2.binding_account is not null and t2.binding_account != '' and t1.user_id=t2.user_id;", nativeQuery = true)
	List<Object[]> getMusicRemindUser();

	@Query(value = "select t1.user_id, t2.binding_account from user_setting t1, user_binding t2 where t1.information_remind=1 and t2.type=1 and t2.binding_account is not null and t2.binding_account != '' and t1.user_id=t2.user_id;", nativeQuery = true)
	List<Object[]> getInformationRemindUser();
	
}
