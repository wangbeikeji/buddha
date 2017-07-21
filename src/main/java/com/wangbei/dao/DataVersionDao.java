package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.DataVersion;

/**
 * 数据版本 Dao
 * 
 * @author luomengan
 *
 */
public interface DataVersionDao {

	public DataVersion createDataVersion(DataVersion dataVersion);

	public void deleteDataVersionById(Integer id);

	public DataVersion updateDataVersion(DataVersion dataVersion);

	public DataVersion retrieveDataVersionById(Integer id);

	public Page<DataVersion> pageDataVersion(int page, int limit);
	
	public List<DataVersion> listDataVersion();
	
	public List<DataVersion> listCurrentVersion();

}
