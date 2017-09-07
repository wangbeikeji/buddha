package com.wangbei.dao.impl.jpa;

import org.springframework.data.repository.Repository;

import com.wangbei.entity.Rune;

/**
 * 符文 Repository
 * 
 * @author luomengan
 *
 */
public interface RuneRepository extends CurdJpaRepository<Rune, Integer>, Repository<Rune, Integer> {

}
