package com.wangbei.service;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.BlessingLampDao;
import com.wangbei.dao.BlessingLampOilDao;
import com.wangbei.dao.UserLightLampDao;
import com.wangbei.entity.BlessingLamp;
import com.wangbei.entity.BlessingLampOil;
import com.wangbei.entity.Trade;
import com.wangbei.entity.UserLightLamp;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.UserLightLampInfo;
import com.wangbei.util.enums.TradeTypeEnum;

/**
 * 祈福明灯 Service
 * 
 * @author luomengan
 *
 */
@Service
public class BlessingLampService {

	@Autowired
	private BlessingLampDao blessingLampDao;

	@Autowired
	private TradeService tradeService;

	@Autowired
	private UserLightLampDao userLightLampDao;

	@Autowired
	private BlessingLampOilDao blessingLampOilDao;

	public BlessingLamp getBlessingLampInfo(Integer id) {
		return blessingLampDao.retrieveBlessingLampById(id);
	}

	@Transactional
	public BlessingLamp addBlessingLamp(BlessingLamp blessingLamp) {
		return blessingLampDao.createBlessingLamp(blessingLamp);
	}

	@Transactional
	public BlessingLamp modifyBlessingLamp(BlessingLamp blessingLamp) {
		return blessingLampDao.updateBlessingLamp(blessingLamp);
	}

	@Transactional
	public void deleteBlessingLamp(Integer id) {
		blessingLampDao.deleteBlessingLampById(id);
	}

	@Transactional
	public void deleteBlessingLamps(String ids) {
		if (ids != null) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				if (!"".equals(id.trim())) {
					blessingLampDao.deleteBlessingLampById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<BlessingLamp> blessingLamps(int page, int limit) {
		return blessingLampDao.pageBlessingLamp(page, limit);
	}

	public List<BlessingLamp> list() {
		return blessingLampDao.listBlessingLamp();
	}

	@Transactional
	public UserLightLampInfo lightLamp(int userId, Integer lampId, Integer oilId) {
		List<UserLightLamp> oldList = userLightLampDao.listUserLightLampByUserId(userId);
		if (oldList != null && oldList.size() > 0) {
			for (UserLightLamp inner : oldList) {
				if (inner.getExpireTime() != null && inner.getExpireTime().getTime() > new Date().getTime()) {
					throw new ServiceException(ExceptionEnum.ALREADY_LIGHTLAMP_EXCEPTION);
				}
			}
		}

		BlessingLamp lamp = blessingLampDao.retrieveBlessingLampById(lampId);
		if (lamp == null) {
			throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION, new Object[] { "祈福明灯id无效!" });
		}
		BlessingLampOil oil = blessingLampOilDao.retrieveBlessingLampOilById(oilId);
		if (oil == null) {
			throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION, new Object[] { "灯油id无效!" });
		}

		// 点灯
		Trade trade = tradeService.trade(userId, TradeTypeEnum.BLESSINGLAMP, oil.getMeritValue());
		if (trade != null) {
			UserLightLamp userLightLamp = new UserLightLamp();
			Date createTime = new Date();
			Date expireTime = new DateTime(createTime.getTime()).plusDays(oil.getFlameDays()).toDate();
			userLightLamp.setCreateTime(createTime);
			userLightLamp.setExpireTime(expireTime);
			userLightLamp.setLampId(lampId);
			userLightLamp.setMeritValue(oil.getMeritValue());
			userLightLamp.setOilId(oilId);
			userLightLamp.setUserId(userId);
			userLightLampDao.createUserLightLamp(userLightLamp);
			
			UserLightLampInfo result = new UserLightLampInfo();
			result.setCreateTime(userLightLamp.getCreateTime());
			result.setExpireTime(userLightLamp.getExpireTime());
			result.setUserId(userLightLamp.getUserId());
			result.setLampEffect(lamp.getEffect());
			result.setLampId(lamp.getId());
			result.setLampLink(lamp.getLink());
			result.setLampMeaning(lamp.getMeaning());
			result.setLampName(lamp.getName());
			result.setLampSmallLink(lamp.getSmallLink());
			result.setMeritValue(userLightLamp.getMeritValue());
			result.setOilId(userLightLamp.getOilId());
			result.setOilDesc(oil.getOilDesc());
			result.setOilFlameDays(oil.getFlameDays());
			return result;
		} else {
			throw new ServiceException(ExceptionEnum.MERIT_POOL);
		}
	}

	public UserLightLampInfo getCurrentLightLamp(int userId) {
		List<UserLightLamp> oldList = userLightLampDao.listUserLightLampByUserId(userId);
		if (oldList != null && oldList.size() > 0) {
			UserLightLampInfo result = new UserLightLampInfo();
			UserLightLamp userLightLamp = oldList.get(0);
			BlessingLamp lamp = blessingLampDao.retrieveBlessingLampById(userLightLamp.getLampId());
			BlessingLampOil oil = blessingLampOilDao.retrieveBlessingLampOilById(userLightLamp.getOilId());

			result.setCreateTime(userLightLamp.getCreateTime());
			result.setExpireTime(userLightLamp.getExpireTime());
			result.setUserId(userLightLamp.getUserId());
			result.setLampEffect(lamp.getEffect());
			result.setLampId(lamp.getId());
			result.setLampLink(lamp.getLink());
			result.setLampMeaning(lamp.getMeaning());
			result.setLampName(lamp.getName());
			result.setLampSmallLink(lamp.getSmallLink());
			result.setMeritValue(userLightLamp.getMeritValue());
			result.setOilId(userLightLamp.getOilId());
			result.setOilDesc(oil.getOilDesc());
			result.setOilFlameDays(oil.getFlameDays());
			return result;
		}
		return null;
	}

}
