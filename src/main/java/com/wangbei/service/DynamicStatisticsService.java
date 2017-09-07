package com.wangbei.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.wangbei.dao.FreeLifeDao;
import com.wangbei.dao.TradeDao;
import com.wangbei.dao.UserDao;
import com.wangbei.entity.FreeLife;
import com.wangbei.entity.Trade;
import com.wangbei.entity.User;
import com.wangbei.pojo.NewestFreeLife;
import com.wangbei.pojo.NewestMeritDonation;
import com.wangbei.util.enums.CreatureEnum;
import com.wangbei.util.enums.TradeStatusEnum;
import com.wangbei.util.enums.TradeTypeEnum;

/**
 * 动态统计 Service
 * 
 * @author luomengan
 *
 */
@Service
public class DynamicStatisticsService {

	@Autowired
	private TradeDao tradeDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private FreeLifeDao freeLifeDao;

	public List<NewestFreeLife> newestFreeLife(int size) {
		List<NewestFreeLife> result = new ArrayList<>();
		Page<FreeLife> pageData = freeLifeDao.pageFreeLifes(0, size);
		if (pageData != null && pageData.getContent() != null && pageData.getContent().size() > 0) {
			for (FreeLife freeLife : pageData.getContent()) {
				User user = userDao.retrieveUserById(freeLife.getUserId());
				if (user != null) {
					NewestFreeLife inner = new NewestFreeLife();
					inner.setMeritValue(freeLife.getMeritValue());
					inner.setUserId(freeLife.getUserId());
					inner.setTime(freeLife.getCreateTime());
					inner.setPhone(user.getPhone());
					inner.setCreatureName(CreatureEnum.getByIndex(freeLife.getCreatureId()).getType());
					result.add(inner);
				}
			}
		}
		return result;
	}

	public List<NewestMeritDonation> newestMeritDonation(int size) {
		List<NewestMeritDonation> result = new ArrayList<>();
		Page<Trade> pageData = tradeDao.pageTradesByTypeAndStatus(TradeTypeEnum.MERIT, TradeStatusEnum.COMPLETED, 0,
				size);
		if (pageData != null && pageData.getContent() != null && pageData.getContent().size() > 0) {
			for (Trade trade : pageData.getContent()) {
				User user = userDao.retrieveUserById(trade.getUserId());
				if (user != null) {
					NewestMeritDonation inner = new NewestMeritDonation();
					inner.setMeritValue(trade.getMeritValue());
					inner.setUserId(trade.getUserId());
					inner.setTime(trade.getCreateTime());
					inner.setPhone(user.getPhone());
					result.add(inner);
				}
			}
		}
		return result;
	}

}
