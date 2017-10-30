package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.BuddhistTopic;

/**
 * 修行主题 Repository
 * 
 * @author luomengan
 *
 */
public interface BuddhistTopicRepository extends Repository<BuddhistTopic, Integer> {

	BuddhistTopic save(BuddhistTopic buddhistTopic);

	void delete(Integer id);

	Page<BuddhistTopic> findAll(Pageable pageable);

	List<BuddhistTopic> findAll(Sort sort);

	BuddhistTopic findById(Integer id);

	Page<BuddhistTopic> findByIsEnable(boolean isEnable, Pageable pageable);

	List<BuddhistTopic> findByMaterialResourceId(Integer materialResourceId);

}
