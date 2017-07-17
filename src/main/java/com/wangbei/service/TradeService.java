package com.wangbei.service;

import com.wangbei.dao.TradeDao;
import com.wangbei.entity.Trade;
import com.wangbei.util.enums.TradeTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc
 */
@Service
public class TradeService {

    @Autowired
    private TradeDao tardeDao;

    @Transactional
    public Trade trade(Integer user, TradeTypeEnum type,Integer meritValue){
        Trade trade = new Trade();
        trade.setUserId(user);
        trade.setType(type);
        trade.setMeritValue(meritValue);
        return tardeDao.create(trade);
    }
}
