package com.wangbei.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.UserBindingDao;
import com.wangbei.entity.UserBinding;
import com.wangbei.util.enums.BindingTypeEnum;

/**
 * 用户绑定 Service
 * 
 * @author luomengan
 *
 */
@Service
public class UserBindingService {

	@Autowired
	private UserBindingDao userBindingDao;

	public UserBinding getUserBindingInfo(Integer id) {
		return userBindingDao.retrieveUserBindingById(id);
	}

	@Transactional
	public UserBinding addUserBinding(UserBinding userBinding) {
		return userBindingDao.createUserBinding(userBinding);
	}

	@Transactional
	public UserBinding modifyUserBinding(UserBinding userBinding) {
		return userBindingDao.updateUserBinding(userBinding);
	}

	@Transactional
	public void deleteUserBinding(Integer id) {
		userBindingDao.deleteUserBindingById(id);
	}

	@Transactional
	public void deleteUserBindings(String ids) {
		if (ids != null) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				if (!"".equals(id.trim())) {
					userBindingDao.deleteUserBindingById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<UserBinding> userBindings(int page, int limit) {
		return userBindingDao.pageUserBinding(page, limit);
	}

	public List<UserBinding> list() {
		return userBindingDao.listUserBinding();
	}

	public UserBinding bindAccount(Integer userId, BindingTypeEnum type, String bindingAccount) {
		if (bindingAccount == null || "".equals(bindingAccount.trim())) {
			return null;
		}
		UserBinding userBinding = userBindingDao.retrieveUserBindingByUserIdAndType(userId, type);
		if (userBinding == null) {
			userBinding = new UserBinding();
			userBinding.setBindingAccount(bindingAccount);
			userBinding.setBindingTime(new Date());
			userBinding.setType(type);
			userBinding.setUserId(userId);
			userBindingDao.createUserBinding(userBinding);
		} else {
			userBinding.setBindingAccount(bindingAccount);
			userBinding.setBindingTime(new Date());
			userBindingDao.updateUserBinding(userBinding);
		}
		return userBinding;
	}

}
