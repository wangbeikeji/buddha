package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.Rune;

/**
 * 符文 Dao
 * 
 * @author luomengan
 *
 */
public interface RuneDao {

	public Rune createRune(Rune rune);

	public void deleteRuneById(Integer id);

	public Rune updateRune(Rune rune);

	public Rune retrieveRuneById(Integer id);

	public Page<Rune> pageRune(int page, int limit);

	public List<Rune> listRune();

}
