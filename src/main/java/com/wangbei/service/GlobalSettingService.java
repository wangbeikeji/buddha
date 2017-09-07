package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.GlobalSettingDao;
import com.wangbei.entity.GlobalSetting;

/**
 * 全局设置 Service
 * 
 * @author luomengan
 *
 */
@Service
public class GlobalSettingService {

	@Autowired
	private GlobalSettingDao globalSettingDao;
	
	@Autowired
	private PushSchedule pushSchedule;

	public GlobalSetting getGlobalSettingInfo(Integer id) {
		return globalSettingDao.retrieveGlobalSettingById(id);
	}

	@Transactional
	public GlobalSetting addGlobalSetting(GlobalSetting globalSetting) {
		return globalSettingDao.createGlobalSetting(globalSetting);
	}

	@Transactional
	public GlobalSetting modifyGlobalSetting(GlobalSetting globalSetting) {
		return globalSettingDao.updateGlobalSetting(globalSetting);
	}

	@Transactional
	public void deleteGlobalSetting(Integer id) {
		globalSettingDao.deleteGlobalSettingById(id);
	}

	@Transactional
	public void deleteGlobalSettings(String ids) {
		if (ids != null) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				if (!"".equals(id.trim())) {
					globalSettingDao.deleteGlobalSettingById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<GlobalSetting> globalSettings(int page, int limit) {
		return globalSettingDao.pageGlobalSetting(page, limit);
	}

	public List<GlobalSetting> list() {
		return globalSettingDao.listGlobalSetting();
	}

	@Transactional
	public GlobalSetting getCurrentSetting() {
		List<GlobalSetting> list = globalSettingDao.listGlobalSetting();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			GlobalSetting globalSetting = new GlobalSetting();
			globalSettingDao.createGlobalSetting(globalSetting);
			return globalSetting;
		}
	}

	@Transactional
	public GlobalSetting updateCurrentSetting(String buddhistFestivalRemindTime, String feastDayRemindTime) {
		GlobalSetting globalSetting = getCurrentSetting();
		globalSetting.setBuddhistFestivalRemindTime(buddhistFestivalRemindTime);
		globalSetting.setFeastDayRemindTime(feastDayRemindTime);
		globalSettingDao.updateGlobalSetting(globalSetting);
		return globalSetting;
	}

	@Transactional
	public GlobalSetting updateBuddhistFestivalRemindTime(String buddhistFestivalRemindTime) {
		GlobalSetting globalSetting = getCurrentSetting();
		if (buddhistFestivalRemindTime != null && !"".equals(buddhistFestivalRemindTime.trim())) {
			globalSetting.setBuddhistFestivalRemindTime(buddhistFestivalRemindTime);
			globalSettingDao.updateGlobalSetting(globalSetting);
			
			pushSchedule.initBuddhistFestivalRemindTask();
		}
		return globalSetting;
	}

	@Transactional
	public GlobalSetting updateFeastDayRemindTime(String feastDayRemindTime) {
		GlobalSetting globalSetting = getCurrentSetting();
		if (feastDayRemindTime != null && !"".equals(feastDayRemindTime)) {
			globalSetting.setFeastDayRemindTime(feastDayRemindTime);
			globalSettingDao.updateGlobalSetting(globalSetting);
			
			pushSchedule.initFeastDayRemindTask();
		}
		return globalSetting;
	}

}
