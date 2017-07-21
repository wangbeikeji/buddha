package com.wangbei.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.AccountDao;
import com.wangbei.dao.TradeDao;
import com.wangbei.entity.Account;
import com.wangbei.entity.Trade;
import com.wangbei.exception.ServiceException;
import com.wangbei.util.RandomUtil;
import com.wangbei.util.enums.TradeStatusEnum;
import com.wangbei.util.enums.TradeTypeEnum;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc
 */
@Service
public class TradeService {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	@Autowired
	private TradeDao tardeDao;
	@Autowired
	private AccountDao accountDao;

	@Transactional
	public Trade trade(Integer user, TradeTypeEnum type, Integer meritValue) {
		// step 1 : 获取用户账号，不存在则创建用户账号
		Account account = accountDao.findByUser(user);
		if (account == null) {
			account = new Account();
			account.setUserId(user);
			account.setMeritValue(0);
			accountDao.create(account);
		}
		// step 2 : 判断增加功德还是扣除功德
		boolean isIncrease = false;
		if (type == TradeTypeEnum.CHARGE || type == TradeTypeEnum.CHECKIN || type == TradeTypeEnum.FREELIFE) {
			isIncrease = true;
		}
		// step 3 : 得到最终功德数
		Integer finalMeritValue = isIncrease ? (account.getMeritValue() + meritValue)
				: (account.getMeritValue() - meritValue);
		if (finalMeritValue < 0) {
			throw new ServiceException(ServiceException.MERIT_POOL);
		}
		// step 4 : 更新账户功德数（充值或者放生不更新，等确认充值成功再更新）
		if (type != TradeTypeEnum.CHARGE && type != TradeTypeEnum.FREELIFE) {
			account.setMeritValue(finalMeritValue);
			accountDao.update(account);
		}
		// step 5 : 保存交易记录（交易状态默认为已完成，充值或者放生为待完成）
		Trade trade = new Trade();
		trade.setUserId(user);
		trade.setType(type);
		trade.setMeritValue(meritValue);
		trade.setTradeNo(generateTradeNo());
		if (type != TradeTypeEnum.CHARGE && type != TradeTypeEnum.FREELIFE) {
			trade.setStatus(TradeStatusEnum.COMPLETED);
		} else {
			trade.setStatus(TradeStatusEnum.PENDING);
		}
		return tardeDao.create(trade);
	}

	public Trade getTradeInfo(Integer tradeId) {
		return tardeDao.retrieveById(tradeId);
	}

	/**
	 * 生成交易流水号
	 * 
	 * @return
	 */
	private String generateTradeNo() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(sdf.format(new Date()));
		strBuilder.append(RandomUtil.generateRandomCode(6));
		return strBuilder.toString();
	}

	public static void main(String[] args) {
		TradeService service = new TradeService();
		for (int i = 0; i < 71; i++) {
			System.out.println(service.generateTradeNo());
		}
	}

}
