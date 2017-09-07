package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.UserLoginLog;

/**
 * 用户登录日志 Dao
 * 
 * @author luomengan
 *
 */
public interface UserLoginLogDao {

	public UserLoginLog createUserLoginLog(UserLoginLog userLoginLog);

	public void deleteUserLoginLogById(Integer id);

	public UserLoginLog updateUserLoginLog(UserLoginLog userLoginLog);

	public UserLoginLog retrieveUserLoginLogById(Integer id);

	public Page<UserLoginLog> pageUserLoginLog(int page, int limit);
	
	public List<UserLoginLog> listUserLoginLog();

}
