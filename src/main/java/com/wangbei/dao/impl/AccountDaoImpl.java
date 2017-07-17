package com.wangbei.dao.impl;

import com.wangbei.dao.AccountDao;
import com.wangbei.dao.impl.jpa.AccountRepository;
import com.wangbei.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Account update(Account account) {
        return null;
    }

    @Override
    public Account retrieveById(Integer id) {
        return null;
    }

    @Override
    public Page<Account> page(int page, int limit) {
        return null;
    }

    @Override
    public List<Account> list() {
        return null;
    }

    @Override
    public Account findByUser(Integer user) {
        return accountRepository.findByUserId(user);
    }
}
