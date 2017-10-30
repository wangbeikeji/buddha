package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.UserLightLamp;

/**
 * 用户点灯 Repository
 * 
 * @author luomengan
 *
 */
public interface UserLightLampRepository extends Repository<UserLightLamp, Integer> {

	UserLightLamp save(UserLightLamp userLightLamp);

	void delete(Integer id);

	Page<UserLightLamp> findAll(Pageable pageable);

	List<UserLightLamp> findAll();

	UserLightLamp findById(Integer id);

	List<UserLightLamp> findByUserId(int userId, Sort sort);

}
