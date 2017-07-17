package com.wangbei.dao.impl.jpa;

import com.wangbei.entity.MeritDetail;
import com.wangbei.util.enums.OfferingTypeEnum;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
public interface MeritDetailRepository extends Repository<MeritDetail, Integer>, CurdJpaRepository<MeritDetail,
        Integer> {

    List<MeritDetail> findAllByUserIdAndExpireTimeGreaterThan(Integer user, Date date);

    MeritDetail findByUserIdAndType(Integer user, OfferingTypeEnum type);
}
