package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.UserLoginLogDao;
import com.wangbei.dao.impl.jpa.UserLoginLogRepository;
import com.wangbei.entity.UserLoginLog;

/**
 * 用户登录日志 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class UserLoginLogDaoImpl implements UserLoginLogDao {

	@Autowired
	private UserLoginLogRepository userLoginLogRepository;

	@Override
	public UserLoginLog createUserLoginLog(UserLoginLog userLoginLog) {
		return userLoginLogRepository.save(userLoginLog);
	}

	@Override
	public void deleteUserLoginLogById(Integer id) {
		userLoginLogRepository.delete(id);
	}

	@Override
	public UserLoginLog updateUserLoginLog(UserLoginLog userLoginLog) {
		return userLoginLogRepository.save(userLoginLog);
	}

	@Override
	public UserLoginLog retrieveUserLoginLogById(Integer id) {
		return userLoginLogRepository.findById(id);
	}

	@Override
	public Page<UserLoginLog> pageUserLoginLog(int page, int limit) {
		return userLoginLogRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<UserLoginLog> listUserLoginLog() {
		return userLoginLogRepository.findAll();
	}

}
