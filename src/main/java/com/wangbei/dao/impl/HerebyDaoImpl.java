package com.wangbei.dao.impl;

import com.wangbei.dao.HerebyDao;
import com.wangbei.entity.Hereby;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
@Repository
public class HerebyDaoImpl implements HerebyDao{
    @Override
    public Hereby create(Hereby hereby) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Hereby update(Hereby hereby) {
        return null;
    }

    @Override
    public Hereby retrieveById(Integer id) {
        return null;
    }

    @Override
    public Page<Hereby> page(int page, int limit) {
        return null;
    }

    @Override
    public List<Hereby> list() {
        return null;
    }
}
