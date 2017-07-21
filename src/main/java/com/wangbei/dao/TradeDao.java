package com.wangbei.dao;

import com.wangbei.entity.Trade;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc
 */
public interface TradeDao extends BaseDao<Trade,Integer> {
	
	Trade retrieveByTradeNo(String tradeNo);
	
}
