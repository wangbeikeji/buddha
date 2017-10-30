package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.BlessingLampOil;

/**
 * 灯油 Repository
 * 
 * @author luomengan
 *
 */
public interface BlessingLampOilRepository extends Repository<BlessingLampOil, Integer> {

	BlessingLampOil save(BlessingLampOil blessingLampOil);

	void delete(Integer id);

	Page<BlessingLampOil> findAll(Pageable pageable);
	
	List<BlessingLampOil> findAll();

	BlessingLampOil findById(Integer id);
	
}
