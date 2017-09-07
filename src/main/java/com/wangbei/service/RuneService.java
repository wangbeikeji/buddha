package com.wangbei.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.RuneDao;
import com.wangbei.entity.Beg;
import com.wangbei.entity.Rune;
import com.wangbei.pojo.RuneWithBegInfo;

/**
 * 符文 Service
 * 
 * @author luomengan
 *
 */
@Service
public class RuneService {

	@Autowired
	private RuneDao runeDao;
	@Autowired
	private BegService begService;

	public Rune getRuneInfo(Integer id) {
		return runeDao.retrieveRuneById(id);
	}

	@Transactional
	public Rune addRune(Rune rune) {
		return runeDao.createRune(rune);
	}

	@Transactional
	public Rune modifyRune(Rune rune) {
		return runeDao.updateRune(rune);
	}

	@Transactional
	public void deleteRune(Integer id) {
		runeDao.deleteRuneById(id);
	}

	public Page<Rune> runes(int page, int limit) {
		return runeDao.pageRune(page, limit);
	}

	public List<Rune> list() {
		return runeDao.listRune();
	}

	/**
	 * @author yuyidi 2017-07-17 14:08:45
	 * @method getDivinationByUser
	 * @param user
	 * @return java.util.List<com.wangbei.entity.Divination>
	 * @description 根据用户获取用户的求符信息
	 */
	public List<Rune> getRuneByUser(Integer user) {
		// 从功德详情中获取未过期的供品信息
		List<Beg> begs = begService.getByUserAndExpireTimeGreaterThan(user, new Date());
		List<Rune> result = new ArrayList<>();
		for (Beg beg : begs) {
			Rune rune = getRuneInfo(beg.getRuneId());
			if (rune != null) {
				rune.setCreateTime(beg.getCreateTime());
				rune.setExpireTime(beg.getExpireTime());
				result.add(rune);
			}
		}
		return result;
	}

	/**
	 * 根据用户id和佛id获取符文
	 * 
	 * @param userId
	 *            用户id
	 * @param jossId
	 *            佛id
	 * @return 符文
	 */
	public List<Rune> getRuneByUserIdAndJossId(Integer userId, Integer jossId) {
		// 从功德详情中获取未过期的供品信息
		List<Beg> begs = begService.getByUserIdAndJossIdAndExpireTimeGreaterThan(userId, jossId, new Date());
		List<Rune> result = new ArrayList<>();
		for (Beg beg : begs) {
			Rune rune = getRuneInfo(beg.getRuneId());
			if (rune != null) {
				rune.setCreateTime(beg.getCreateTime());
				rune.setExpireTime(beg.getExpireTime());
				result.add(rune);
			}
		}
		return result;
	}

	/**
	 * 根据用户id和佛id获取符文
	 * 
	 * @param userId
	 *            用户id
	 * @param jossId
	 *            佛id
	 * @return 符文
	 */
	public List<RuneWithBegInfo> getRuneWithBegInfoByUserIdAndJossId(Integer userId, Integer jossId) {
		List<RuneWithBegInfo> result = new ArrayList<>();
		// 从功德详情中获取未过期的供品信息
		List<Beg> begs = begService.getByUserIdAndJossIdAndExpireTimeGreaterThan(userId, jossId, new Date());
		for (Beg beg : begs) {
			Rune rune = getRuneInfo(beg.getRuneId());
			if (rune != null) {
				rune.setCreateTime(beg.getCreateTime());
				rune.setExpireTime(beg.getExpireTime());
				result.add(new RuneWithBegInfo(rune, beg));
			}
		}
		return result;
	}

}
