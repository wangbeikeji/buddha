package com.wangbei.dao.impl.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Checkin;

/**
 * 签到 Repository
 * 
 * @author luomengan
 *
 */
public interface CheckinRepository extends Repository<Checkin, Integer> {

	Checkin save(Checkin checkin);

	void delete(Integer id);

	Page<Checkin> findAll(Pageable pageable);
	
	List<Checkin> findAll();

	Checkin findById(Integer id);
	
	@Query("from Checkin where userId=?1 and checkinTime>=?2 and checkinTime<?3")
    List<Checkin> getUserCheckin(Integer userId, Date today, Date tomorrow);
	
}
