package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.BlessingLampOilDao;
import com.wangbei.dao.impl.jpa.BlessingLampOilRepository;
import com.wangbei.entity.BlessingLampOil;

/**
 * 灯油 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class BlessingLampOilDaoImpl implements BlessingLampOilDao {

	@Autowired
	private BlessingLampOilRepository blessingLampOilRepository;

	@Override
	public BlessingLampOil createBlessingLampOil(BlessingLampOil blessingLampOil) {
		return blessingLampOilRepository.save(blessingLampOil);
	}

	@Override
	public void deleteBlessingLampOilById(Integer id) {
		blessingLampOilRepository.delete(id);
	}

	@Override
	public BlessingLampOil updateBlessingLampOil(BlessingLampOil blessingLampOil) {
		return blessingLampOilRepository.save(blessingLampOil);
	}

	@Override
	public BlessingLampOil retrieveBlessingLampOilById(Integer id) {
		return blessingLampOilRepository.findById(id);
	}

	@Override
	public Page<BlessingLampOil> pageBlessingLampOil(int page, int limit) {
		return blessingLampOilRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<BlessingLampOil> listBlessingLampOil() {
		return blessingLampOilRepository.findAll();
	}

}
