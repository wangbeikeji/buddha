package com.wangbei.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.CheckinDao;
import com.wangbei.entity.Checkin;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.util.enums.TradeTypeEnum;

/**
 * 签到 Service
 * 
 * @author luomengan
 *
 */
@Service
public class CheckinService {

	/**
	 * 每次签到赠送的功德值
	 */
	private static final int checkinMeritValue = 10;

	@Autowired
	private CheckinDao checkinDao;

	@Autowired
	private TradeService tradeService;

	public Checkin getCheckinInfo(Integer id) {
		return checkinDao.retrieveCheckinById(id);
	}

	@Transactional
	public Checkin addCheckin(Checkin checkin) {
		return checkinDao.createCheckin(checkin);
	}

	@Transactional
	public Checkin modifyCheckin(Checkin checkin) {
		return checkinDao.updateCheckin(checkin);
	}

	@Transactional
	public void deleteCheckin(Integer id) {
		checkinDao.deleteCheckinById(id);
	}

	public Page<Checkin> checkins(int page, int limit) {
		return checkinDao.pageCheckin(page, limit);
	}

	public List<Checkin> list() {
		return checkinDao.listCheckin();
	}

	public Checkin checkin(Integer userId) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Date today = cal.getTime();
		cal.setTimeInMillis(cal.getTimeInMillis() + 24 * 60 * 60 * 1000);
		Date tomorrow = cal.getTime();

		List<Checkin> list = checkinDao.getUserCheckin(userId, today, tomorrow);
		if (list != null && list.size() > 0) {
			throw new ServiceException(ExceptionEnum.USER_ALREADY_CHECKIN_EXCEPTION);
		} else {
			// 增加功德数
			tradeService.trade(userId, TradeTypeEnum.CHECKIN, checkinMeritValue);
			// 保存签到记录
			Checkin checkin = new Checkin();
			checkin.setCheckinTime(new Date());
			checkin.setUserId(userId);
			checkin.setMeritValue(checkinMeritValue);
			return checkinDao.createCheckin(checkin);
		}
	}

}
