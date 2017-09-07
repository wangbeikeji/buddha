package com.wangbei.dao.impl.jpa;

import org.springframework.data.repository.Repository;

import com.wangbei.entity.UserDivination;

/**
 * 用户求签 Repository
 * 
 * @author luomengan
 *
 */
public interface UserDivinationRepository
		extends CurdJpaRepository<UserDivination, Integer>, Repository<UserDivination, Integer> {

}
