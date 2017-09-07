package com.wangbei.dao.impl.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Checkin;

/**
 * 签到 Repository
 * 
 * @author luomengan
 *
 */
public interface CheckinRepository extends CurdJpaRepository<Checkin, Integer>, Repository<Checkin, Integer> {

	@Query("select c from Checkin as c where c.userId=?1 and c.checkinTime>=?2 and c.checkinTime<?3")
	List<Checkin> getUserCheckin(Integer userId, Date today, Date tomorrow);

}
