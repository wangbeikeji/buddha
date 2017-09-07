package com.wangbei.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.wangbei.entity.Almanac;
import com.wangbei.entity.GlobalSetting;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.util.enums.PushTypeEnum;

@Component
public class PushSchedule {

	private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdfShortDate = new SimpleDateFormat("yyyyMMdd");

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GlobalSettingService globalSettingService;

	@Autowired
	private UserSettingService userSettingService;

	@Autowired
	private JiguangService jiguangService;

	@Autowired
	private AlmanacService almanacService;

	@Value("${custom.isneedpush}")
	private boolean isNeedpush;

	private Timer buddhistFestivalRemindTimer;

	private Timer feastDayRemindTimeTimer;

	@PostConstruct
	public void init() {
		initBuddhistFestivalRemindTask();
		initFeastDayRemindTask();
	}

	public void initBuddhistFestivalRemindTask() {
		if (!isNeedpush) {
			return;
		}
		logger.info("--------------设置佛教节日提醒定点任务--------------");
		GlobalSetting setting = globalSettingService.getCurrentSetting();
		String buddhistFestivalRemindTime = setting.getBuddhistFestivalRemindTime();
		if (buddhistFestivalRemindTime != null && !"".equals(buddhistFestivalRemindTime.trim())) {
			if (buddhistFestivalRemindTimer != null) {
				buddhistFestivalRemindTimer.cancel();
				buddhistFestivalRemindTimer = null;
			}
			try {
				// 计算下次执行时间
				DateTime now = new DateTime();
				String nowRemindStr = sdfDate.format(now.toDate()) + " " + buddhistFestivalRemindTime;
				Date executeDate = sdfTime.parse(nowRemindStr);
				if (executeDate.getTime() <= now.toDate().getTime()) {
					DateTime tomorrow = now.plusHours(24);
					String tomorrowRemindStr = sdfDate.format(tomorrow.toDate()) + " " + buddhistFestivalRemindTime;
					executeDate = sdfTime.parse(tomorrowRemindStr);
				}
				// 开启定点任务
				buddhistFestivalRemindTimer = new Timer();
				buddhistFestivalRemindTimer.schedule(new BuddhistFestivalRemindTask(this), executeDate);
			} catch (Exception ex) {
				throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION,
						new Object[] { "开启佛教节日提醒任务失败_" + ex.getMessage() });
			}
		}
	}

	public void initFeastDayRemindTask() {
		if (!isNeedpush) {
			return;
		}
		logger.info("--------------设置斋日提醒定点任务--------------");
		GlobalSetting setting = globalSettingService.getCurrentSetting();
		String feastDayRemindTime = setting.getFeastDayRemindTime();
		if (feastDayRemindTime != null && !"".equals(feastDayRemindTime.trim())) {
			if (feastDayRemindTimeTimer != null) {
				feastDayRemindTimeTimer.cancel();
				feastDayRemindTimeTimer = null;
			}
			try {
				// 计算下次执行时间
				DateTime now = new DateTime();
				String nowRemindStr = sdfDate.format(now.toDate()) + " " + feastDayRemindTime;
				Date executeDate = sdfTime.parse(nowRemindStr);
				if (executeDate.getTime() <= now.toDate().getTime()) {
					DateTime tomorrow = now.plusHours(24);
					String tomorrowRemindStr = sdfDate.format(tomorrow.toDate()) + " " + feastDayRemindTime;
					executeDate = sdfTime.parse(tomorrowRemindStr);
				}
				// 开启定点任务
				feastDayRemindTimeTimer = new Timer();
				feastDayRemindTimeTimer.schedule(new FeastDayRemindTimeTask(this), executeDate);
			} catch (Exception ex) {
				throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION,
						new Object[] { "开启斋日提醒任务失败_" + ex.getMessage() });
			}
		}
	}

	public class BuddhistFestivalRemindTask extends TimerTask {

		private PushSchedule pushSchedule;

		public BuddhistFestivalRemindTask(PushSchedule pushSchedule) {
			this.pushSchedule = pushSchedule;
		}

		@Override
		public void run() {
			try {
				Date now = new Date();
				String todayStr = sdfShortDate.format(now);
				Almanac almanac = pushSchedule.getAlmanacService().getAlmanacByGregorianCalendar(todayStr);
				if (almanac != null && almanac.getBuddhistCalendar() != null
						&& almanac.getBuddhistCalendar().indexOf("斋") < 0
						&& !"".equals(almanac.getBuddhistCalendar())) {
					PushTypeEnum type = PushTypeEnum.BuddhistFestivalRemind;
					Integer resourceId = 0;
					String title = "佛教节日提醒";
					String alert = almanac.getBuddhistCalendar();
					logger.info("--------------推送佛教节日提醒--------------");
					pushSchedule.getJiguangService().pushBatchNotification(
							pushSchedule.getUserSettingService().getRemindUserByPushType(type), resourceId, title,
							alert, type);
				}
			} finally {
				pushSchedule.initBuddhistFestivalRemindTask();
			}
		}

	}

	public class FeastDayRemindTimeTask extends TimerTask {

		@Autowired
		private PushSchedule pushSchedule;

		public FeastDayRemindTimeTask(PushSchedule pushSchedule) {
			this.pushSchedule = pushSchedule;
		}

		@Override
		public void run() {
			try {
				Date now = new Date();
				String todayStr = sdfShortDate.format(now);
				Almanac almanac = pushSchedule.getAlmanacService().getAlmanacByGregorianCalendar(todayStr);
				if (almanac != null && almanac.getBuddhistCalendar() != null
						&& almanac.getBuddhistCalendar().indexOf("斋") >= 0) {
					PushTypeEnum type = PushTypeEnum.FeastDayRemind;
					Integer resourceId = 0;
					String title = "斋日提醒";
					String alert = almanac.getBuddhistCalendar();
					logger.info("--------------推送斋日提醒消息--------------");
					pushSchedule.getJiguangService().pushBatchNotification(
							pushSchedule.getUserSettingService().getRemindUserByPushType(type), resourceId, title,
							alert, type);
				}
			} finally {
				pushSchedule.initFeastDayRemindTask();
			}
		}

	}

	public JiguangService getJiguangService() {
		return jiguangService;
	}

	public void setJiguangService(JiguangService jiguangService) {
		this.jiguangService = jiguangService;
	}

	public UserSettingService getUserSettingService() {
		return userSettingService;
	}

	public void setUserSettingService(UserSettingService userSettingService) {
		this.userSettingService = userSettingService;
	}

	public AlmanacService getAlmanacService() {
		return almanacService;
	}

	public void setAlmanacService(AlmanacService almanacService) {
		this.almanacService = almanacService;
	}

}
