package com.wangbei.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.RankingDao;
import com.wangbei.dao.impl.jpa.RankingRepository;

/**
 * 排行榜 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class RankingDaoImpl implements RankingDao {
	
	@Autowired
	private RankingRepository rankingRepo;

	public RankingDaoImpl() {
		// TODO Auto-generated constructor stub
	}

}
