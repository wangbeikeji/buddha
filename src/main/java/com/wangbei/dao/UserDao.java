package com.wangbei.dao;

import java.util.Date;

import com.wangbei.entity.User;

/**
 * @author yuyidi 2017-07-06 17:33:23
 * @class com.wangbei.dao.UserDao
 * @description 用户数据访问接口
 */
public interface UserDao {
	
	public User retrieveUserById(Integer id);
	
	public User createUser(User user);
	
	public User updateUser(User user);

	public User fetchUserByPhone(String phone);

	public User fetchUserByPhoneAndPassword(String phone, String password);

	public Integer staCountOfUser(Date startDate, Date endDate);
}
