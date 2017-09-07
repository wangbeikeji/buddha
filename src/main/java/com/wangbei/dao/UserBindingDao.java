package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.UserBinding;
import com.wangbei.util.enums.BindingTypeEnum;

/**
 * 用户绑定 Dao
 * 
 * @author luomengan
 *
 */
public interface UserBindingDao {

	public UserBinding createUserBinding(UserBinding userBinding);

	public void deleteUserBindingById(Integer id);

	public UserBinding updateUserBinding(UserBinding userBinding);

	public UserBinding retrieveUserBindingById(Integer id);
	
	public UserBinding retrieveUserBindingByUserIdAndType(Integer userId, BindingTypeEnum type);
	
	public UserBinding retrieveUserBindingByBindingAccountAndType(String bindingAccount, BindingTypeEnum type);

	public Page<UserBinding> pageUserBinding(int page, int limit);
	
	public List<UserBinding> listUserBinding();
	
	public List<UserBinding> listUserBindingByType(BindingTypeEnum type);

}
