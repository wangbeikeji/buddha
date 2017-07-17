package com.wangbei.service;

import com.wangbei.dao.AccountDao;
import com.wangbei.dao.TradeDao;
import com.wangbei.entity.Account;
import com.wangbei.entity.Trade;
import com.wangbei.exception.ServiceException;
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
    @Autowired
    private AccountDao accountDao;

    @Transactional
    public Trade trade(Integer user, TradeTypeEnum type, Integer meritValue) {
        //校检用户
        if(type != TradeTypeEnum.CHARGE){
            Account account = accountDao.findByUser(user);
            Integer meritsub = account.getMeritValue() - meritValue;
            if (meritsub > 0) {
                //账户余额充足，扣款
                account.setMeritValue(meritsub);
                accountDao.update(account);
            }else{
                throw new ServiceException(ServiceException.MERIT_POOL);
            }
        }
        Trade trade = new Trade();
        trade.setUserId(user);
        trade.setType(type);
        trade.setMeritValue(meritValue);
        return tardeDao.create(trade);
    }
}
