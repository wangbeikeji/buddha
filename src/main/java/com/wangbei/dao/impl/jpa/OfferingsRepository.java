package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Offerings;

/**
 * 供品 Repository
 * 
 * @author luomengan
 *
 */
public interface OfferingsRepository extends CurdJpaRepository<Offerings, Integer>, Repository<Offerings, Integer> {

	List<Offerings> findAll(Sort sort);

}
