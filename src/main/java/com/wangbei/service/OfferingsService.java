package com.wangbei.service;

import java.util.*;

import com.wangbei.entity.MeritDetail;
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
	@Autowired
	private MeritDetailService meritDetailService;

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

	/**
	* @author yuyidi 2017-07-15 15:29:56
	* @method getOfferingsByUser
	* @param user
	* @return java.util.Map<java.lang.String,com.wangbei.entity.Offerings>
	* @description 根据用户获取用户供佛的未过期供品信息
	*/
	public Map<String, Offerings> getOfferingsByUser(Integer user) {
		//从功德详情中获取未过期的供品信息
		List<MeritDetail> meritDetails = meritDetailService.getByUserAndExpireTimeLessthan(user, new Date());
		//遍历功德详情，将用户供品信息按照类型分组
		Map<String, Offerings> result = new HashMap<>();
		for (MeritDetail meritDetail : meritDetails) {
			Offerings offerings = getOfferingsInfo(meritDetail.getOfferingsId());
			if (offerings != null) {
				offerings.setCreateTime(meritDetail.getCreateTime());
				offerings.setExpireTime(meritDetail.getExpireTime());
				result.put(meritDetail.getType().name(),offerings);
			}
		}
		return result;
	}
}
