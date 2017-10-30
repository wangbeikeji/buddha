package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.BlessingLampOilDao;
import com.wangbei.entity.BlessingLampOil;

/**
 * 灯油 Service
 * 
 * @author luomengan
 *
 */
@Service
public class BlessingLampOilService {

	@Autowired
	private BlessingLampOilDao blessingLampOilDao;

	public BlessingLampOil getBlessingLampOilInfo(Integer id) {
		return blessingLampOilDao.retrieveBlessingLampOilById(id);
	}

	@Transactional
	public BlessingLampOil addBlessingLampOil(BlessingLampOil blessingLampOil) {
		return blessingLampOilDao.createBlessingLampOil(blessingLampOil);
	}

	@Transactional
	public BlessingLampOil modifyBlessingLampOil(BlessingLampOil blessingLampOil) {
		return blessingLampOilDao.updateBlessingLampOil(blessingLampOil);
	}

	@Transactional
	public void deleteBlessingLampOil(Integer id) {
		blessingLampOilDao.deleteBlessingLampOilById(id);
	}
	
	@Transactional
	public void deleteBlessingLampOils(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					blessingLampOilDao.deleteBlessingLampOilById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<BlessingLampOil> blessingLampOils(int page, int limit) {
		return blessingLampOilDao.pageBlessingLampOil(page, limit);
	}
	
	public List<BlessingLampOil> list() {
		return blessingLampOilDao.listBlessingLampOil();
	}

}
