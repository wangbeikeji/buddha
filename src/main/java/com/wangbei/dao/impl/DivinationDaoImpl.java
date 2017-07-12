package com.wangbei.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.DivinationDao;
import com.wangbei.dao.impl.jpa.DivinationRepository;
import com.wangbei.entity.Divination;

/**
 * 灵签 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class DivinationDaoImpl implements DivinationDao {

	@Autowired
	private DivinationRepository divinationRepository;

	@Override
	public Divination createDivination(Divination divination) {
		return divinationRepository.save(divination);
	}

	@Override
	public void deleteDivinationById(Integer id) {
		divinationRepository.delete(id);
	}

	@Override
	public Divination updateDivination(Divination divination) {
		return divinationRepository.save(divination);
	}

	@Override
	public Divination retrieveDivinationById(Integer id) {
		return divinationRepository.findById(id);
	}

	@Override
	public Page<Divination> pageDivination(int page, int limit) {
		return divinationRepository.findAll(new PageRequest(page, limit));
	}

}
