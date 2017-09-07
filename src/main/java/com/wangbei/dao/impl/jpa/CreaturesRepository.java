package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Creatures;

/**
 * 生灵 Repository
 * 
 * @author luomengan
 *
 */
public interface CreaturesRepository extends Repository<Creatures, Integer> {

	Creatures save(Creatures creatures);

	void delete(Integer id);

	Page<Creatures> findAll(Pageable pageable);
	
	List<Creatures> findAll();

	Creatures findById(Integer id);

	List<Creatures> findAll(Sort sort);

	List<Creatures> findByIsEnable(boolean isEnable, Sort sort);
	
}
