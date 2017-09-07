package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.BuddhistNoteJossDao;
import com.wangbei.entity.BuddhistNoteJoss;

/**
 * 佛录佛像 Service
 * 
 * @author luomengan
 *
 */
@Service
public class BuddhistNoteJossService {

	@Autowired
	private BuddhistNoteJossDao buddhistNoteJossDao;

	public BuddhistNoteJoss getBuddhistNoteJossInfo(Integer id) {
		return buddhistNoteJossDao.retrieveBuddhistNoteJossById(id);
	}

	@Transactional
	public BuddhistNoteJoss addBuddhistNoteJoss(BuddhistNoteJoss buddhistNoteJoss) {
		return buddhistNoteJossDao.createBuddhistNoteJoss(buddhistNoteJoss);
	}

	@Transactional
	public BuddhistNoteJoss modifyBuddhistNoteJoss(BuddhistNoteJoss buddhistNoteJoss) {
		return buddhistNoteJossDao.updateBuddhistNoteJoss(buddhistNoteJoss);
	}

	@Transactional
	public void deleteBuddhistNoteJoss(Integer id) {
		buddhistNoteJossDao.deleteBuddhistNoteJossById(id);
	}
	
	@Transactional
	public void deleteBuddhistNoteJosss(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					buddhistNoteJossDao.deleteBuddhistNoteJossById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<BuddhistNoteJoss> buddhistNoteJosss(int page, int limit) {
		return buddhistNoteJossDao.pageBuddhistNoteJoss(page, limit);
	}
	
	public List<BuddhistNoteJoss> list() {
		return buddhistNoteJossDao.listBuddhistNoteJoss();
	}

}
