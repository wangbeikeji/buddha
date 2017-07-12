package com.wangbei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.DivinationDao;
import com.wangbei.entity.Divination;

/**
 * 灵签 Service
 * 
 * @author luomengan
 *
 */
@Service
public class DivinationService {

	@Autowired
	private DivinationDao divinationDao;

	public Divination getDivinationInfo(Integer id) {
		return divinationDao.retrieveDivinationById(id);
	}

	@Transactional
	public Divination addDivination(Divination divination) {
		return divinationDao.createDivination(divination);
	}

	@Transactional
	public Divination modifyDivination(Divination divination) {
		return divinationDao.updateDivination(divination);
	}

	@Transactional
	public void deleteDivination(Integer id) {
		divinationDao.deleteDivinationById(id);
	}

	public Page<Divination> divinations(int page, int limit) {
		return divinationDao.pageDivination(page, limit);
	}

}
