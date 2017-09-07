package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.UserLoginLog;

/**
 * 用户登录日志 Repository
 * 
 * @author luomengan
 *
 */
public interface UserLoginLogRepository extends Repository<UserLoginLog, Integer> {

	UserLoginLog save(UserLoginLog userLoginLog);

	void delete(Integer id);

	Page<UserLoginLog> findAll(Pageable pageable);
	
	List<UserLoginLog> findAll();

	UserLoginLog findById(Integer id);
	
}
