package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.GlobalSetting;

/**
 * 全局设置 Repository
 * 
 * @author luomengan
 *
 */
public interface GlobalSettingRepository extends Repository<GlobalSetting, Integer> {

	GlobalSetting save(GlobalSetting globalSetting);

	void delete(Integer id);

	Page<GlobalSetting> findAll(Pageable pageable);
	
	List<GlobalSetting> findAll();

	GlobalSetting findById(Integer id);
	
}
