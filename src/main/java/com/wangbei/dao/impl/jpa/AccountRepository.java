package com.wangbei.dao.impl.jpa;

import com.wangbei.entity.Account;
import org.springframework.data.repository.Repository;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc
 */
public interface AccountRepository extends CurdJpaRepository<Account, Integer> ,Repository<Account,Integer> {

    Account findByUserId(Integer user);
}
