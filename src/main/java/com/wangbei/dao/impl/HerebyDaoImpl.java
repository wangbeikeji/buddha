package com.wangbei.dao.impl;

import com.wangbei.dao.HerebyDao;
import com.wangbei.dao.impl.jpa.HerebyRepository;
import com.wangbei.entity.Hereby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
@Repository
public class HerebyDaoImpl implements HerebyDao{

    @Autowired
    private HerebyRepository herebyRepository;

    @Override
    public Hereby create(Hereby hereby) {
        return herebyRepository.save(hereby);
    }

    @Override
    public void deleteById(Integer id) {
        herebyRepository.delete(id);
    }

    @Override
    public Hereby update(Hereby hereby) {
        return herebyRepository.save(hereby);
    }

    @Override
    public Hereby retrieveById(Integer id) {
        return herebyRepository.findById(id);
    }

    @Override
    public Page<Hereby> page(int page, int limit) {
        return herebyRepository.findAll(new PageRequest(page,limit));
    }

    @Override
    public List<Hereby> list() {
        return herebyRepository.findAll();
    }

    @Override
    public Integer updateJossByUser(Integer userId, Integer joss) {
        return herebyRepository.updateJossByUser(userId,joss);
    }
}
