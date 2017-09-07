package com.wangbei.dao.impl.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.FreeLife;

/**
 * @author Created by yuyidi on 2017/7/18.
 * @desc
 */
public interface FreeLifeRepository extends CurdJpaRepository<FreeLife, Integer>, Repository<FreeLife, Integer> {

	Page<FreeLife> findByMeritValueGreaterThan(int meritValue, Pageable pagable);

}
