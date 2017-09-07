package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Hereby;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
public interface HerebyRepository extends CurdJpaRepository<Hereby, Integer>, Repository<Hereby, Integer> {

	@Query("update Hereby h set h.jossId = ?2 where h.userId = ?1 ")
	@Modifying
	Integer updateJossByUser(Integer user, Integer joss);

	Hereby findByUserIdAndJossId(Integer userId, Integer jossId);

	@Query("delete from Hereby where userId=?1 and jossId=?2")
	@Modifying
	void deleteByUserIdAndJossId(Integer userId, Integer jossId);
	
	List<Hereby> findByUserId(Integer userId, Sort sort);

}
