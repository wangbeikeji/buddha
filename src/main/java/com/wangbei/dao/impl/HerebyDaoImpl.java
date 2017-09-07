package com.wangbei.dao.impl;

import com.wangbei.dao.HerebyDao;
import com.wangbei.dao.impl.jpa.HerebyRepository;
import com.wangbei.entity.Hereby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
@Repository
public class HerebyDaoImpl implements HerebyDao {

	@Autowired
	private HerebyRepository herebyRepository;

	@Override
	public Hereby create(Hereby hereby) {
		return herebyRepository.save(hereby);
	}

	@Override
	public void deleteById(Integer id) {
		herebyRepository.delete(id);
	}

	@Override
	public Hereby update(Hereby hereby) {
		return herebyRepository.save(hereby);
	}

	@Override
	public Hereby retrieveById(Integer id) {
		return herebyRepository.findById(id);
	}

	@Override
	public Page<Hereby> page(int page, int limit) {
		return herebyRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<Hereby> list() {
		return herebyRepository.findAll();
	}
	
	@Override
	public List<Hereby> listByUserId(Integer userId) {
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "sortNum"));
		return herebyRepository.findByUserId(userId, sort);
	}

	@Override
	public Hereby retrieveByUser(Integer user) {
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "sortNum"));
		List<Hereby> list = herebyRepository.findByUserId(user, sort);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Hereby retrieveByUserIdAndJossId(Integer userId, Integer jossId) {
		return herebyRepository.findByUserIdAndJossId(userId, jossId);
	}

	@Override
	public void deleteByUserIdAndJossId(Integer userId, Integer jossId) {
		herebyRepository.deleteByUserIdAndJossId(userId, jossId);
	}

}
