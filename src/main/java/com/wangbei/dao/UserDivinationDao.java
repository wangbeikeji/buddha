package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.UserDivination;

/**
 * 用户求签 Dao
 * 
 * @author luomengan
 *
 */
public interface UserDivinationDao {

	public UserDivination createUserDivination(UserDivination userDivination);

	public void deleteUserDivinationById(Integer id);

	public UserDivination updateUserDivination(UserDivination userDivination);

	public UserDivination retrieveUserDivinationById(Integer id);

	public Page<UserDivination> pageUserDivination(int page, int limit);
	
	public List<UserDivination> listUserDivination();

}
