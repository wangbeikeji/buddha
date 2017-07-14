package com.wangbei.service;

import com.wangbei.dao.HerebyDao;
import com.wangbei.entity.Hereby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Hereby addHereby(Hereby hereby) {
        return herebyDao.create(hereby);
    }
}
