package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.AlmanacDao;
import com.wangbei.dao.impl.jpa.AlmanacRepository;
import com.wangbei.entity.Almanac;

/**
 * 黄历+佛历 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class AlmanacDaoImpl implements AlmanacDao {

	@Autowired
	private AlmanacRepository almanacRepository;

	@Override
	public Almanac createAlmanac(Almanac almanac) {
		return almanacRepository.save(almanac);
	}

	@Override
	public void deleteAlmanacById(Integer id) {
		almanacRepository.delete(id);
	}

	@Override
	public Almanac updateAlmanac(Almanac almanac) {
		return almanacRepository.save(almanac);
	}

	@Override
	public Almanac retrieveAlmanacById(Integer id) {
		return almanacRepository.findById(id);
	}

	@Override
	public Page<Almanac> pageAlmanac(int page, int limit) {
		return almanacRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<Almanac> listAlmanac() {
		return almanacRepository.findAll();
	}

	@Override
	public Almanac retrieveAlmanacByGregorianCalendar(String gregorianCalendar) {
		return almanacRepository.findByGregorianCalendar(gregorianCalendar);
	}

	@Override
	public List<Almanac> listAlmanacBetweenYear(int startRecentYear, int endRecentYear) {
		return almanacRepository.findByBetweenYear(startRecentYear + "0101", endRecentYear + "1231");
	}

}
