package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.AppVersionDao;
import com.wangbei.entity.AppVersion;

/**
 * 数据版本 Service
 * 
 * @author luomengan
 *
 */
@Service
public class AppVersionService {

	@Autowired
	private AppVersionDao appVersionDao;

	public AppVersion getDataVersionInfo(Integer id) {
		return appVersionDao.retrieveDataVersionById(id);
	}

	@Transactional
	public AppVersion addDataVersion(AppVersion appVersion) {
		return appVersionDao.createDataVersion(appVersion);
	}

	@Transactional
	public AppVersion modifyDataVersion(AppVersion appVersion) {
		return appVersionDao.updateDataVersion(appVersion);
	}

	@Transactional
	public void deleteDataVersion(Integer id) {
		appVersionDao.deleteDataVersionById(id);
	}

	public Page<AppVersion> appVersions(int page, int limit) {
		return appVersionDao.pageDataVersion(page, limit);
	}

	public List<AppVersion> list() {
		return appVersionDao.listDataVersion();
	}

	public Integer getCurrent() {
		Integer result = 1;
		List<AppVersion> list = appVersionDao.listCurrentVersion();
		if (list != null && list.size() > 0) {
			result = list.get(0).getVersion();
		}
		return result;
	}

	public boolean isOnline() {
		boolean result = false;
		List<AppVersion> list = appVersionDao.listCurrentVersion();
		if (list != null && list.size() > 0) {
			result = list.get(0).getIsOnline();
		}
		return result;
	}

}
