package com.wangbei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.HerebyDao;
import com.wangbei.entity.Hereby;

/**
 * @author yuyidi 2017-07-14 11:33:00
 * @class com.wangbei.service.HerebyService
 * @description 恭请佛业务
 */
@Service
public class HerebyService {

	@Autowired
	private HerebyDao herebyDao;

    @Transactional
    public Hereby addHereby(Integer user, Integer joss) {
        Hereby hereby = new Hereby(user, joss);
        return herebyDao.create(hereby);
    }

    @Transactional
    public Hereby modifyHereby(Integer id,Integer user, Integer joss) {
        Hereby hereby = new Hereby(user,joss);
        hereby.setId(id);
        return herebyDao.update(hereby);
    }

    public Hereby findByUser(Integer user) {
        return herebyDao.retrieveByUser(user);
    }

}
