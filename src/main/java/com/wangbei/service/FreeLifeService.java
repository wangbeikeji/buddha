package com.wangbei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.FreeLifeDao;
import com.wangbei.dao.TradeDao;
import com.wangbei.entity.FreeLife;
import com.wangbei.entity.Trade;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.util.enums.CreatureEnum;
import com.wangbei.util.enums.TradeStatusEnum;

/**
 * @author Created by yuyidi on 2017/7/18.
 * @desc
 */
@Service
public class FreeLifeService {

	@Autowired
	private FreeLifeDao freeLifeDao;

	@Autowired
	private TradeDao tradeDao;

	@Transactional
	public FreeLife addFreeLife(Integer user, CreatureEnum type, String tradeNo) {
		if(type == CreatureEnum.FISH) {
			// 鱼是免费的，不需要判断交易信息
			FreeLife request = new FreeLife(user, type.getIndex(), 0);
			return freeLifeDao.create(request);
		} else {
			Trade trade = tradeDao.retrieveByTradeNo(tradeNo);
			if (trade != null && trade.getStatus() == TradeStatusEnum.COMPLETED) {
				FreeLife request = new FreeLife(user, type.getIndex(), trade.getMeritValue());
				return freeLifeDao.create(request);
			} else {
				if (trade == null) {
					throw new ServiceException(ExceptionEnum.TRADENO_NOTEXIST_EXCEPTION);
				} else {
					throw new ServiceException(ExceptionEnum.TRADE_NOTCOMPLETED_EXCEPTION);
				}
			}
		}
	}
}
