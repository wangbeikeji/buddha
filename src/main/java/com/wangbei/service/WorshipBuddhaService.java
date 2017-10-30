package com.wangbei.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.BegDao;
import com.wangbei.dao.HerebyDao;
import com.wangbei.dao.JossDao;
import com.wangbei.dao.MeritDetailDao;
import com.wangbei.dao.OfferingsDao;
import com.wangbei.entity.Beg;
import com.wangbei.entity.Hereby;
import com.wangbei.entity.Joss;
import com.wangbei.entity.MeritDetail;
import com.wangbei.entity.Offerings;
import com.wangbei.entity.Rune;
import com.wangbei.entity.Trade;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.AddOfferingsResultInfo;
import com.wangbei.pojo.HerebyInfo;
import com.wangbei.pojo.RuneWithBegInfo;
import com.wangbei.pojo.WorshipBuddhaInfo;
import com.wangbei.util.enums.OfferingTypeEnum;
import com.wangbei.util.enums.TradeTypeEnum;

/**
 * 拜佛 Service
 * 
 * @author luomengan
 *
 */
@Service
public class WorshipBuddhaService {

	@Autowired
	private HerebyDao herebyDao;

	@Autowired
	private JossDao jossDao;

	@Autowired
	private OfferingsDao offeringsDao;

	@Autowired
	private BegDao begDao;

	@Autowired
	private MeritDetailService meritDetailService;

	@Autowired
	private MeritDetailDao meritDetailDao;

	@Autowired
	private RuneService runeService;

	@Autowired
	private TradeService tradeService;

	@Transactional
	public HerebyInfo pleaseBuddha(Integer userId, Integer newJossId, Integer oldJossId) {
		HerebyInfo result = null;
		if (newJossId != null && newJossId > 0) {
			Joss joss = jossDao.retrieveJossById(newJossId);
			if (joss == null) {
				throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION, new Object[] { "newJossId无效!" });
			}
			// 添加新佛
			Hereby hereby = herebyDao.retrieveByUserIdAndJossId(userId, newJossId);
			if (hereby == null) {
				hereby = new Hereby();
				hereby.setCreateTime(new Date());
				hereby.setJossId(newJossId);
				hereby.setUserId(userId);
				if (oldJossId > 0) {
					Hereby oldHereby = herebyDao.retrieveByUserIdAndJossId(userId, oldJossId);
					if (oldHereby != null) {
						hereby.setSortNum(oldHereby.getSortNum());
					} else {
						hereby.setSortNum(getHerebyMaxSortNum(herebyDao.listByUserId(userId)));
					}
				} else {
					hereby.setSortNum(getHerebyMaxSortNum(herebyDao.listByUserId(userId)));
				}

				herebyDao.create(hereby);
			} else {
				hereby.setCreateTime(new Date());
				herebyDao.update(hereby);
			}

			result = new HerebyInfo();
			result.setJossCreateTime(hereby.getCreateTime());
			result.setJossBuddhaName(joss.getBuddhaName());
			result.setJossId(joss.getId());
			result.setJossIntroduction(joss.getIntroduction());
			result.setJossLink(joss.getLink());
		}

		if (oldJossId != null && oldJossId > 0 && oldJossId != newJossId) {
			// 删除旧佛
			herebyDao.deleteByUserIdAndJossId(userId, oldJossId);
			if (newJossId > 0) {
				// 将旧佛的供品添加到新佛上
				List<MeritDetail> oldOfferingsList = meritDetailDao.meritDetailsByUserIdAndJossId(userId, oldJossId);
				if (oldOfferingsList != null && oldOfferingsList.size() > 0) {
					for (MeritDetail oldOfferings : oldOfferingsList) {
						oldOfferings.setJossId(newJossId);
						meritDetailDao.update(oldOfferings);
					}
				}
				// 将旧佛的符文添加到新佛上
				List<Beg> oldRunesList = begDao.begByUserIdAndJossId(userId, oldJossId);
				if (oldRunesList != null && oldRunesList.size() > 0) {
					for (Beg oldRunes : oldRunesList) {
						oldRunes.setJossId(newJossId);
						begDao.update(oldRunes);
					}
				}
			}
		}

