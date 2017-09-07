package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.SensitiveWord;

/**
 * 敏感词 Dao
 * 
 * @author luomengan
 *
 */
public interface SensitiveWordDao {

	public SensitiveWord createSensitiveWord(SensitiveWord sensitiveWord);

	public void deleteSensitiveWordById(Integer id);

	public SensitiveWord updateSensitiveWord(SensitiveWord sensitiveWord);

	public SensitiveWord retrieveSensitiveWordById(Integer id);

	public Page<SensitiveWord> pageSensitiveWord(int page, int limit);
	
	public List<SensitiveWord> listSensitiveWord();
	
	public List<SensitiveWord> listSensitiveWordByIsEnable(boolean isEnable);

}
