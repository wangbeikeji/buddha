package com.wangbei.dao.impl.jpa;

import com.wangbei.entity.FreeLife;
import org.springframework.data.repository.Repository;

/**
 * @author Created by yuyidi on 2017/7/18.
 * @desc
 */
public interface FreeLifeRepository extends CurdJpaRepository<FreeLife, Integer>, Repository<FreeLife, Integer> {

}
