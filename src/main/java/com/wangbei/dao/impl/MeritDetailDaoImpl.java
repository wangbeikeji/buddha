package com.wangbei.dao.impl;

import com.wangbei.dao.MeritDetailDao;
import com.wangbei.dao.impl.jpa.MeritDetailRepository;
import com.wangbei.entity.MeritDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
@Repository
public class MeritDetailDaoImpl implements MeritDetailDao {

    @Autowired
    private MeritDetailRepository meritDetailRepository;

    @Override
    public MeritDetail create(MeritDetail meritDetail) {
        return meritDetailRepository.save(meritDetail);
    }

    @Override
    public void deleteById(Integer id) {
        meritDetailRepository.delete(id);
    }

    @Override
    public MeritDetail update(MeritDetail meritDetail) {
        return meritDetailRepository.save(meritDetail);
    }

    @Override
    public MeritDetail retrieveById(Integer id) {
        return meritDetailRepository.findById(id);
    }

    @Override
    public Page<MeritDetail> page(int page, int limit) {
        return meritDetailRepository.findAll(new PageRequest(page,limit));
    }

    @Override
    public List<MeritDetail> list() {
        return meritDetailRepository.findAll();
    }

    @Override
    public List<MeritDetail> meritDetailsByUserAndExpireTimeGreaterThan(Integer user, Date date) {
        return meritDetailRepository.findAllByUserIdAndExpireTimeGreaterThan(user,date);
    }
}
