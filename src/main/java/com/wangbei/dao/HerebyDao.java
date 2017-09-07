package com.wangbei.dao;

import java.util.List;

import com.wangbei.entity.Hereby;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
public interface HerebyDao extends BaseDao<Hereby, Integer> {

    Hereby retrieveByUser(Integer user);
    
	Hereby retrieveByUserIdAndJossId(Integer userId, Integer newJossId);

	void deleteByUserIdAndJossId(Integer userId, Integer jossId);
	
	List<Hereby> listByUserId(Integer userId);
	
}
