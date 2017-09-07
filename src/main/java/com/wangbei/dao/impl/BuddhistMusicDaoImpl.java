package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.BuddhistMusicDao;
import com.wangbei.dao.impl.jpa.BuddhistMusicRepository;
import com.wangbei.entity.BuddhistMusic;

/**
 * 佛音 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class BuddhistMusicDaoImpl implements BuddhistMusicDao {

	@Autowired
	private BuddhistMusicRepository buddhistMusicRepository;

	@Override
	public BuddhistMusic createBuddhistMusic(BuddhistMusic buddhistMusic) {
		return buddhistMusicRepository.save(buddhistMusic);
	}

	@Override
	public void deleteBuddhistMusicById(Integer id) {
		buddhistMusicRepository.delete(id);
	}

	@Override
	public BuddhistMusic updateBuddhistMusic(BuddhistMusic buddhistMusic) {
		return buddhistMusicRepository.save(buddhistMusic);
	}

	@Override
	public BuddhistMusic retrieveBuddhistMusicById(Integer id) {
		return buddhistMusicRepository.findById(id);
	}

	@Override
	public Page<BuddhistMusic> pageBuddhistMusic(int page, int limit) {
		return buddhistMusicRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<BuddhistMusic> listBuddhistMusic() {
		return buddhistMusicRepository.findAll();
	}

	@Override
	public List<BuddhistMusic> listBuddhistMusicByCategoryId(int categoryId) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "sortNum"));
		return buddhistMusicRepository.findByCategoryId(categoryId, sort);
	}

	@Override
	public BuddhistMusic retrieveBuddhistMusicByLink(String link) {
		return buddhistMusicRepository.findByLink(link);
	}

	@Override
	public List<BuddhistMusic> listBuddhistMusicByCategoryIdAndIsHomeTop(int categoryId, boolean isHomeTop) {
		return buddhistMusicRepository.findByCategoryIdAndIsHomeTop(categoryId, isHomeTop);
	}

	@Override
	public Page<BuddhistMusic> pageByCategoryId(int categoryId, int page, int limit) {
		Pageable pagable = new PageRequest(page, limit, new Sort(new Sort.Order(Direction.DESC, "sortNum")));
		return buddhistMusicRepository.findByCategoryId(categoryId, pagable);
	}

}
