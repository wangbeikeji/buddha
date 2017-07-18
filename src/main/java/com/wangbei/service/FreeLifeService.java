package com.wangbei.service;

import com.wangbei.dao.FreeLifeDao;
import com.wangbei.entity.FreeLife;
import com.wangbei.entity.Trade;
import com.wangbei.exception.ServiceException;
import com.wangbei.util.enums.CreatureEnum;
import com.wangbei.util.enums.TradeTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Created by yuyidi on 2017/7/18.
 * @desc
 */
@Service
public class FreeLifeService {

    @Autowired
    private FreeLifeDao freeLifeDao;
    @Autowired
    private TradeService tradeService;

    @Transactional
    public FreeLife addFreeLife(Integer user, CreatureEnum type, Integer meritValue) {
        Trade trade = tradeService.trade(user, TradeTypeEnum.FREELIFE, meritValue);
        if (trade != null) {
            FreeLife request = new FreeLife(user,type.getIndex(),meritValue);
            return freeLifeDao.create(request);
        }
        throw new ServiceException(ServiceException.FREE_LIFE_EXCEPTION);
    }
}
