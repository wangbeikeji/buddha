package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.GlobalSetting;

/**
 * 全局设置 Dao
 * 
 * @author luomengan
 *
 */
public interface GlobalSettingDao {

	public GlobalSetting createGlobalSetting(GlobalSetting globalSetting);

	public void deleteGlobalSettingById(Integer id);

	public GlobalSetting updateGlobalSetting(GlobalSetting globalSetting);

	public GlobalSetting retrieveGlobalSettingById(Integer id);

	public Page<GlobalSetting> pageGlobalSetting(int page, int limit);
	
	public List<GlobalSetting> listGlobalSetting();

}
