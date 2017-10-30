package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.MaterialResourceDao;
import com.wangbei.dao.impl.jpa.MaterialResourceRepository;
import com.wangbei.entity.MaterialResource;
import com.wangbei.util.enums.MaterialResourceTypeEnum;

/**
 * 素材资源 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class MaterialResourceDaoImpl implements MaterialResourceDao {

	@Autowired
	private MaterialResourceRepository materialResourceRepository;

	@Override
	public MaterialResource createMaterialResource(MaterialResource materialResource) {
		return materialResourceRepository.save(materialResource);
	}

	@Override
	public void deleteMaterialResourceById(Integer id) {
		materialResourceRepository.delete(id);
	}

	@Override
	public MaterialResource updateMaterialResource(MaterialResource materialResource) {
		return materialResourceRepository.save(materialResource);
	}

	@Override
	public MaterialResource retrieveMaterialResourceById(Integer id) {
		return materialResourceRepository.findById(id);
	}

	@Override
	public Page<MaterialResource> pageMaterialResource(int page, int limit) {
		return materialResourceRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<MaterialResource> listMaterialResource() {
		return materialResourceRepository.findAll();
	}

	@Override
	public List<MaterialResource> listMaterialResourceByType(MaterialResourceTypeEnum type) {
		return materialResourceRepository.findByType(type);
	}

}
