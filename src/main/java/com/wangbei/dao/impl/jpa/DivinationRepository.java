package com.wangbei.dao.impl.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Divination;

/**
 * 灵签 Repository
 * 
 * @author luomengan
 *
 */
public interface DivinationRepository extends Repository<Divination, Integer> {

	public Divination save(Divination divination);

	void delete(Integer id);

	Page<Divination> findAll(Pageable pageable);

	Divination findById(Integer id);
	
}
