package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.AdminUser;

/**
 * 后台管理用户 Dao
 * 
 * @author luomengan
 *
 */
public interface AdminUserDao {

	public AdminUser createAdminUser(AdminUser adminUser);

	public void deleteAdminUserById(Integer id);

	public AdminUser updateAdminUser(AdminUser adminUser);

	public AdminUser retrieveAdminUserById(Integer id);
	
	public AdminUser retrieveAdminUserByUsername(String username);

	public Page<AdminUser> pageAdminUser(int page, int limit);
	
	public List<AdminUser> listAdminUser();

}
