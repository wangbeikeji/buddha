package com.wangbei.dao.impl.jpa;

import com.wangbei.entity.User;
import org.springframework.data.repository.Repository;

/**
 * @author Created by yuyidi on 2017/7/6.
 * @desc
 */
public interface UserRepository extends Repository<User, Integer> {
	public User save(User user);

	public User findByPhone(String phone);

	public User findByPhoneAndPassword(String phone, String password);
}
