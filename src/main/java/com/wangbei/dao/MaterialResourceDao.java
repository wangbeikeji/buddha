package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.MaterialResource;
import com.wangbei.util.enums.MaterialResourceTypeEnum;

/**
 * 素材资源 Dao
 * 
 * @author luomengan
 *
 */
public interface MaterialResourceDao {

	public MaterialResource createMaterialResource(MaterialResource materialResource);

	public void deleteMaterialResourceById(Integer id);

	public MaterialResource updateMaterialResource(MaterialResource materialResource);

	public MaterialResource retrieveMaterialResourceById(Integer id);

	public Page<MaterialResource> pageMaterialResource(int page, int limit);
	
	public List<MaterialResource> listMaterialResource();

	public List<MaterialResource> listMaterialResourceByType(MaterialResourceTypeEnum type);

}
