package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.AppVersionDao;
import com.wangbei.dao.CreaturesDao;
import com.wangbei.entity.AppVersion;
import com.wangbei.entity.Creatures;

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

	@Autowired
	private CreaturesDao creaturesDao;

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
			if (list.get(0).getIsOnline() != null) {
				result = list.get(0).getIsOnline();
			}
		}
		return result;
	}

	public boolean swichOnlineStatus() {
		boolean result = false;
		List<AppVersion> list = appVersionDao.listCurrentVersion();
		if (list != null && list.size() > 0) {
			AppVersion appVersion = list.get(0);
			List<Creatures> creatureList = creaturesDao.listAllCreatures();
			if (appVersion.getIsOnline() != null && appVersion.getIsOnline() == true) {
				appVersion.setIsOnline(false);
				appVersionDao.updateDataVersion(appVersion);
				for (Creatures creature : creatureList) {
					if (creature.getMeritValue() > 0) {
						creature.setIsEnable(false);
						creaturesDao.updateCreatures(creature);
					}
				}
				result = false;
			} else if (appVersion.getIsOnline() != null && appVersion.getIsOnline() == false) {
				appVersion.setIsOnline(true);
				appVersionDao.updateDataVersion(appVersion);
				for (Creatures creature : creatureList) {
					if (creature.getMeritValue() > 0) {
						creature.setIsEnable(true);
						creaturesDao.updateCreatures(creature);
					}
				}
				result = true;
			}
		}
		return result;
	}

}
