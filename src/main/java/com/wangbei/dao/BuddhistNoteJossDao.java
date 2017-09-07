package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.BuddhistNoteJoss;

/**
 * 佛录佛像 Dao
 * 
 * @author luomengan
 *
 */
public interface BuddhistNoteJossDao {

	public BuddhistNoteJoss createBuddhistNoteJoss(BuddhistNoteJoss buddhistNoteJoss);

	public void deleteBuddhistNoteJossById(Integer id);

	public BuddhistNoteJoss updateBuddhistNoteJoss(BuddhistNoteJoss buddhistNoteJoss);

	public BuddhistNoteJoss retrieveBuddhistNoteJossById(Integer id);

	public Page<BuddhistNoteJoss> pageBuddhistNoteJoss(int page, int limit);
	
	public List<BuddhistNoteJoss> listBuddhistNoteJoss();

	public BuddhistNoteJoss retrieveBuddhistNoteJossByBuddhaName(String buddha);

}
