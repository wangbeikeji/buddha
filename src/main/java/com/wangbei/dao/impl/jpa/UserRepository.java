package com.wangbei.dao.impl.jpa;

import org.springframework.data.repository.Repository;

import com.wangbei.entity.User;

/**
 * @author Created by yuyidi on 2017/7/6.
 * @desc
 */
public interface UserRepository extends Repository<User, Integer> {
	
	public User findById(Integer id);
	
	public User save(User user);

	public User findByPhone(String phone);

	public User findByPhoneAndPassword(String phone, String password);
}
