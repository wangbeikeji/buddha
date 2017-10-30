package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.CreaturesDao;
import com.wangbei.dao.impl.jpa.CreaturesRepository;
import com.wangbei.entity.Creatures;

/**
 * 生灵 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class CreaturesDaoImpl implements CreaturesDao {

	@Autowired
	private CreaturesRepository creaturesRepository;

	@Override
	public Creatures createCreatures(Creatures creatures) {
		return creaturesRepository.save(creatures);
	}

	@Override
	public void deleteCreaturesById(Integer id) {
		creaturesRepository.delete(id);
	}

	@Override
	public Creatures updateCreatures(Creatures creatures) {
		return creaturesRepository.save(creatures);
	}

	@Override
	public Creatures retrieveCreaturesById(Integer id) {
		return creaturesRepository.findById(id);
	}

	@Override
	public Page<Creatures> pageCreatures(int page, int limit) {
		return creaturesRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<Creatures> listCreatures() {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "sortNum"));
		return creaturesRepository.findByIsEnable(true, sort);
	}

	@Override
	public List<Creatures> listAllCreatures() {
		return creaturesRepository.findAll();
	}

}
