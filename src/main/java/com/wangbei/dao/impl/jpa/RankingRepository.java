package com.wangbei.dao.impl.jpa;

import org.springframework.data.repository.Repository;

import com.wangbei.entity.User;

public interface RankingRepository extends CurdJpaRepository<User, Integer>, Repository<User, Integer> {

}
