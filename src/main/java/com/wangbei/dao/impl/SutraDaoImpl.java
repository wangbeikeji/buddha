package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.SutraDao;
import com.wangbei.dao.impl.jpa.SutraRepository;
import com.wangbei.entity.Sutra;

/**
 * 经书 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class SutraDaoImpl implements SutraDao {

	@Autowired
	private SutraRepository sutraRepository;

	@Override
	public Sutra createSutra(Sutra sutra) {
		return sutraRepository.save(sutra);
	}

	@Override
	public void deleteSutraById(Integer id) {
		sutraRepository.delete(id);
	}

	@Override
	public Sutra updateSutra(Sutra sutra) {
		return sutraRepository.save(sutra);
	}

	@Override
	public Sutra retrieveSutraById(Integer id) {
		return sutraRepository.findById(id);
	}

	@Override
	public Page<Sutra> pageSutra(int page, int limit) {
		return sutraRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<Sutra> listSutra() {
		return sutraRepository.findAll();
	}

}
