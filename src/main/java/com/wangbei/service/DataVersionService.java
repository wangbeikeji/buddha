package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.DataVersionDao;
import com.wangbei.entity.DataVersion;

/**
 * 数据版本 Service
 * 
 * @author luomengan
 *
 */
@Service
public class DataVersionService {

	@Autowired
	private DataVersionDao dataVersionDao;

	public DataVersion getDataVersionInfo(Integer id) {
		return dataVersionDao.retrieveDataVersionById(id);
	}

	@Transactional
	public DataVersion addDataVersion(DataVersion dataVersion) {
		return dataVersionDao.createDataVersion(dataVersion);
	}

	@Transactional
	public DataVersion modifyDataVersion(DataVersion dataVersion) {
		return dataVersionDao.updateDataVersion(dataVersion);
	}

	@Transactional
	public void deleteDataVersion(Integer id) {
		dataVersionDao.deleteDataVersionById(id);
	}

	public Page<DataVersion> dataVersions(int page, int limit) {
		return dataVersionDao.pageDataVersion(page, limit);
	}

	public List<DataVersion> list() {
		return dataVersionDao.listDataVersion();
	}

	public String getCurrent() {
		String result = "0.0.1";
		List<DataVersion> list = dataVersionDao.listCurrentVersion();
		if (list != null && list.size() > 0) {
			result = list.get(0).getVersion();
		}
		return result;
	}

}
