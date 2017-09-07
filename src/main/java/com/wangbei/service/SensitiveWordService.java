package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.SensitiveWordDao;
import com.wangbei.entity.SensitiveWord;

/**
 * 敏感词 Service
 * 
 * @author luomengan
 *
 */
@Service
public class SensitiveWordService {

	@Autowired
	private SensitiveWordDao sensitiveWordDao;

	public SensitiveWord getSensitiveWordInfo(Integer id) {
		return sensitiveWordDao.retrieveSensitiveWordById(id);
	}

	@Transactional
	public SensitiveWord addSensitiveWord(SensitiveWord sensitiveWord) {
		return sensitiveWordDao.createSensitiveWord(sensitiveWord);
	}

	@Transactional
	public SensitiveWord modifySensitiveWord(SensitiveWord sensitiveWord) {
		return sensitiveWordDao.updateSensitiveWord(sensitiveWord);
	}

	@Transactional
	public void deleteSensitiveWord(Integer id) {
		sensitiveWordDao.deleteSensitiveWordById(id);
	}
	
	@Transactional
	public void deleteSensitiveWords(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					sensitiveWordDao.deleteSensitiveWordById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<SensitiveWord> sensitiveWords(int page, int limit) {
		return sensitiveWordDao.pageSensitiveWord(page, limit);
	}
	
	public List<SensitiveWord> list() {
		return sensitiveWordDao.listSensitiveWord();
	}

}
