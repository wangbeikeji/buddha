package com.wangbei.dao.impl.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Offerings;

/**
 * 供品 Repository
 * 
 * @author luomengan
 *
 */
public interface OfferingsRepository extends Repository<Offerings, Integer> {

	public Offerings save(Offerings offerings);

	void delete(Integer id);

	Page<Offerings> findAll(Pageable pageable);

	Offerings findById(Integer id);
	
}
