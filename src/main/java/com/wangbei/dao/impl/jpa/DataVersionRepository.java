package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.AppVersion;

/**
 * 数据版本 Repository
 * 
 * @author luomengan
 *
 */
public interface DataVersionRepository extends CurdJpaRepository<AppVersion, Integer>, Repository<AppVersion, Integer> {
	
	List<AppVersion> findByIsCurrent(boolean isCurrent, Sort sort);
	
}
