package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.DataVersion;

/**
 * 数据版本 Repository
 * 
 * @author luomengan
 *
 */
public interface DataVersionRepository extends Repository<DataVersion, Integer> {

	DataVersion save(DataVersion dataVersion);

	void delete(Integer id);

	Page<DataVersion> findAll(Pageable pageable);
	
	List<DataVersion> findAll();

	DataVersion findById(Integer id);
	
	List<DataVersion> findByIsCurrent(boolean isCurrent, Sort sort);
	
}
