package com.wangbei.dao.impl.jpa;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.User;

/**
 * @author Created by yuyidi on 2017/7/6.
 * @desc
 */
public interface UserRepository extends CurdJpaRepository<User, Integer>, Repository<User, Integer> {

	public User findByPhone(String phone);

	public User findByPhoneAndPassword(String phone, String password);

	@Query("select count(id) from User where creatTime>=?1 and creatTime<?2")
	public Integer staCountOfUser(Date startDate, Date endDate);

}
