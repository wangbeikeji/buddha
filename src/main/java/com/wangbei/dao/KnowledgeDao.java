package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.Knowledge;
import com.wangbei.util.enums.KnowledgeTypeEnum;

/**
 * 佛学知识 Dao
 * 
 * @author luomengan
 *
 */
public interface KnowledgeDao {

	public Knowledge createKnowledge(Knowledge knowledge);

	public void deleteKnowledgeById(Integer id);

	public Knowledge updateKnowledge(Knowledge knowledge);

	public Knowledge retrieveKnowledgeById(Integer id);

	public Page<Knowledge> pageKnowledgeByType(KnowledgeTypeEnum type, int page, int limit);

	public Page<Knowledge> pageKnowledge(int page, int limit);
	
	public List<Knowledge> listKnowledgeByType(KnowledgeTypeEnum type);
	
	public List<Knowledge> listKnowledge();
	
}