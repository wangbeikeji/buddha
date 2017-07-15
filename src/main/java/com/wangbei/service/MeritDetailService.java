package com.wangbei.service;

import com.wangbei.dao.MeritDetailDao;
import com.wangbei.entity.MeritDetail;
import com.wangbei.util.enums.OfferingTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author yuyidi 2017-07-14 17:41:40
 * @class com.wangbei.service.MeritDetailService
 * @description 功德详情服务
 */
@Service
public class MeritDetailService {

    @Autowired
    private MeritDetailDao meritDetailDao;

    @Transactional
    public MeritDetail addMeritDetail(Integer user, Integer offerings, Integer meritValue, OfferingTypeEnum type) {
        //TODO 需根据当前用户与供品类型查询出对应的用户是否已经添加供品，
        // 若已添加，则判断供品是否已经过期，如果没有过期，则提示不允许再次添加供品信息
        MeritDetail meritDetail = new MeritDetail(user, offerings, meritValue, type);
        meritDetail.expire();
        return meritDetailDao.create(meritDetail);
    }

    @Transactional
    public MeritDetail addMeritDetail(MeritDetail meritDetail) {
        return meritDetailDao.create(meritDetail);
    }

    /**
    * @author yuyidi 2017-07-15 15:16:58
    * @method getByUserAndExpireTimeLessthan
    * @param user
    * @param date
    * @return java.util.List<com.wangbei.entity.MeritDetail>
    * @description 获取用户供佛的供品信息
    */
    public List<MeritDetail> getByUserAndExpireTimeLessthan(Integer user, Date date) {
        return meritDetailDao.meritDetailsByUserAndExpireTimeGreaterThan(user,date);
    }
}
