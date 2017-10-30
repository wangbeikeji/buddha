package com.wangbei.service;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangbei.dao.AccountDao;
import com.wangbei.dao.UserDao;
import com.wangbei.dao.UserMessageDao;
import com.wangbei.entity.Account;
import com.wangbei.entity.Trade;
import com.wangbei.entity.User;
import com.wangbei.entity.UserMessage;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.TradeWithUserMeritValue;
import com.wangbei.ueditor.ActionEnter;
import com.wangbei.util.RandomUtil;
import com.wangbei.util.enums.GenderEnum;
import com.wangbei.util.enums.PaymentTypeEnum;
import com.wangbei.util.enums.PushTypeEnum;
import com.wangbei.util.enums.TradeTypeEnum;

/**
 * @author yuyidi 2017-07-06 17:36:53
 * @class com.wangbei.service.UserService
 * @description 用户业务服务
 */
@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserDao userDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private TradeService tradeService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserMessageDao userMessageDao;
	@Autowired
	private RestTemplate restTemplate;
	@Value("${custom.outer.resources}")
	private String outerResources;
	@Value("${custom.outer.uploadremote}")
	private String uploadremote;
	@Value("${custom.outer.fileserverurl}")
	private String fileServerUrl;

	public User getUser(Integer id) {
		return userDao.retrieveUserById(id);
	}

	@Transactional
	public User addUser(User user) {
		User checkUser = userDao.fetchUserByPhone(user.getPhone());
		if (checkUser != null) {
			throw new ServiceException(ExceptionEnum.USER_REGISTER_EXIST_EXCEPTION);
		}
		User result = userDao.createUser(user);
		if (user.getGender() == null) {
			user.setGender(GenderEnum.MAN);
		}
		if (result != null) {
			// 用户注册成功之后为当前用户初始化一个账户信息
			accountDao.create(new Account(result.getId(), 0));
		}
		return result;
	}

	public User getUserByPhoneAndPassword(String phone, String password) {
		return userDao.fetchUserByPhoneAndPassword(phone, password);
	}

	public User getUserByPhone(String phone) {
		return userDao.fetchUserByPhone(phone);
	}

	public Integer getUserMeritValue(Integer userId) {
		Integer meritValue = 0;
		Account account = accountDao.findByUser(userId);
		if (account != null) {
			meritValue = account.getMeritValue();
		}
		return meritValue;
	}

	@Transactional
	public User modifyUser(User user) {
		return userDao.updateUser(user);
	}

	@Transactional
	public User resetPassword(String phone, String password) {
		User user = userDao.fetchUserByPhone(phone);
		if (user == null) {
			throw new ServiceException(ExceptionEnum.USER_PHONE_NOTEXIST_EXCEPTION);
		}

		user.setPassword(password);
		return userDao.updateUser(user);
	}

	@Transactional
	public Trade charge(Integer user, Integer meritValue, Integer type) {
		String tradeNo = TradeService.generateTradeNo();
		orderService.generateOrder(user, PaymentTypeEnum.ApplePay, (meritValue / 10d), tradeNo, tradeNo);
		return tradeService.paymentTrade(tradeNo, user, TradeTypeEnum.getByIndex(type), meritValue);
	}

	@Transactional
	public TradeWithUserMeritValue validateCharge(String tradeNo) {
		orderService.completeOrders(tradeNo, null);
		Trade trade = tradeService.completePaymentTrade(tradeNo);
		Account account = accountDao.findByUser(trade.getUserId());
		TradeWithUserMeritValue result = new TradeWithUserMeritValue(trade);
		result.setUserMeritValue(account.getMeritValue());
		return result;
	}

	@Transactional
	public User generateAnonymousUser() {
		User user = new User();
		user.setCreatTime(new Date());
		user.setPhone(RandomUtil.generateRandomCode(9) + RandomUtil.generateRandomCode(2));
		user.setPassword(RandomUtil.generateRandomCode(6));
		user.setGender(GenderEnum.MAN);
		user.setIsAnonymous(true);
		userDao.createUser(user);
		accountDao.create(new Account(user.getId(), 0));

		UserMessage userMessage = new UserMessage();
		userMessage.setCreateTime(new Date());
		userMessage.setTitle("游客用户初始化信息");
		userMessage.setAlert(String.format("尊敬的佛友，欢迎加入华人佛教，您的初始密码为：%s，广善结缘。", user.getPassword()));
		userMessage.setResourceId(user.getId());
		userMessage.setUserId(user.getId());
		userMessage.setType(PushTypeEnum.AnonymousUserInit);
		userMessageDao.createUserMessage(userMessage);
		return user;
	}

	public String uploadHeadPortrait(Integer userId, HttpServletRequest servletRequest) {
		String headPortraitLink = null;
		try {
			servletRequest.setCharacterEncoding("utf-8");
			File file = new File(outerResources + "editorupload");
			String rootPath = file.getParentFile().getAbsolutePath();
			String result = new ActionEnter(servletRequest, rootPath, "uploadimage").exec().toString();
			logger.info("result:{}", result);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode node = objectMapper.readTree(result);
			if (node.get("url") != null) {
				logger.info(outerResources + node.get("url").asText());
				upload(outerResources + node.get("url").asText());
				headPortraitLink = fileServerUrl + node.get("url").asText();
				User user = userDao.retrieveUserById(userId);
				user.setHeadPortraitLink(headPortraitLink);
				userDao.updateUser(user);
			} else {
				throw new RuntimeException(node.get("state").asText());
			}
		} catch (Exception ex) {
			throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION, new Object[] { "上图用户头像失败:" + ex.getMessage() });
		}
		return headPortraitLink;
	}

	public String upload(String url) {
		File file = new File(url);
		FileSystemResource resource = new FileSystemResource(file);
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("file", resource);
		// param.add("fileName",file.getName());
		String result = restTemplate.postForObject(uploadremote, param, String.class);
		logger.info("远程上传结果:{}", result);
		return "You successfully uploaded " + file.getName() + "!";
	}

	public User register(String phone, String password) {
		User check = userDao.fetchUserByPhone(phone);
		if (check != null) {
			throw new ServiceException(ExceptionEnum.USER_REGISTER_EXIST_EXCEPTION);
		}

		User user = new User();
		user.setPhone(phone);
		user.setPassword(password);
		user.setCreatTime(new Date());
		user.setGender(GenderEnum.MAN);
		userDao.createUser(user);
		accountDao.create(new Account(user.getId(), 0));
		return user;
	}

}
