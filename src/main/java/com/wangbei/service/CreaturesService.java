package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.CreaturesDao;
import com.wangbei.entity.Creatures;

/**
 * 生灵 Service
 * 
 * @author luomengan
 *
 */
@Service
public class CreaturesService {

	@Autowired
	private CreaturesDao creaturesDao;

	public Creatures getCreaturesInfo(Integer id) {
		return creaturesDao.retrieveCreaturesById(id);
	}

	@Transactional
	public Creatures addCreatures(Creatures creatures) {
		return creaturesDao.createCreatures(creatures);
	}

	@Transactional
	public Creatures modifyCreatures(Creatures creatures) {
		return creaturesDao.updateCreatures(creatures);
	}

	@Transactional
	public void deleteCreatures(Integer id) {
		creaturesDao.deleteCreaturesById(id);
	}
	
	@Transactional
	public void deleteCreaturess(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					creaturesDao.deleteCreaturesById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<Creatures> creaturess(int page, int limit) {
		return creaturesDao.pageCreatures(page, limit);
	}
	
	public List<Creatures> list() {
		return creaturesDao.listCreatures();
	}

}
