package com.wangbei.dao.impl.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.MeritDetail;
import com.wangbei.util.enums.OfferingTypeEnum;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
public interface MeritDetailRepository
		extends Repository<MeritDetail, Integer>, CurdJpaRepository<MeritDetail, Integer> {

	List<MeritDetail> findAllByUserIdAndExpireTimeGreaterThan(Integer user, Date date);

	MeritDetail findByUserIdAndType(Integer user, OfferingTypeEnum type);

	List<MeritDetail> findAllByUserIdAndJossIdAndExpireTimeGreaterThan(Integer userId, Integer jossId, Date date, Sort sort);

	List<MeritDetail> findByUserIdAndJossId(Integer userId, Integer jossId);
}
