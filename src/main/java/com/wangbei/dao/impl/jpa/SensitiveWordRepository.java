package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.SensitiveWord;

/**
 * 敏感词 Repository
 * 
 * @author luomengan
 *
 */
public interface SensitiveWordRepository extends Repository<SensitiveWord, Integer> {

	SensitiveWord save(SensitiveWord sensitiveWord);

	void delete(Integer id);

	Page<SensitiveWord> findAll(Pageable pageable);
	
	List<SensitiveWord> findAll();

	SensitiveWord findById(Integer id);

	List<SensitiveWord> findByIsEnable(boolean isEnable);
	
}
