package com.wangbei.dao.impl.jpa;

import com.wangbei.entity.Hereby;
import org.springframework.data.repository.Repository;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
public interface HerebyRepository extends CurdJpaRepository<Hereby, Integer>, Repository<Hereby, Integer> {

}
