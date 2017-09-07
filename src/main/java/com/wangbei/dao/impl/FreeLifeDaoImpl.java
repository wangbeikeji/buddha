package com.wangbei.dao.impl;

import com.wangbei.dao.FreeLifeDao;
import com.wangbei.dao.impl.jpa.FreeLifeRepository;
import com.wangbei.entity.FreeLife;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/7/18.
 * @desc
 */
@Repository
public class FreeLifeDaoImpl implements FreeLifeDao {

	@Autowired
	private FreeLifeRepository freeLifeRepository;

	@Override
	public FreeLife create(FreeLife freeLife) {
		return freeLifeRepository.save(freeLife);
	}

	@Override
	public void deleteById(Integer id) {
		freeLifeRepository.delete(id);
	}

	@Override
	public FreeLife update(FreeLife freeLife) {
		return freeLifeRepository.save(freeLife);
	}

	@Override
	public FreeLife retrieveById(Integer id) {
		return freeLifeRepository.findById(id);
	}

	@Override
	public Page<FreeLife> page(int page, int limit) {
		return freeLifeRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<FreeLife> list() {
		return freeLifeRepository.findAll();
	}

	@Override
	public Page<FreeLife> pageFreeLifes(int page, int limit) {
		Pageable pagable = new PageRequest(page, limit, new Sort(new Sort.Order(Sort.Direction.DESC, "createTime")));
		return freeLifeRepository.findByMeritValueGreaterThan(0, pagable);
	}
}
