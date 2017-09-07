package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.Sutra;
import com.wangbei.util.enums.SutraTypeEnum;

/**
 * 经书 Dao
 * 
 * @author luomengan
 *
 */
public interface SutraDao {

	public Sutra createSutra(Sutra sutra);

	public void deleteSutraById(Integer id);

	public Sutra updateSutra(Sutra sutra);

	public Sutra retrieveSutraById(Integer id);

	public Page<Sutra> pageSutra(int page, int limit);
	
	public Page<Sutra> pageSutraByIsEnableOrderById(boolean isEnable, int page, int limit);
	
	public List<Sutra> listSutra();
	
	public List<Sutra> listSutraByIsEnableOrderById(boolean isEnable);

	public List<Sutra> listSutraOrderById();

	public Page<Sutra> pageSutraByTypeAndIsEnableOrderById(boolean isEnable, SutraTypeEnum type, int page, int limit);

	public List<Sutra> listSutraByTypeOrderById(SutraTypeEnum type);

}
