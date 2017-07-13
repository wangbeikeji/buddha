package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Rune;

/**
 * 符文 Repository
 * 
 * @author luomengan
 *
 */
public interface RuneRepository extends Repository<Rune, Integer> {

	public Rune save(Rune rune);

	void delete(Integer id);

	Page<Rune> findAll(Pageable pageable);
	
	List<Rune> findAll();

	Rune findById(Integer id);
	
}
