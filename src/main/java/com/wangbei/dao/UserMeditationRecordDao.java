package com.wangbei.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.UserMeditationRecord;

/**
 * 用户禅修记录 Dao
 * 
 * @author luomengan
 *
 */
public interface UserMeditationRecordDao {

	public UserMeditationRecord createUserMeditationRecord(UserMeditationRecord userMeditationRecord);

	public void deleteUserMeditationRecordById(Integer id);

	public UserMeditationRecord updateUserMeditationRecord(UserMeditationRecord userMeditationRecord);

	public UserMeditationRecord retrieveUserMeditationRecordById(Integer id);

	public Page<UserMeditationRecord> pageUserMeditationRecord(int page, int limit);
	
	public List<UserMeditationRecord> listUserMeditationRecord();

	public List<UserMeditationRecord> listUserMeditationRecordByUserIdAndBettenTime(Integer userId, Date startTime, Date endTime);

}
