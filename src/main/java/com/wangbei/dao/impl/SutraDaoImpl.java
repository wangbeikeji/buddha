package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.SutraDao;
import com.wangbei.dao.impl.jpa.SutraRepository;
import com.wangbei.entity.Sutra;
import com.wangbei.util.enums.SutraTypeEnum;

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

	@Override
	public List<Sutra> listSutraOrderById() {
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "id"));
		return sutraRepository.findAll(sort);
	}
	
	@Override
	public List<Sutra> listSutraByTypeOrderById(SutraTypeEnum type) {
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "id"));
		return sutraRepository.findByType(type, sort);
	}

	@Override
	public List<Sutra> listSutraByIsEnableOrderById(boolean isEnable) {
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "id"));
		return sutraRepository.findByIsEnable(isEnable, sort);
	}

	@Override
	public Page<Sutra> pageSutraByIsEnableOrderById(boolean isEnable, int page, int limit) {
		Pageable pageable = new PageRequest(page, limit, new Sort(new Sort.Order(Direction.ASC, "id")));
		return sutraRepository.findByIsEnable(isEnable, pageable);
	}

	@Override
	public Page<Sutra> pageSutraByTypeAndIsEnableOrderById(boolean isEnable, SutraTypeEnum type, int page, int limit) {
		Pageable pageable = new PageRequest(page, limit, new Sort(new Sort.Order(Direction.ASC, "id")));
		return sutraRepository.findByTypeAndIsEnable(type, isEnable, pageable);
	}

}
