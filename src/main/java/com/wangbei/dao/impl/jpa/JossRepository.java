package com.wangbei.dao.impl.jpa;

import org.springframework.data.repository.Repository;

import com.wangbei.entity.Joss;

/**
 * @author Created by yuyidi on 2017/7/11.
 * @desc
 */
public interface JossRepository extends CurdJpaRepository<Joss, Integer>, Repository<Joss, Integer>{

}
