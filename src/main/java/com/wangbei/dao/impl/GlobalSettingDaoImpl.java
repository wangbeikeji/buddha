package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.GlobalSettingDao;
import com.wangbei.dao.impl.jpa.GlobalSettingRepository;
import com.wangbei.entity.GlobalSetting;

/**
 * 全局设置 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class GlobalSettingDaoImpl implements GlobalSettingDao {

	@Autowired
	private GlobalSettingRepository globalSettingRepository;

	@Override
	public GlobalSetting createGlobalSetting(GlobalSetting globalSetting) {
		return globalSettingRepository.save(globalSetting);
	}

	@Override
	public void deleteGlobalSettingById(Integer id) {
		globalSettingRepository.delete(id);
	}

	@Override
	public GlobalSetting updateGlobalSetting(GlobalSetting globalSetting) {
		return globalSettingRepository.save(globalSetting);
	}

	@Override
	public GlobalSetting retrieveGlobalSettingById(Integer id) {
		return globalSettingRepository.findById(id);
	}

	@Override
	public Page<GlobalSetting> pageGlobalSetting(int page, int limit) {
		return globalSettingRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<GlobalSetting> listGlobalSetting() {
		return globalSettingRepository.findAll();
	}

}
