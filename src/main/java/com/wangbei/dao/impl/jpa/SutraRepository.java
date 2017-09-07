package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Sutra;
import com.wangbei.util.enums.SutraTypeEnum;

/**
 * 经书 Repository
 * 
 * @author luomengan
 *
 */
public interface SutraRepository extends CurdJpaRepository<Sutra, Integer>, Repository<Sutra, Integer> {

	List<Sutra> findAll(Sort sort);

	List<Sutra> findByIsEnable(boolean isEnable, Sort sort);

	Page<Sutra> findByIsEnable(boolean isEnable, Pageable pageable);

	Page<Sutra> findByTypeAndIsEnable(SutraTypeEnum type, boolean isEnable, Pageable pageable);

	List<Sutra> findByType(SutraTypeEnum type, Sort sort);

}
