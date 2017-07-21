package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.AppVersion;

/**
 * 数据版本 Dao
 * 
 * @author luomengan
 *
 */
public interface AppVersionDao {

	public AppVersion createDataVersion(AppVersion appVersion);

	public void deleteDataVersionById(Integer id);

	public AppVersion updateDataVersion(AppVersion appVersion);

	public AppVersion retrieveDataVersionById(Integer id);

	public Page<AppVersion> pageDataVersion(int page, int limit);
	
	public List<AppVersion> listDataVersion();
	
	public List<AppVersion> listCurrentVersion();

}
