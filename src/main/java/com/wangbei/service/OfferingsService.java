package com.wangbei.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Map<String, List<Offerings>> groupByType() {
		Map<String, List<Offerings>> result = new HashMap<>();
		
		// 添加分组List对象
		List<Offerings> list = offeringsDao.listOfferings(
				new Sort(new Sort.Order(Direction.ASC, "type"), new Sort.Order(Direction.ASC, "meritValue")));
		OfferingTypeEnum[] typeArr = OfferingTypeEnum.values();
		for(OfferingTypeEnum type : typeArr) {
			result.put(type.name().toLowerCase(), new ArrayList<Offerings>());
		}
		// 将数据放到对应的List对象中，List的顺序为枚举的类型
		if(list != null && list.size() > 0) {
			for(Offerings o : list) {
				if(o.getType() != null) {
					result.get(o.getType().name().toLowerCase()).add(o);
				}
			}
		}

		return result;
	}

}
