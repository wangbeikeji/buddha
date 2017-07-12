package com.wangbei.dao;

import org.springframework.data.domain.Page;

import com.wangbei.entity.Divination;

/**
 * 灵签 Dao
 * 
 * @author luomengan
 *
 */
public interface DivinationDao {

	public Divination createDivination(Divination divination);

	public void deleteDivinationById(Integer id);

	public Divination updateDivination(Divination divination);

	public Divination retrieveDivinationById(Integer id);

	public Page<Divination> pageDivination(int page, int limit);

}
