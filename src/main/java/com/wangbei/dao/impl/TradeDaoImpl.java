package com.wangbei.dao.impl;

import com.wangbei.dao.TradeDao;
import com.wangbei.dao.impl.jpa.TradeRepository;
import com.wangbei.entity.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc
 */
@Repository
public class TradeDaoImpl implements TradeDao {

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public Trade create(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Trade update(Trade trade) {
        return null;
    }

    @Override
    public Trade retrieveById(Integer id) {
        return null;
    }

    @Override
    public Page<Trade> page(int page, int limit) {
        return null;
    }

    @Override
    public List<Trade> list() {
        return null;
    }
}
