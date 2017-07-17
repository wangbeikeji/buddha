package com.wangbei.service;

import com.wangbei.dao.MeritDetailDao;
import com.wangbei.entity.MeritDetail;
import com.wangbei.entity.Offerings;
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
    public MeritDetail addMeritDetail(Integer user, Offerings offerings) {
        MeritDetail meritDetail = getByUserAndType(user, offerings.getType());
        if (meritDetail != null) {
            if (meritDetail.getExpireTime().getTime() < System.currentTimeMillis()) {
                //若还未过期
                return meritDetail;
            }
            //若过期时间超过当前日期，则允许并更新当前供品信息
            meritDetail.expire();
            MeritDetail result = addMeritDetail(meritDetail);
            return result;
        }
        MeritDetail request = new MeritDetail(user, offerings.getId(), offerings.getMeritValue(), offerings.getType());
        request.expire();
        return meritDetailDao.create(request);
    }

    @Transactional
    public MeritDetail addMeritDetail(MeritDetail meritDetail) {
        return meritDetailDao.create(meritDetail);
    }

    /**
     * @param user
     * @param date
     * @return java.util.List<com.wangbei.entity.MeritDetail>
     * @author yuyidi 2017-07-15 15:16:58
     * @method getByUserAndExpireTimeLessthan
     * @description 获取用户供佛的供品信息
     */
    public List<MeritDetail> getByUserAndExpireTimeLessthan(Integer user, Date date) {
        return meritDetailDao.meritDetailsByUserAndExpireTimeGreaterThan(user, date);
    }

    /**
     * @param user
     * @param type
     * @return com.wangbei.entity.MeritDetail
     * @author yuyidi 2017-07-17 09:59:04
     * @method getByUserAndType
     * @description 根据用户与供品类型获取供品功德详情
     */
    public MeritDetail getByUserAndType(Integer user, OfferingTypeEnum type) {
        return meritDetailDao.meritDetailByUserAndType(user, type);
    }
}
