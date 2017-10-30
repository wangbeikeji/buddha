package com.wangbei.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.UserMeditationRecordDao;
import com.wangbei.dao.impl.jpa.UserMeditationRecordRepository;
import com.wangbei.entity.UserMeditationRecord;

/**
 * 用户禅修记录 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class UserMeditationRecordDaoImpl implements UserMeditationRecordDao {

	@Autowired
	private UserMeditationRecordRepository userMeditationRecordRepository;

	@Override
	public UserMeditationRecord createUserMeditationRecord(UserMeditationRecord userMeditationRecord) {
		return userMeditationRecordRepository.save(userMeditationRecord);
	}

	@Override
	public void deleteUserMeditationRecordById(Integer id) {
		userMeditationRecordRepository.delete(id);
	}

	@Override
	public UserMeditationRecord updateUserMeditationRecord(UserMeditationRecord userMeditationRecord) {
		return userMeditationRecordRepository.save(userMeditationRecord);
	}

	@Override
	public UserMeditationRecord retrieveUserMeditationRecordById(Integer id) {
		return userMeditationRecordRepository.findById(id);
	}

	@Override
	public Page<UserMeditationRecord> pageUserMeditationRecord(int page, int limit) {
		return userMeditationRecordRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<UserMeditationRecord> listUserMeditationRecord() {
		return userMeditationRecordRepository.findAll();
	}

	@Override
	public List<UserMeditationRecord> listUserMeditationRecordByUserIdAndBettenTime(Integer userId, Date startTime,
			Date endTime) {
		return userMeditationRecordRepository.findByUserIdAndBettenTime(userId, startTime, endTime);
	}

}
