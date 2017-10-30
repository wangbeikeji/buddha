package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.BlessingLamp;

/**
 * 祈福明灯 Repository
 * 
 * @author luomengan
 *
 */
public interface BlessingLampRepository extends Repository<BlessingLamp, Integer> {

	BlessingLamp save(BlessingLamp blessingLamp);

	void delete(Integer id);

	Page<BlessingLamp> findAll(Pageable pageable);
	
	List<BlessingLamp> findAll();

	BlessingLamp findById(Integer id);
	
}
