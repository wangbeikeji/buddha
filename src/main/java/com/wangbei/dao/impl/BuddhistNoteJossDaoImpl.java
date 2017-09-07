package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.BuddhistNoteJossDao;
import com.wangbei.dao.impl.jpa.BuddhistNoteJossRepository;
import com.wangbei.entity.BuddhistNoteJoss;

/**
 * 佛录佛像 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class BuddhistNoteJossDaoImpl implements BuddhistNoteJossDao {

	@Autowired
	private BuddhistNoteJossRepository buddhistNoteJossRepository;

	@Override
	public BuddhistNoteJoss createBuddhistNoteJoss(BuddhistNoteJoss buddhistNoteJoss) {
		return buddhistNoteJossRepository.save(buddhistNoteJoss);
	}

	@Override
	public void deleteBuddhistNoteJossById(Integer id) {
		buddhistNoteJossRepository.delete(id);
	}

	@Override
	public BuddhistNoteJoss updateBuddhistNoteJoss(BuddhistNoteJoss buddhistNoteJoss) {
		return buddhistNoteJossRepository.save(buddhistNoteJoss);
	}

	@Override
	public BuddhistNoteJoss retrieveBuddhistNoteJossById(Integer id) {
		return buddhistNoteJossRepository.findById(id);
	}

	@Override
	public Page<BuddhistNoteJoss> pageBuddhistNoteJoss(int page, int limit) {
		return buddhistNoteJossRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<BuddhistNoteJoss> listBuddhistNoteJoss() {
		return buddhistNoteJossRepository.findAll();
	}

	@Override
	public BuddhistNoteJoss retrieveBuddhistNoteJossByBuddhaName(String buddha) {
		return buddhistNoteJossRepository.findByBuddhaName(buddha);
	}

}
