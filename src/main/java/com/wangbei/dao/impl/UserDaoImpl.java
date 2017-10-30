package com.wangbei.dao.impl;

import com.wangbei.dao.UserDao;
import com.wangbei.dao.impl.jpa.UserRepository;
import com.wangbei.entity.User;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author yuyidi 2017-07-06 17:35:37
 * @class com.wangbei.dao.impl.UserDaoImpl
 * @description 用户数据访问实现
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User fetchUserByPhoneAndPassword(String phone, String password) {
		return userRepository.findByPhoneAndPassword(phone, password);
	}

	@Override
	public User fetchUserByPhone(String phone) {
		return userRepository.findByPhone(phone);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User retrieveUserById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public Integer staCountOfUser(Date startDate, Date endDate) {
		return userRepository.staCountOfUser(startDate, endDate, false);
	}
}
