package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.UserBinding;
import com.wangbei.util.enums.BindingTypeEnum;

/**
 * 用户绑定 Repository
 * 
 * @author luomengan
 *
 */
public interface UserBindingRepository extends Repository<UserBinding, Integer> {

	UserBinding save(UserBinding userBinding);

	void delete(Integer id);

	Page<UserBinding> findAll(Pageable pageable);

	List<UserBinding> findAll();

	UserBinding findById(Integer id);

	UserBinding findByUserIdAndType(Integer userId, BindingTypeEnum type);

	List<UserBinding> findByType(BindingTypeEnum type);

	UserBinding findByBindingAccountAndType(String bindingAccount, BindingTypeEnum type);

}
