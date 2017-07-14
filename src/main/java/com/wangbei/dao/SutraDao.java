package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.Sutra;

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
	
	public List<Sutra> listSutra();

}
