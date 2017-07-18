package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.UserDivinationDao;
import com.wangbei.dao.impl.jpa.UserDivinationRepository;
import com.wangbei.entity.UserDivination;

/**
 * 用户求签 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class UserDivinationDaoImpl implements UserDivinationDao {

	@Autowired
	private UserDivinationRepository userDivinationRepository;

	@Override
	public UserDivination createUserDivination(UserDivination userDivination) {
		return userDivinationRepository.save(userDivination);
	}

	@Override
	public void deleteUserDivinationById(Integer id) {
		userDivinationRepository.delete(id);
	}

	@Override
	public UserDivination updateUserDivination(UserDivination userDivination) {
		return userDivinationRepository.save(userDivination);
	}

	@Override
	public UserDivination retrieveUserDivinationById(Integer id) {
		return userDivinationRepository.findById(id);
	}

	@Override
	public Page<UserDivination> pageUserDivination(int page, int limit) {
		return userDivinationRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<UserDivination> listUserDivination() {
		return userDivinationRepository.findAll();
	}

}
