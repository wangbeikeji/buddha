package com.wangbei.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.BegDao;
import com.wangbei.entity.Beg;
import com.wangbei.entity.Trade;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.util.enums.TradeTypeEnum;

/**
 * @author yuyidi 2017-07-14 11:33:00
 * @class com.wangbei.service.HerebyService
 * @description 请符文
 */
@Service
public class BegService {

	@Autowired
	private BegDao begDao;
	@Autowired
	private TradeService tradeService;

	@Transactional
	public Beg addBeg(Integer user, Integer rune, Integer meritValue) {
		// Beg beg = findByUser(user);
		// if (beg != null && rune != null) {
		// beg.setRuneId(rune);
		// return begDao.create(beg);
		// }
		// 首先检查用户当前账户功德是否足够
		Trade trade = tradeService.trade(user, TradeTypeEnum.RUNE, meritValue);
		if (trade != null) {
			Beg request = new Beg(user, rune);
			request.setMeritValue(meritValue);
			request.expire();
			return begDao.create(request);
		}
		throw new ServiceException(ExceptionEnum.MERIT_POOL);
	}

	@Transactional
	public Beg modifyBeg(Integer id, Integer user, Integer divination) {
		Beg beg = new Beg(user, divination);
		beg.setId(id);
		beg.expire();
		return begDao.update(beg);
	}

	public Beg findByUser(Integer user) {
		return begDao.retrieveByUser(user);
	}

	public List<Beg> getByUserAndExpireTimeGreaterThan(Integer user, Date date) {
		return begDao.begByUserAndExpireTimeGreaterThan(user, date);
	}

	public List<Beg> getByUserIdAndJossIdAndExpireTimeGreaterThan(Integer userId, Integer jossId, Date date) {
		return begDao.begByUserIdAndJossIdAndExpireTimeGreaterThan(userId, jossId, date);
	}

}
