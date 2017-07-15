package com.wangbei.dao;

import com.wangbei.entity.MeritDetail;

import java.util.Date;
import java.util.List;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
public interface MeritDetailDao extends BaseDao<MeritDetail,Integer> {

    List<MeritDetail> meritDetailsByUserAndExpireTimeGreaterThan(Integer user,Date time);

}
