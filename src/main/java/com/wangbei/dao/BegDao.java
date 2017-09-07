package com.wangbei.dao;

import java.util.Date;
import java.util.List;

import com.wangbei.entity.Beg;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
public interface BegDao extends BaseDao<Beg, Integer> {

    Beg retrieveByUser(Integer user);

    List<Beg> begByUserAndExpireTimeGreaterThan(Integer user, Date time);
    
    List<Beg> begByUserIdAndJossIdAndExpireTimeGreaterThan(Integer userId, Integer jossId, Date time);

	List<Beg> begByUserIdAndJossId(Integer userId, Integer oldJossId);
}
