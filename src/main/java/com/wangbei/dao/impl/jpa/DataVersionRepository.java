package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.AppVersion;

/**
 * 数据版本 Repository
 * 
 * @author luomengan
 *
 */
public interface DataVersionRepository extends Repository<AppVersion, Integer> {

	AppVersion save(AppVersion appVersion);

	void delete(Integer id);

	Page<AppVersion> findAll(Pageable pageable);
	
	List<AppVersion> findAll();

	AppVersion findById(Integer id);
	
	List<AppVersion> findByIsCurrent(boolean isCurrent, Sort sort);
	
}
