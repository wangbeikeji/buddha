package com.wangbei.dao.impl.jpa;

import com.wangbei.entity.Trade;
import org.springframework.data.repository.Repository;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc
 */
public interface TradeRepository extends CurdJpaRepository<Trade, Integer>, Repository<Trade, Integer> {

}
