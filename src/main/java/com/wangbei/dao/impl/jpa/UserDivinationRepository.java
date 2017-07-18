package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.UserDivination;

/**
 * 用户求签 Repository
 * 
 * @author luomengan
 *
 */
public interface UserDivinationRepository extends Repository<UserDivination, Integer> {

	public UserDivination save(UserDivination userDivination);

	void delete(Integer id);

	Page<UserDivination> findAll(Pageable pageable);
	
	List<UserDivination> findAll();

	UserDivination findById(Integer id);
	
}
