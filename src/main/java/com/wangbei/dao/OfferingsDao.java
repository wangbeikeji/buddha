package com.wangbei.dao;

import org.springframework.data.domain.Page;

import com.wangbei.entity.Offerings;

/**
 * 供品 Dao
 * 
 * @author luomengan
 *
 */
public interface OfferingsDao {

	public Offerings createOfferings(Offerings offerings);

	public void deleteOfferingsById(Integer id);

	public Offerings updateOfferings(Offerings offerings);

	public Offerings retrieveOfferingsById(Integer id);

	public Page<Offerings> pageOfferings(int page, int limit);

}
