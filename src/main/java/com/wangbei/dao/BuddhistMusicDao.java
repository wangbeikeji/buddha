package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.BuddhistMusic;

/**
 * 佛音 Dao
 * 
 * @author luomengan
 *
 */
public interface BuddhistMusicDao {

	public BuddhistMusic createBuddhistMusic(BuddhistMusic buddhistMusic);

	public void deleteBuddhistMusicById(Integer id);

	public BuddhistMusic updateBuddhistMusic(BuddhistMusic buddhistMusic);

	public BuddhistMusic retrieveBuddhistMusicById(Integer id);
	
	public BuddhistMusic retrieveBuddhistMusicByLink(String link);

	public Page<BuddhistMusic> pageBuddhistMusic(int page, int limit);
	
	public List<BuddhistMusic> listBuddhistMusic();

	public List<BuddhistMusic> listBuddhistMusicByCategoryId(int categoryId);

	public List<BuddhistMusic> listBuddhistMusicByCategoryIdAndIsHomeTop(int categoryId, boolean isHomeTop);

	public Page<BuddhistMusic> pageByCategoryId(int categoryId, int page, int limit);

}