		return result;
	}

	private Integer getHerebyMaxSortNum(List<Hereby> list) {
		Integer result = 1;
		if (list != null && list.size() > 0) {
			Integer max = 0;
			for (Hereby hereby : list) {
				if (hereby.getSortNum() != null && hereby.getSortNum() > max) {
					max = hereby.getSortNum();
				}
			}
			return max + 1;
		}
		return result;
	}

	@Transactional
	public List<WorshipBuddhaInfo> getWorshipBuddhaInfo(Integer userId) {
		List<WorshipBuddhaInfo> result = new ArrayList<>();

		List<Hereby> herebyList = herebyDao.listByUserId(userId);
		if (herebyList != null && herebyList.size() > 0) {
			for (int i = 0; i < herebyList.size(); i++) {
				Hereby hereby = herebyList.get(i);
				if (hereby.getSortNum() == null || hereby.getSortNum() != (i + 1)) {
					hereby.setSortNum(i + 1);
					herebyDao.update(hereby);
				}
				WorshipBuddhaInfo info = new WorshipBuddhaInfo();
				// 佛
				Joss joss = jossDao.retrieveJossById(hereby.getJossId());
				info.setJossId(joss.getId());
				info.setJossBuddhaName(joss.getBuddhaName());
				info.setJossIntroduction(joss.getIntroduction());
				info.setJossLink(joss.getLink());
				info.setJossCreateTime(hereby.getCreateTime());

				// 花、香、果
				List<MeritDetail> meritDetails = meritDetailService.getByUserIdAndJossIdAndExpireTimeLessthan(userId,
						joss.getId(), new Date());
				for (MeritDetail meritDetail : meritDetails) {
					Offerings offerings = offeringsDao.retrieveOfferingsById(meritDetail.getOfferingsId());
					if (offerings != null) {
						if (offerings.getType() == OfferingTypeEnum.SANDALWOOD) {
							info.setSandalwoodCreateTime(meritDetail.getCreateTime());
							info.setSandalwoodExpireTime(meritDetail.getExpireTime());
							info.setSandalwoodDesignation(offerings.getDesignation());
							info.setSandalwoodId(offerings.getId());
							info.setSandalwoodLink(offerings.getLink());
							info.setSandalwoodParaphrase(offerings.getParaphrase());
							info.setSandalwoodSmallLink(offerings.getSmallLink());
						} else if (offerings.getType() == OfferingTypeEnum.FLOWERS) {
							info.setFlowersCreateTime(meritDetail.getCreateTime());
							info.setFlowersExpireTime(meritDetail.getExpireTime());
							info.setFlowersDesignation(offerings.getDesignation());
							info.setFlowersId(offerings.getId());
							info.setFlowersLink(offerings.getLink());
							info.setFlowersParaphrase(offerings.getParaphrase());
							info.setFlowersSmallLink(offerings.getSmallLink());
						} else if (offerings.getType() == OfferingTypeEnum.FRUIT) {
							info.setFruitCreateTime(meritDetail.getCreateTime());
							info.setFruitExpireTime(meritDetail.getExpireTime());
							info.setFruitDesignation(offerings.getDesignation());
							info.setFruitId(offerings.getId());
							info.setFruitLink(offerings.getLink());
							info.setFruitParaphrase(offerings.getParaphrase());
							info.setFruitSmallLink(offerings.getSmallLink());
						}
					}
				}

				// 符文列表
				List<RuneWithBegInfo> runes = runeService.getRuneWithBegInfoByUserIdAndJossId(userId, joss.getId());
				info.setRunes(runes);
				result.add(info);
			}
		}

		return result;
	}

	@Transactional
	public AddOfferingsResultInfo addOfferings(Integer userId, Integer jossId, Integer offeringsId) {
		Offerings offerings = offeringsDao.retrieveOfferingsById(offeringsId);
		if (offerings != null) {
			AddOfferingsResultInfo result = new AddOfferingsResultInfo();
			MeritDetail meritDetail = meritDetailService.addMeritDetail(userId, jossId, offerings);
			result.setCreateTime(meritDetail.getCreateTime());
			result.setExpireTime(meritDetail.getExpireTime());
			result.setJossId(jossId);
			result.setMeritValue(meritDetail.getMeritValue());
			result.setType(offerings.getType());
			result.setUserId(userId);
			result.setOfferingsDesignation(offerings.getDesignation());
			result.setOfferingsId(offerings.getId());
			result.setOfferingsLink(offerings.getLink());
			result.setOfferingsParaphrase(offerings.getParaphrase());
			result.setOfferingsSmallLink(offerings.getSmallLink());
			return result;
		}
		return null;
	}

	@Transactional
	public RuneWithBegInfo pleaseRune(Integer userId, Integer jossId, Integer runeId, boolean isSelf, String otherName,
			Integer otherGender, String otherBirthday) {
		// TODO 验证jossId是否正确
		Rune runeInfo = runeService.getRuneInfo(runeId);
		if (runeInfo != null) {
			// 首先检查用户当前账户功德是否足够
			Trade trade = tradeService.trade(userId, TradeTypeEnum.RUNE, runeInfo.getMeritValue());
			if (trade != null) {
				Beg request = new Beg(userId, runeId);
				request.setJossId(jossId);
				request.setIsSelf(isSelf);
				if (!isSelf) {
					request.setOtherBirthday(otherBirthday);
					request.setOtherGender(otherGender);
					request.setOtherName(otherName);
				}
				request.setMeritValue(runeInfo.getMeritValue());
				request.expire();
				begDao.create(request);

				return new RuneWithBegInfo(runeInfo, request);
			}
			throw new ServiceException(ExceptionEnum.MERIT_POOL);
		} else {
			throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION, new Object[] { "runeId不存在!" });
		}
	}

}
