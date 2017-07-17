package com.wangbei.dao;

import com.wangbei.entity.Account;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc
 */
public interface AccountDao extends BaseDao<Account,Integer>{

    Account findByUser(Integer user);
}
