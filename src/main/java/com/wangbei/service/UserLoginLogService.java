package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.UserLoginLogDao;
import com.wangbei.entity.UserLoginLog;

/**
 * 用户登录日志 Service
 * 
 * @author luomengan
 *
 */
@Service
public class UserLoginLogService {

	@Autowired
	private UserLoginLogDao userLoginLogDao;

	public UserLoginLog getUserLoginLogInfo(Integer id) {
		return userLoginLogDao.retrieveUserLoginLogById(id);
	}

	@Transactional
	public UserLoginLog addUserLoginLog(UserLoginLog userLoginLog) {
		return userLoginLogDao.createUserLoginLog(userLoginLog);
	}

	@Transactional
	public UserLoginLog modifyUserLoginLog(UserLoginLog userLoginLog) {
		return userLoginLogDao.updateUserLoginLog(userLoginLog);
	}

	@Transactional
	public void deleteUserLoginLog(Integer id) {
		userLoginLogDao.deleteUserLoginLogById(id);
	}
	
	@Transactional
	public void deleteUserLoginLogs(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					userLoginLogDao.deleteUserLoginLogById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<UserLoginLog> userLoginLogs(int page, int limit) {
		return userLoginLogDao.pageUserLoginLog(page, limit);
	}
	
	public List<UserLoginLog> list() {
		return userLoginLogDao.listUserLoginLog();
	}

}
