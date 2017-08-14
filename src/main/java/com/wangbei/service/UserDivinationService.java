package com.wangbei.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.DivinationDao;
import com.wangbei.dao.UserDivinationDao;
import com.wangbei.entity.Divination;
import com.wangbei.entity.Trade;
import com.wangbei.entity.UserDivination;
import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.UserShakeDivinationInfo;
import com.wangbei.util.RandomUtil;
import com.wangbei.util.enums.DivinationTypeEnum;
import com.wangbei.util.enums.TradeTypeEnum;

/**
 * 用户求签 Service
 * 
 * @author luomengan
 *
 */
@Service
public class UserDivinationService {

	@Autowired
	private UserDivinationDao userDivinationDao;

	@Autowired
	private DivinationDao divinationDao;

	@Autowired
	private TradeService tradeService;

	public UserDivination getUserDivinationInfo(Integer id) {
		return userDivinationDao.retrieveUserDivinationById(id);
	}

	@Transactional
	public UserDivination addUserDivination(UserDivination userDivination) {
		return userDivinationDao.createUserDivination(userDivination);
	}

	@Transactional
	public UserDivination modifyUserDivination(UserDivination userDivination) {
		return userDivinationDao.updateUserDivination(userDivination);
	}

	@Transactional
	public void deleteUserDivination(Integer id) {
		userDivinationDao.deleteUserDivinationById(id);
	}

	public Page<UserDivination> userDivinations(int page, int limit) {
		return userDivinationDao.pageUserDivination(page, limit);
	}

	public List<UserDivination> list() {
		return userDivinationDao.listUserDivination();
	}

	@Transactional
	public UserShakeDivinationInfo shakeDivination() {
		UserShakeDivinationInfo result = null;
		// TODO 后面需将所有签进行缓存，不应每次都去查询数据库
		List<Divination> list = divinationDao.listDivination();
		if (list != null && list.size() > 0) {
			// step 1 : 从已有的签中随机获取一个签
			Divination divination = randomDivination2(list);
			// step 2 : 保存用户的这次摇签记录
			UserDivination userDivination = new UserDivination();
			userDivination.setDivinationId(divination.getId());
			userDivination.setMeritValue(divination.getMeritValue());
			userDivination.setShakeTime(new Date());
			userDivinationDao.createUserDivination(userDivination);
			// step 3 : 返回用户摇签信息
			result = new UserShakeDivinationInfo();
			result.setType(divination.getType());
			result.setPoem(divination.getPoem());
			result.setId(userDivination.getId());
			result.setMeritValue(divination.getMeritValue());
			return result;
		}
		return result;
	}
	
	@Deprecated
	@SuppressWarnings("unused")
	private Divination randomDivination1(List<Divination> list) {
		// 从已有的签中随机获取一个签，随机规则如下
		// 当第一次随机的签为上签或者上上签时，取该签
		// 当第一次随机的签为中签时，再随机第二，取该签
		// 当第一次随机的签为下签或者下下签时，再随机第二，如为下签或者下下签，再随机第三次，取签
		int index = RandomUtil.getRandomInt(list.size());
		if (list.get(index).getType() == DivinationTypeEnum.MIDDLE) {
			index = RandomUtil.getRandomInt(list.size());
		} else if (list.get(index).getType() == DivinationTypeEnum.DOWN
				|| list.get(index).getType() == DivinationTypeEnum.DOWNDOWN) {
			index = RandomUtil.getRandomInt(list.size());
			if (list.get(index).getType() == DivinationTypeEnum.DOWN
					|| list.get(index).getType() == DivinationTypeEnum.DOWNDOWN) {
				index = RandomUtil.getRandomInt(list.size());
			}
		}
		return list.get(index);
	}
	
	private Divination randomDivination2(List<Divination> list) {
		int size = list.size();
		// 遍历，增加上签、上上签、中签出现的几率
		for(int i = 0; i < size; i++) {
			Divination div = list.get(i);
			if(div.getType() == DivinationTypeEnum.UP || div.getType() == DivinationTypeEnum.UPUP) {
				list.add(div);
				list.add(div);
				list.add(div);
				list.add(div);
				list.add(div);
				list.add(div);
			} else if(div.getType() == DivinationTypeEnum.MIDDLE) {
				list.add(div);
			}
		}
		// 打乱list中元素的顺序
		Collections.shuffle(list);
		// 随机获取一个签的位置
		int index = RandomUtil.getRandomInt(list.size());
		return list.get(index);
	}

	public Divination explainDivination(Integer userId, Integer userDivinationId) {
		UserDivination userDivination = userDivinationDao.retrieveUserDivinationById(userDivinationId);
		if (userDivination.getExplainTime() != null) {
			// 已经解过签，直接返回
			return divinationDao.retrieveDivinationById(userDivination.getDivinationId());
		}
		// 扣除用户功德
		Trade trade = tradeService.trade(userId, TradeTypeEnum.DIVINATION, userDivination.getMeritValue());
		if (trade != null) {
			// 设置解签时间
			userDivination.setUserId(userId);
			userDivination.setExplainTime(new Date());
			userDivinationDao.updateUserDivination(userDivination);
			return divinationDao.retrieveDivinationById(userDivination.getDivinationId());
		}
		throw new ServiceException(ServiceException.MERIT_POOL);
	}

}
