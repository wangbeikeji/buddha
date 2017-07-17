package com.wangbei.dao.impl;

import com.wangbei.dao.BegDao;
import com.wangbei.dao.impl.jpa.BegRepository;
import com.wangbei.entity.Beg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc
 */
@Repository
public class BegDaoImpl implements BegDao{

    @Autowired
    private BegRepository begRepository;

    @Override
    public Beg create(Beg beg) {
        return begRepository.save(beg);
    }

    @Override
    public void deleteById(Integer id) {
        begRepository.delete(id);
    }

    @Override
    public Beg update(Beg beg) {
        Assert.notNull(beg.getId(),"标识不能为空");
        return begRepository.save(beg);
    }

    @Override
    public Beg retrieveById(Integer id) {
        return begRepository.findById(id);
    }

    @Override
    public Page<Beg> page(int page, int limit) {
        return begRepository.findAll(new PageRequest(page,limit));
    }

    @Override
    public List<Beg> list() {
        return begRepository.findAll();
    }

    @Override
    public Beg retrieveByUser(Integer user) {
        return begRepository.findByUserId(user);
    }

    @Override
    public List<Beg> begByUserAndExpireTimeGreaterThan(Integer user, Date time) {
        return begRepository.findAllByUserIdAndExpireTimeGreaterThan(user,time);
    }
}
