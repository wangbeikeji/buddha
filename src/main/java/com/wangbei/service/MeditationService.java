package com.wangbei.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangbei.dao.MaterialResourceDao;
import com.wangbei.dao.UserMeditationRecordDao;
import com.wangbei.dao.UserMeditationSettingDao;
import com.wangbei.entity.MaterialResource;
import com.wangbei.entity.UserMeditationRecord;
import com.wangbei.entity.UserMeditationSetting;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.UserDayMeditationRecord;
import com.wangbei.pojo.UserMeditationSettingInfo;
import com.wangbei.util.DateTimeUtil;

/**
 * 禅修 Service
 * 
 * @author luomengan
 *
 */
@Service
public class MeditationService {

	@Autowired
	private MaterialResourceDao materialResourceDao;

	@Autowired
	private UserMeditationRecordDao recordDao;

	@Autowired
	private UserMeditationSettingDao settingDao;

	public UserMeditationSettingInfo getUserSetting(int userId) {
		List<UserMeditationSetting> list = settingDao.listUserMeditationSettingByUserId(userId);
		if (list != null && list.size() > 0) {
			UserMeditationSetting setting = list.get(0);
			MaterialResource resource = materialResourceDao
					.retrieveMaterialResourceById(setting.getBgMusicMaterialResourceId());

			UserMeditationSettingInfo result = new UserMeditationSettingInfo();
			result.setId(setting.getId());
			result.setUserId(setting.getUserId());
			result.setSettingTime(setting.getSettingTime());
			result.setMaterialResourceId(resource.getId());
			result.setMaterialResourceName(resource.getName());
			result.setMaterialResourceLink(resource.getLink());
			return result;
		}
		return null;
	}

	public UserMeditationSettingInfo modifyUserSetting(int userId, Integer materialResourceId) {
		MaterialResource resource = materialResourceDao.retrieveMaterialResourceById(materialResourceId);
		if (resource == null) {
			throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION, new Object[] { "素材id无效!" });
		}

		UserMeditationSetting setting = null;
		List<UserMeditationSetting> list = settingDao.listUserMeditationSettingByUserId(userId);
		if (list != null && list.size() > 0) {
			setting = list.get(0);
			setting.setBgMusicMaterialResourceId(materialResourceId);
			setting.setSettingTime(new Date());
			settingDao.updateUserMeditationSetting(setting);
		} else {
			setting = new UserMeditationSetting();
			setting.setBgMusicMaterialResourceId(materialResourceId);
			setting.setSettingTime(new Date());
			setting.setUserId(userId);
			settingDao.createUserMeditationSetting(setting);
		}

		UserMeditationSettingInfo result = new UserMeditationSettingInfo();
		result.setId(setting.getId());
		result.setUserId(setting.getUserId());
		result.setSettingTime(setting.getSettingTime());
		result.setMaterialResourceId(resource.getId());
		result.setMaterialResourceName(resource.getName());
		result.setMaterialResourceLink(resource.getLink());
		return result;
	}

	public UserMeditationRecord addUserMeditationRecord(int userId, String startTimeStr, String endTimeStr) {
		try {
			Date startTime = DateTimeUtil.getSdfTimestampDate().parse(startTimeStr);
			Date endTime = DateTimeUtil.getSdfTimestampDate().parse(endTimeStr);

			UserMeditationRecord record = new UserMeditationRecord();
			record.setUserId(userId);
			record.setStartTime(startTime);
			record.setEndTime(endTime);
			return recordDao.createUserMeditationRecord(record);
		} catch (ParseException ex) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public UserDayMeditationRecord staUserDayMeditationRecord(Integer userId, String day) {
		try {
			UserDayMeditationRecord result = new UserDayMeditationRecord();
			Date startTime = DateTimeUtil.getSdfDate().parse(day);
			Date endTime = DateTimeUtil.getNextDate(startTime);
			List<UserMeditationRecord> list = recordDao.listUserMeditationRecordByUserIdAndBettenTime(userId, startTime,
					endTime);
			if (list != null && list.size() > 0) {
				int times = 0;
				int newestDuration = 0;
				int totalDuration = 0;
				for (int i = 0; i < list.size(); i++) {
					UserMeditationRecord record = list.get(i);
					Date meditationStartTime = record.getStartTime();
					Date meditationEndTime = record.getEndTime();
					if (meditationStartTime.getTime() < startTime.getTime()) {
						meditationStartTime = startTime;
					}
					if (meditationEndTime.getTime() > endTime.getTime()) {
						meditationEndTime = endTime;
					}
					if (i == 0) {
						newestDuration += (meditationEndTime.getTime() - meditationStartTime.getTime()) / 1000;
					}
					times++;
					totalDuration += (meditationEndTime.getTime() - meditationStartTime.getTime()) / 1000;
				}

				result.setTimes(times);
				result.setNewestDuration(newestDuration);
				result.setTotalDuration(totalDuration);
			}
			return result;
		} catch (ParseException ex) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

}
