package com.wangbei.dao.impl.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.Trade;
import com.wangbei.util.enums.TradeStatusEnum;
import com.wangbei.util.enums.TradeTypeEnum;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc
 */
public interface TradeRepository extends CurdJpaRepository<Trade, Integer>, Repository<Trade, Integer> {

	Trade findByTradeNo(String tradeNo);

	Page<Trade> findByTypeAndStatusAndMeritValueGreaterThan(TradeTypeEnum type, TradeStatusEnum status, int meritValue,
			Pageable pageable);

}
