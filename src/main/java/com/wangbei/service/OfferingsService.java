package com.wangbei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.OfferingsDao;
import com.wangbei.entity.Offerings;

/**
 * 供品 Service
 * 
 * @author luomengan
 *
 */
@Service
public class OfferingsService {

	@Autowired
	private OfferingsDao offeringsDao;

	public Offerings getOfferingsInfo(Integer id) {
		return offeringsDao.retrieveOfferingsById(id);
	}

	@Transactional
	public Offerings addOfferings(Offerings offerings) {
		return offeringsDao.createOfferings(offerings);
	}

	@Transactional
	public Offerings modifyOfferings(Offerings offerings) {
		return offeringsDao.updateOfferings(offerings);
	}

	@Transactional
	public void deleteOfferings(Integer id) {
		offeringsDao.deleteOfferingsById(id);
	}

	public Page<Offerings> offeringss(int page, int limit) {
		return offeringsDao.pageOfferings(page, limit);
	}

}
