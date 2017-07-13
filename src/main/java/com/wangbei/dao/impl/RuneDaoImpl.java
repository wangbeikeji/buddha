package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.RuneDao;
import com.wangbei.dao.impl.jpa.RuneRepository;
import com.wangbei.entity.Rune;

/**
 * 符文 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class RuneDaoImpl implements RuneDao {

	@Autowired
	private RuneRepository runeRepository;

	@Override
	public Rune createRune(Rune rune) {
		return runeRepository.save(rune);
	}

	@Override
	public void deleteRuneById(Integer id) {
		runeRepository.delete(id);
	}

	@Override
	public Rune updateRune(Rune rune) {
		return runeRepository.save(rune);
	}

	@Override
	public Rune retrieveRuneById(Integer id) {
		return runeRepository.findById(id);
	}

	@Override
	public Page<Rune> pageRune(int page, int limit) {
		return runeRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<Rune> listRune() {
		return runeRepository.findAll();
	}

}
