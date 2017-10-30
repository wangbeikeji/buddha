package com.wangbei.dao.impl.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.UserMeditationRecord;

/**
 * 用户禅修记录 Repository
 * 
 * @author luomengan
 *
 */
public interface UserMeditationRecordRepository extends Repository<UserMeditationRecord, Integer> {

	UserMeditationRecord save(UserMeditationRecord userMeditationRecord);

	void delete(Integer id);

	Page<UserMeditationRecord> findAll(Pageable pageable);

	List<UserMeditationRecord> findAll();

	UserMeditationRecord findById(Integer id);

	@Query("from UserMeditationRecord where userId=?1 and ((startTime>=?2 and startTime<?3) or (endTime>=?2 and endTime<?3)) order by startTime desc")
	List<UserMeditationRecord> findByUserIdAndBettenTime(Integer userId, Date startTime, Date endTime);

}
