package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.UserLightLampDao;
import com.wangbei.dao.impl.jpa.UserLightLampRepository;
import com.wangbei.entity.UserLightLamp;

/**
 * 用户点灯 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class UserLightLampDaoImpl implements UserLightLampDao {

	@Autowired
	private UserLightLampRepository userLightLampRepository;

	@Override
	public UserLightLamp createUserLightLamp(UserLightLamp userLightLamp) {
		return userLightLampRepository.save(userLightLamp);
	}

	@Override
	public void deleteUserLightLampById(Integer id) {
		userLightLampRepository.delete(id);
	}

	@Override
	public UserLightLamp updateUserLightLamp(UserLightLamp userLightLamp) {
		return userLightLampRepository.save(userLightLamp);
	}

	@Override
	public UserLightLamp retrieveUserLightLampById(Integer id) {
		return userLightLampRepository.findById(id);
	}

	@Override
	public Page<UserLightLamp> pageUserLightLamp(int page, int limit) {
		return userLightLampRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<UserLightLamp> listUserLightLamp() {
		return userLightLampRepository.findAll();
	}

	@Override
	public List<UserLightLamp> listUserLightLampByUserId(int userId) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "expireTime"));
		return userLightLampRepository.findByUserId(userId, sort);
	}

}
