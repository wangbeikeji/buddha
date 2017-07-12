package com.wangbei.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.OfferingsDao;
import com.wangbei.dao.impl.jpa.OfferingsRepository;
import com.wangbei.entity.Offerings;

/**
 * 供品 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class OfferingsDaoImpl implements OfferingsDao {

	@Autowired
	private OfferingsRepository offeringsRepository;

	@Override
	public Offerings createOfferings(Offerings offerings) {
		return offeringsRepository.save(offerings);
	}

	@Override
	public void deleteOfferingsById(Integer id) {
		offeringsRepository.delete(id);
	}

	@Override
	public Offerings updateOfferings(Offerings offerings) {
		return offeringsRepository.save(offerings);
	}

	@Override
	public Offerings retrieveOfferingsById(Integer id) {
		return offeringsRepository.findById(id);
	}

	@Override
	public Page<Offerings> pageOfferings(int page, int limit) {
		return offeringsRepository.findAll(new PageRequest(page, limit));
	}

}
