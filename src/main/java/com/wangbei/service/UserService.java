package com.wangbei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.AccountDao;
import com.wangbei.dao.TradeDao;
import com.wangbei.dao.UserDao;
import com.wangbei.entity.Account;
import com.wangbei.entity.Trade;
import com.wangbei.entity.User;
import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.TradeWithUserMeritValue;
import com.wangbei.util.enums.TradeStatusEnum;
import com.wangbei.util.enums.TradeTypeEnum;

/**
 * @author yuyidi 2017-07-06 17:36:53
 * @class com.wangbei.service.UserService
 * @description 用户业务服务
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private TradeDao tradeDao;
	@Autowired
	private TradeService tradeService;

	@Transactional
	public User addUser(User user) {
		User checkUser = userDao.fetchUserByPhone(user.getPhone());
		if (checkUser != null) {
			throw new ServiceException(ServiceException.USER_REGISTER_EXIST_EXCEPTION);
		}
		User result = userDao.createUser(user);
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
		return userDao.createUser(user);
	}

	@Transactional
	public User resetPassword(String phone, String password) {
		User user = userDao.fetchUserByPhone(phone);
		if (user == null) {
			throw new ServiceException(ServiceException.USER_PHONE_NOTEXIST_EXCEPTION);
		}

		user.setPassword(password);
		return userDao.updateUser(user);
	}

	@Transactional
	public Trade charge(Integer user, Integer meritValue, Integer type) {
		TradeTypeEnum typeEnum = TradeTypeEnum.getByIndex(type);
		if (!(typeEnum == TradeTypeEnum.CHARGE || typeEnum == TradeTypeEnum.FREELIFE || typeEnum == TradeTypeEnum.MERIT)) {
			throw new ServiceException(ServiceException.CHARGETYPE_NOTMATCH_EXCEPTION);
		}
		return tradeService.trade(user, typeEnum, meritValue);
	}

	@Transactional
	public TradeWithUserMeritValue validateCharge(String tradeNo) {
		Trade trade = tradeDao.retrieveByTradeNo(tradeNo);
		if (trade == null) {
			throw new ServiceException(ServiceException.TRADENO_NOTEXIST_EXCEPTION);
		}
		trade.setStatus(TradeStatusEnum.COMPLETED);

		Account account = accountDao.findByUser(trade.getUserId());
		account.setMeritValue(account.getMeritValue() + trade.getMeritValue());
		accountDao.update(account);

		TradeWithUserMeritValue result = new TradeWithUserMeritValue(trade);
		result.setUserMeritValue(account.getMeritValue());
		return result;
	}

}
