package com.wangbei.dao;

import org.springframework.data.domain.Page;

import com.wangbei.entity.Trade;
import com.wangbei.util.enums.TradeStatusEnum;
import com.wangbei.util.enums.TradeTypeEnum;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc
 */
public interface TradeDao extends BaseDao<Trade,Integer> {
	
	Trade retrieveByTradeNo(String tradeNo);

	Page<Trade> pageTradesByTypeAndStatus(TradeTypeEnum freelife, TradeStatusEnum completed, int page, int size);
	
}
