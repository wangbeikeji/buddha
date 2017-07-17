package com.wangbei.service;

import com.wangbei.dao.AccountDao;
import com.wangbei.dao.UserDao;
import com.wangbei.entity.Account;
import com.wangbei.entity.User;
import com.wangbei.exception.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public User addUser(User user) {
		User checkUser = userDao.fetchUserByPhone(user.getPhone());
		if (checkUser != null) {
			throw new ServiceException(ServiceException.USER_REGISTER_EXIST_EXCEPTION);
		}
		User result = userDao.createUser(user);
		if (result != null) {
			//用户注册成功之后为当前用户初始化一个账户信息
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

	@Transactional
	public User modifyUser(User user) {
		return userDao.createUser(user);
	}

	public User resetPassword(String phone, String password) {
		User user = userDao.fetchUserByPhone(phone);
		if (user == null) {
			throw new ServiceException(ServiceException.USER_PHONE_NOTEXIST_EXCEPTION);
		}

		user.setPassword(password);
		return userDao.updateUser(user);
	}
}
