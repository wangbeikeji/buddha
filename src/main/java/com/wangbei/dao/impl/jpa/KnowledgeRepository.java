package com.wangbei.dao.impl.jpa;

import java.util.List;

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
public interface KnowledgeRepository extends CurdJpaRepository<Knowledge, Integer>, Repository<Knowledge, Integer> {

	Page<Knowledge> findByType(KnowledgeTypeEnum type, Pageable pageable);
	
	List<Knowledge> findByType(KnowledgeTypeEnum type);
	
}
