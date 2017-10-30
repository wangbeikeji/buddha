package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.UserBindingDao;
import com.wangbei.dao.impl.jpa.UserBindingRepository;
import com.wangbei.entity.UserBinding;
import com.wangbei.util.enums.BindingTypeEnum;

/**
 * 用户绑定 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class UserBindingDaoImpl implements UserBindingDao {

	@Autowired
	private UserBindingRepository userBindingRepository;

	@Override
	public UserBinding createUserBinding(UserBinding userBinding) {
		return userBindingRepository.save(userBinding);
	}

	@Override
	public void deleteUserBindingById(Integer id) {
		userBindingRepository.delete(id);
	}

	@Override
	public UserBinding updateUserBinding(UserBinding userBinding) {
		return userBindingRepository.save(userBinding);
	}

	@Override
	public UserBinding retrieveUserBindingById(Integer id) {
		return userBindingRepository.findById(id);
	}

	@Override
	public Page<UserBinding> pageUserBinding(int page, int limit) {
		return userBindingRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<UserBinding> listUserBinding() {
		return userBindingRepository.findAll();
	}

	@Override
	public UserBinding retrieveUserBindingByUserIdAndType(Integer userId, BindingTypeEnum type) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "bindingTime"));
		List<UserBinding> list = userBindingRepository.findByUserIdAndType(userId, type, sort);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<UserBinding> listUserBindingByType(BindingTypeEnum type) {
		return userBindingRepository.findByType(type);
	}

	@Override
	public UserBinding retrieveUserBindingByBindingAccountAndType(String bindingAccount, BindingTypeEnum type) {
		return userBindingRepository.findByBindingAccountAndType(bindingAccount, type);
	}

}
