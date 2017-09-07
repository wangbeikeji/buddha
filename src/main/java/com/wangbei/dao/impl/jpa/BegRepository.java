package com.wangbei.dao.impl.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Beg;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc
 */
public interface BegRepository extends CurdJpaRepository<Beg, Integer>, Repository<Beg, Integer> {

	Beg findByUserId(Integer user);

	List<Beg> findAllByUserIdAndExpireTimeGreaterThan(Integer user, Date time);

	@Query("select b from Beg b where b.userId=?1 and b.jossId=?2 and b.expireTime>?3")
	List<Beg> findAllByUserIdAndJossIdExpireTimeGreaterThan(Integer userId, Integer jossId, Date time);

	List<Beg> findByUserIdAndJossId(Integer userId, Integer jossId);

}
