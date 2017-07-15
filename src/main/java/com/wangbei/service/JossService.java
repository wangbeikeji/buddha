package com.wangbei.service;

import java.util.List;

import javax.transaction.Transactional;

import com.wangbei.entity.Hereby;
import com.wangbei.entity.MeritDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.wangbei.dao.JossDao;
import com.wangbei.entity.Joss;

/**
 * @author yuyidi 2017-07-11 11:57:25
 * @class com.wangbei.service.JossService
 * @description 佛信息
 */
@Service
public class JossService {

	@Autowired
	private JossDao jossDao;
	@Autowired
	private HerebyService herebyService;

	public Joss getJossInfo(Integer id) {
		return jossDao.retrieveJossById(id);
	}

	public Joss getJossByUser(Integer user) {
		Joss result = null;
		Hereby hereby = herebyService.findByUser(user);
		if (hereby != null) {
			result = jossDao.retrieveJossById(hereby.getJossId());
		}
		return result;
	}

	@Transactional
	public Joss addJoss(Joss joss) {
		return jossDao.createJoss(joss);
	}

	@Transactional
	public Joss modifyJoss(Joss joss) {
		return jossDao.updateJoss(joss);
	}

	@Transactional
	public void deleteJoss(Integer id) {
		jossDao.deleteJossById(id);
	}

	public Page<Joss> josss(int page, int limit) {
		return jossDao.pageJoss(page, limit);
	}

	public List<Joss> list() {
		return jossDao.listJoss();
	}

}
