package com.wangbei.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.OfferingsDao;
import com.wangbei.entity.Offerings;
import com.wangbei.util.enums.OfferingTypeEnum;

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

	public List<List<Offerings>> groupByType() {
		List<List<Offerings>> result = new ArrayList<>();
		
		// 添加分组List对象
		List<Offerings> list = offeringsDao.listOfferings(
				new Sort(new Sort.Order(Direction.ASC, "type"), new Sort.Order(Direction.ASC, "meritValue")));
		for(int i = 0; i < OfferingTypeEnum.values().length; i++) {
			result.add(new ArrayList<Offerings>());
		}
		// 将数据放到对应的List对象中，List的顺序为枚举的类型
		if(list != null && list.size() > 0) {
			for(Offerings o : list) {
				if(o.getType() != null) {
					result.get(o.getType().getIndex() - 1).add(o);
				}
			}
		}

		return result;
	}

}
