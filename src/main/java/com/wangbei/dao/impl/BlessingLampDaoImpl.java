package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.BlessingLampDao;
import com.wangbei.dao.impl.jpa.BlessingLampRepository;
import com.wangbei.entity.BlessingLamp;

/**
 * 祈福明灯 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class BlessingLampDaoImpl implements BlessingLampDao {

	@Autowired
	private BlessingLampRepository blessingLampRepository;

	@Override
	public BlessingLamp createBlessingLamp(BlessingLamp blessingLamp) {
		return blessingLampRepository.save(blessingLamp);
	}

	@Override
	public void deleteBlessingLampById(Integer id) {
		blessingLampRepository.delete(id);
	}

	@Override
	public BlessingLamp updateBlessingLamp(BlessingLamp blessingLamp) {
		return blessingLampRepository.save(blessingLamp);
	}

	@Override
	public BlessingLamp retrieveBlessingLampById(Integer id) {
		return blessingLampRepository.findById(id);
	}

	@Override
	public Page<BlessingLamp> pageBlessingLamp(int page, int limit) {
		return blessingLampRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<BlessingLamp> listBlessingLamp() {
		return blessingLampRepository.findAll();
	}

}
