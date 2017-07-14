package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Sutra;

/**
 * 经书 Repository
 * 
 * @author luomengan
 *
 */
public interface SutraRepository extends Repository<Sutra, Integer> {

	public Sutra save(Sutra sutra);

	void delete(Integer id);

	Page<Sutra> findAll(Pageable pageable);
	
	List<Sutra> findAll();

	Sutra findById(Integer id);
	
}
