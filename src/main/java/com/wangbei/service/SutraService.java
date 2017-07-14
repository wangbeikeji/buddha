package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.SutraDao;
import com.wangbei.entity.Sutra;

/**
 * 经书 Service
 * 
 * @author luomengan
 *
 */
@Service
public class SutraService {

	@Autowired
	private SutraDao sutraDao;

	public Sutra getSutraInfo(Integer id) {
		return sutraDao.retrieveSutraById(id);
	}

	@Transactional
	public Sutra addSutra(Sutra sutra) {
		return sutraDao.createSutra(sutra);
	}

	@Transactional
	public Sutra modifySutra(Sutra sutra) {
		return sutraDao.updateSutra(sutra);
	}

	@Transactional
	public void deleteSutra(Integer id) {
		sutraDao.deleteSutraById(id);
	}

	public Page<Sutra> sutras(int page, int limit) {
		return sutraDao.pageSutra(page, limit);
	}
	
	public List<Sutra> list() {
		return sutraDao.listSutra();
	}

}
