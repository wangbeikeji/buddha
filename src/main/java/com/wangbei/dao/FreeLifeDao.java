package com.wangbei.dao;

import org.springframework.data.domain.Page;

import com.wangbei.entity.FreeLife;

/**
 * @author Created by yuyidi on 2017/7/18.
 * @desc
 */
public interface FreeLifeDao extends BaseDao<FreeLife, Integer> {
	
	Page<FreeLife> pageFreeLifes(int page, int size);
	
}
