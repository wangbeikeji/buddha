package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.UserLightLampDao;
import com.wangbei.entity.UserLightLamp;

/**
 * 用户点灯 Service
 * 
 * @author luomengan
 *
 */
@Service
public class UserLightLampService {

	@Autowired
	private UserLightLampDao userLightLampDao;

	public UserLightLamp getUserLightLampInfo(Integer id) {
		return userLightLampDao.retrieveUserLightLampById(id);
	}

	@Transactional
	public UserLightLamp addUserLightLamp(UserLightLamp userLightLamp) {
		return userLightLampDao.createUserLightLamp(userLightLamp);
	}

	@Transactional
	public UserLightLamp modifyUserLightLamp(UserLightLamp userLightLamp) {
		return userLightLampDao.updateUserLightLamp(userLightLamp);
	}

	@Transactional
	public void deleteUserLightLamp(Integer id) {
		userLightLampDao.deleteUserLightLampById(id);
	}
	
	@Transactional
	public void deleteUserLightLamps(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					userLightLampDao.deleteUserLightLampById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<UserLightLamp> userLightLamps(int page, int limit) {
		return userLightLampDao.pageUserLightLamp(page, limit);
	}
	
	public List<UserLightLamp> list() {
		return userLightLampDao.listUserLightLamp();
	}

}
