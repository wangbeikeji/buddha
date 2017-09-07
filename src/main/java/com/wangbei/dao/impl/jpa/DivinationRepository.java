package com.wangbei.dao.impl.jpa;

import org.springframework.data.repository.Repository;

import com.wangbei.entity.Divination;

/**
 * 灵签 Repository
 * 
 * @author luomengan
 *
 */
public interface DivinationRepository extends CurdJpaRepository<Divination, Integer>, Repository<Divination, Integer> {
	
}
