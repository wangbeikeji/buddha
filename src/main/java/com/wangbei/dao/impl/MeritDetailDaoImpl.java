package com.wangbei.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.MeritDetailDao;
import com.wangbei.dao.impl.jpa.MeritDetailRepository;
import com.wangbei.entity.MeritDetail;
import com.wangbei.util.enums.OfferingTypeEnum;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
@Repository
public class MeritDetailDaoImpl implements MeritDetailDao {

	@Autowired
	private MeritDetailRepository meritDetailRepository;

	@Override
	public MeritDetail create(MeritDetail meritDetail) {
		return meritDetailRepository.save(meritDetail);
	}

	@Override
	public void deleteById(Integer id) {
		meritDetailRepository.delete(id);
	}

	@Override
	public MeritDetail update(MeritDetail meritDetail) {
		return meritDetailRepository.save(meritDetail);
	}

	@Override
	public MeritDetail retrieveById(Integer id) {
		return meritDetailRepository.findById(id);
	}

	@Override
	public Page<MeritDetail> page(int page, int limit) {
		return meritDetailRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<MeritDetail> list() {
		return meritDetailRepository.findAll();
	}

	@Override
	public List<MeritDetail> meritDetailsByUserAndExpireTimeGreaterThan(Integer user, Date date) {
		return meritDetailRepository.findAllByUserIdAndExpireTimeGreaterThan(user, date);
	}

	@Override
	public MeritDetail meritDetailByUserAndType(Integer user, OfferingTypeEnum type) {
		return meritDetailRepository.findByUserIdAndType(user, type);
	}

	@Override
	public List<MeritDetail> meritDetailsByUserIdAndJossIdAndExpireTimeGreaterThan(Integer userId, Integer jossId,
			Date date) {
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "expireTime"));
		return meritDetailRepository.findAllByUserIdAndJossIdAndExpireTimeGreaterThan(userId, jossId, date, sort);
	}

	@Override
	public List<MeritDetail> meritDetailsByUserIdAndJossId(Integer userId, Integer jossId) {
		return meritDetailRepository.findByUserIdAndJossId(userId, jossId);
	}
}
