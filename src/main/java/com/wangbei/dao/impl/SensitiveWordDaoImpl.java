package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.SensitiveWordDao;
import com.wangbei.dao.impl.jpa.SensitiveWordRepository;
import com.wangbei.entity.SensitiveWord;

/**
 * 敏感词 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class SensitiveWordDaoImpl implements SensitiveWordDao {

	@Autowired
	private SensitiveWordRepository sensitiveWordRepository;

	@Override
	public SensitiveWord createSensitiveWord(SensitiveWord sensitiveWord) {
		return sensitiveWordRepository.save(sensitiveWord);
	}

	@Override
	public void deleteSensitiveWordById(Integer id) {
		sensitiveWordRepository.delete(id);
	}

	@Override
	public SensitiveWord updateSensitiveWord(SensitiveWord sensitiveWord) {
		return sensitiveWordRepository.save(sensitiveWord);
	}

	@Override
	public SensitiveWord retrieveSensitiveWordById(Integer id) {
		return sensitiveWordRepository.findById(id);
	}

	@Override
	public Page<SensitiveWord> pageSensitiveWord(int page, int limit) {
		return sensitiveWordRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<SensitiveWord> listSensitiveWord() {
		return sensitiveWordRepository.findAll();
	}

	@Override
	public List<SensitiveWord> listSensitiveWordByIsEnable(boolean isEnable) {
		return sensitiveWordRepository.findByIsEnable(isEnable);
	}

}
