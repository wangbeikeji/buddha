package com.wangbei.dao.impl;

import com.wangbei.dao.JossDao;
import com.wangbei.dao.impl.jpa.JossRepository;
import com.wangbei.entity.Joss;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

/**
 * @author Created by yuyidi on 2017/7/11.
 * @desc
 */
@Repository
public class JossDaoImpl implements JossDao {

    @Autowired
    private JossRepository jossRepository;

    @Override
    public Joss createJoss(Joss joss) {
        return jossRepository.save(joss);
    }

    @Override
    public void deleteJossById(Integer id) {
        jossRepository.delete(id);
    }

    @Override
    public Joss updateJoss(Joss joss) {
        return jossRepository.save(joss);
    }

    @Override
    public Joss retrieveJossById(Integer id) {
        return jossRepository.findById(id);
    }

    @Override
    public Page<Joss> pageJoss(int page, int limit) {
        return jossRepository.findAll(new PageRequest(page, limit));
    }

	@Override
	public List<Joss> listJoss() {
		return jossRepository.findAll();
	}
}
