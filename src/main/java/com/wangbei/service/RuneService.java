package com.wangbei.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wangbei.entity.Beg;
import com.wangbei.entity.Divination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.RuneDao;
import com.wangbei.entity.Rune;

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
		//从功德详情中获取未过期的供品信息
		List<Beg> begs = begService.getByUserAndExpireTimeGreaterThan(user, new Date());
		List<Rune> result = new ArrayList<>();
		for (Beg beg : begs) {
			Rune rune = getRuneInfo(beg.getRuneId());
			if (rune != null) {
				rune.setExpireTime(beg.getExpireTime());
				result.add(rune);
			}
		}
		return result;
	}

}
