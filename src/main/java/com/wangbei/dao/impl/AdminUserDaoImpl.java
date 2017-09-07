package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.AdminUserDao;
import com.wangbei.dao.impl.jpa.AdminUserRepository;
import com.wangbei.entity.AdminUser;

/**
 * 后台管理用户 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class AdminUserDaoImpl implements AdminUserDao {

	@Autowired
	private AdminUserRepository adminUserRepository;

	@Override
	public AdminUser createAdminUser(AdminUser adminUser) {
		return adminUserRepository.save(adminUser);
	}

	@Override
	public void deleteAdminUserById(Integer id) {
		adminUserRepository.delete(id);
	}

	@Override
	public AdminUser updateAdminUser(AdminUser adminUser) {
		return adminUserRepository.save(adminUser);
	}

	@Override
	public AdminUser retrieveAdminUserById(Integer id) {
		return adminUserRepository.findById(id);
	}

	@Override
	public Page<AdminUser> pageAdminUser(int page, int limit) {
		return adminUserRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<AdminUser> listAdminUser() {
		return adminUserRepository.findAll();
	}

	@Override
	public AdminUser retrieveAdminUserByUsername(String username) {
		return adminUserRepository.findByUsername(username);
	}

}
