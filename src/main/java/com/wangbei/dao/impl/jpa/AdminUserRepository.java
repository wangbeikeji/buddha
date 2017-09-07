package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.AdminUser;

/**
 * 后台管理用户 Repository
 * 
 * @author luomengan
 *
 */
public interface AdminUserRepository extends Repository<AdminUser, Integer> {

	AdminUser save(AdminUser adminUser);

	void delete(Integer id);

	Page<AdminUser> findAll(Pageable pageable);
	
	List<AdminUser> findAll();

	AdminUser findById(Integer id);

	AdminUser findByUsername(String username);
	
}
