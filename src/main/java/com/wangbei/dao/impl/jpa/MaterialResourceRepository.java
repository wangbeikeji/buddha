package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.MaterialResource;
import com.wangbei.util.enums.MaterialResourceTypeEnum;

/**
 * 素材资源 Repository
 * 
 * @author luomengan
 *
 */
public interface MaterialResourceRepository extends Repository<MaterialResource, Integer> {

	MaterialResource save(MaterialResource materialResource);

	void delete(Integer id);

	Page<MaterialResource> findAll(Pageable pageable);
	
	List<MaterialResource> findAll();

	MaterialResource findById(Integer id);

	List<MaterialResource> findByType(MaterialResourceTypeEnum type);
	
}
