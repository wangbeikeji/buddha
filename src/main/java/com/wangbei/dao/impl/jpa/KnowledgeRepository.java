package com.wangbei.dao.impl.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Knowledge;
import com.wangbei.util.enums.KnowledgeTypeEnum;

/**
 * 佛学知识 Repository
 * 
 * @author luomengan
 *
 */
public interface KnowledgeRepository extends Repository<Knowledge, Integer> {

	public Knowledge save(Knowledge knowledge);

	void delete(Integer id);

	Page<Knowledge> findAll(Pageable pageable);

	Page<Knowledge> findByType(KnowledgeTypeEnum type, Pageable pageable);

	Knowledge findById(Integer id);
}
