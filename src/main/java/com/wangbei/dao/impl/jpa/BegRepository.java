package com.wangbei.dao.impl.jpa;

import com.wangbei.entity.Beg;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc
 */
public interface BegRepository extends CurdJpaRepository<Beg, Integer>, Repository<Beg, Integer> {
    Beg findByUserId(Integer user);

    List<Beg> findAllByUserIdAndExpireTimeGreaterThan(Integer user, Date time);
}
