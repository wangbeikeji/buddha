package com.wangbei.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.KnowledgeDao;
import com.wangbei.dao.impl.jpa.KnowledgeRepository;
import com.wangbei.entity.Knowledge;
import com.wangbei.util.enums.KnowledgeTypeEnum;

/**
 * 佛学知识 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class KnowledgeDaoImpl implements KnowledgeDao {

	@Autowired
	private KnowledgeRepository knowledgeRepository;

	@Override
	public Knowledge createKnowledge(Knowledge knowledge) {
		return knowledgeRepository.save(knowledge);
	}

	@Override
	public void deleteKnowledgeById(Integer id) {
		knowledgeRepository.delete(id);
	}

	@Override
	public Knowledge updateKnowledge(Knowledge knowledge) {
		return knowledgeRepository.save(knowledge);
	}

	@Override
	public Knowledge retrieveKnowledgeById(Integer id) {
		return knowledgeRepository.findById(id);
	}

	@Override
	public Page<Knowledge> pageKnowledgeByType(KnowledgeTypeEnum type, int offset, int limit) {
		return knowledgeRepository.findByType(type, new PageRequest(offset, limit));
	}

	@Override
	public Page<Knowledge> pageKnowledge(int offset, int limit) {
		return knowledgeRepository.findAll(new PageRequest(offset, limit));
	}

}
