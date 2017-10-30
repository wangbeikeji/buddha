package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.BlessingLampOil;

/**
 * 灯油 Dao
 * 
 * @author luomengan
 *
 */
public interface BlessingLampOilDao {

	public BlessingLampOil createBlessingLampOil(BlessingLampOil blessingLampOil);

	public void deleteBlessingLampOilById(Integer id);

	public BlessingLampOil updateBlessingLampOil(BlessingLampOil blessingLampOil);

	public BlessingLampOil retrieveBlessingLampOilById(Integer id);

	public Page<BlessingLampOil> pageBlessingLampOil(int page, int limit);
	
	public List<BlessingLampOil> listBlessingLampOil();

}
