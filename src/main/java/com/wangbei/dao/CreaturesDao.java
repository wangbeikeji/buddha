package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.Creatures;

/**
 * 生灵 Dao
 * 
 * @author luomengan
 *
 */
public interface CreaturesDao {

	public Creatures createCreatures(Creatures creatures);

	public void deleteCreaturesById(Integer id);

	public Creatures updateCreatures(Creatures creatures);

	public Creatures retrieveCreaturesById(Integer id);

	public Page<Creatures> pageCreatures(int page, int limit);
	
	public List<Creatures> listCreatures();

}
