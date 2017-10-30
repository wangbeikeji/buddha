package com.wangbei.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.BuddhistMusicDao;
import com.wangbei.dao.KnowledgeDao;
import com.wangbei.dao.SutraDao;
import com.wangbei.dao.UserBindingDao;
import com.wangbei.dao.UserMessageDao;
import com.wangbei.entity.BuddhistMusic;
import com.wangbei.entity.Knowledge;
import com.wangbei.entity.Sutra;
import com.wangbei.entity.UserBinding;
import com.wangbei.entity.UserMessage;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.util.enums.BindingTypeEnum;
import com.wangbei.util.enums.KnowledgeTypeEnum;
import com.wangbei.util.enums.PushTypeEnum;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送
 * 
 * @author luomengan
 *
 */
@Service
public class JiguangService {

	// private static final String APP_KEY = "fa83f24ff4b2c0d5199f699b";

	// private static final String MASTER_SECRET = "efb70dc2278af329f2a4a965";

	private static final String APP_KEY = "c432afcfbec046f220595ec0";

	private static final String MASTER_SECRET = "59ffbebad003de4fa7fe9b06";
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserBindingDao userBindingDao;

	@Autowired
	private SutraDao sutraDao;

	@Autowired
	private BuddhistMusicDao buddhistMusicDao;

	@Autowired
	private KnowledgeDao knowledgeDao;

	@Autowired
	private UserMessageDao userMessageDao;

	@Autowired
	private UserSettingService userSettingService;

	public void pushSingleUser(Integer userId) {
		UserBinding userBinding = userBindingDao.retrieveUserBindingByUserIdAndType(userId,
				BindingTypeEnum.JiguangPush);
		if (userBinding != null && userBinding.getBindingAccount() != null && !"".equals(userBinding)) {
			pushNotification(buildPushObjectForSingle(userBinding.getBindingAccount()));
		}
	}

	private PushPayload buildPushObjectForSingle(String registrationId) {
		Builder builder = PushPayload.newBuilder();
		builder.setPlatform(Platform.all());
		builder.setAudience(
				Audience.newBuilder().addAudienceTarget(AudienceTarget.registrationId(registrationId)).build());
		Map<String, String> extras = new HashMap<>();
		extras.put("姓名", "张三");
		extras.put("年龄", "21");
		extras.put("住址", "广东省深圳市");
		// builder.setNotification(Notification.android("今天是斋日，忌荤，清淡为主！",
		// "华人佛教", extras));
		builder.setNotification(Notification.ios("今天是斋日，忌荤，清淡为主！", extras));
		return builder.build();
	}

	private PushPayload buildPushObjectByRegistrationId(String[] registrationIdArr, String title, String alert,
			Integer resourceId, Integer pushType) {
		Builder builder = PushPayload.newBuilder();
		builder.setPlatform(Platform.all());
		builder.setAudience(
				Audience.newBuilder().addAudienceTarget(AudienceTarget.registrationId(registrationIdArr)).build());
		Map<String, String> extras = new HashMap<>();
		extras.put("resourceId", String.valueOf(resourceId));
		extras.put("pushType", String.valueOf(pushType));

		builder.setNotification(Notification.newBuilder()
				.addPlatformNotification(IosNotification.newBuilder().setAlert(alert).addExtras(extras).build())
				.addPlatformNotification(
						AndroidNotification.newBuilder().setTitle(title).setAlert(alert).addExtras(extras).build())
				.build());
		builder.setOptions(Options.newBuilder().setApnsProduction(true).build());
		return builder.build();
	}

	private PushPayload buildPushObjectForAllDevice() {
		Builder builder = PushPayload.newBuilder();
		// builder.setPlatform(Platform.all());
		builder.setPlatform(Platform.ios());
		builder.setAudience(Audience.newBuilder().setAll(true).build());
		Map<String, String> extras = new HashMap<>();
		extras.put("姓名", "张三");
		extras.put("年龄", "21");
		extras.put("住址", "广东省深圳市");

		builder.setNotification(Notification.ios("今天是斋日，忌荤，清淡为主！", extras));
		/*
		 * builder.setNotification(Notification.android( "今天是斋日，忌荤，清淡为主！",
		 * "华人佛教", extras));
		 */
		// ture为生产环境，false为开发环境
		builder.setOptions(Options.newBuilder().setApnsProduction(true).build());
		return builder.build();
	}

	/**
	 * 推送通知
	 */
	private void pushNotification(PushPayload payload) {
		JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
		try {
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);
			throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION,
					new Object[] { "Connection error, should retry later" });
		} catch (APIRequestException e) {
			logger.error("Should review the error, and fix the request", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION,
					new Object[] { "Should review the error, and fix the request" });
		}
	}

	public void pushAllDevice() {
		pushNotification(buildPushObjectForAllDevice());
	}

	@Transactional
	public void pushBatchNotification(Map<Integer, String> userIdRegistrationIdMap, Integer resourceId, String title,
			String alert, PushTypeEnum type) {
		int size = userIdRegistrationIdMap.size();
		// 推送
		if (size > 0) {
			pushNotification(buildPushObjectByRegistrationId(userIdRegistrationIdMap.values().toArray(new String[size]),
					title, alert, resourceId, type.getIndex()));
			// 保存用户推送记录
			for (Map.Entry<Integer, String> entry : userIdRegistrationIdMap.entrySet()) {
				UserMessage userMessage = new UserMessage();
				userMessage.setCreateTime(new Date());
				userMessage.setAlert(alert);
				userMessage.setResourceId(resourceId);
				userMessage.setTitle(title);
				userMessage.setType(type);
				userMessage.setUserId(entry.getKey());
				userMessageDao.createUserMessage(userMessage);
			}
		}
	}

	@Transactional
	public void adminManualPush(Integer resourceId, String title, String alert, PushTypeEnum type) {
		if (title == null || "".equals(title)) {
			title = "华人佛教";
		}
		if (type == PushTypeEnum.SutraRemind) {
			Sutra sutra = sutraDao.retrieveSutraById(resourceId);
			if (sutra == null) {
				throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION, new Object[] { "经书id无效!" });
			}
			if (alert == null || "".equals(alert)) {
				alert = sutra.getTitle() + "：" + sutra.getSummary();
			}
		} else if (type == PushTypeEnum.MusicRemind) {
			BuddhistMusic music = buddhistMusicDao.retrieveBuddhistMusicById(resourceId);
			if (music == null) {
				throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION, new Object[] { "佛音id无效!" });
			}
			if (alert == null || "".equals(alert)) {
				alert = music.getName();
			}
		} else if (type == PushTypeEnum.InformationRemind) {
			Knowledge knowledge = knowledgeDao.retrieveKnowledgeById(resourceId);
			if (knowledge == null || knowledge.getType() != KnowledgeTypeEnum.INFORMATION) {
				throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION, new Object[] { "资讯id无效!" });
			}
			if (alert == null || "".equals(alert)) {
				alert = knowledge.getTitle();
			}
		} else {
			throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION, new Object[] { "推送类型无效!" });
		}

		// 推送
		pushBatchNotification(userSettingService.getRemindUserByPushType(type), resourceId, title, alert, type);
	}

}
