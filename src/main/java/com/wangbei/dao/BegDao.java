package com.wangbei.dao;

import com.wangbei.entity.Beg;
import com.wangbei.entity.Hereby;

import java.util.Date;
import java.util.List;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
public interface BegDao extends BaseDao<Beg, Integer> {

    Beg retrieveByUser(Integer user);

    List<Beg> begByUserAndExpireTimeGreaterThan(Integer user, Date time);
}
