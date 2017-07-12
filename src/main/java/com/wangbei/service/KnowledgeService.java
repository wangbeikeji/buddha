package com.wangbei.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.KnowledgeDao;
import com.wangbei.entity.Knowledge;
import com.wangbei.util.enums.KnowledgeTypeEnum;

/**
 * 佛山知识 Service
 * 
 * @author luomengan
 *
 */
@Service
public class KnowledgeService {

	@Autowired
	private KnowledgeDao knowledgeDao;

	public Knowledge getKnowledgeInfo(Integer id) {
		return knowledgeDao.retrieveKnowledgeById(id);
	}

	@Transactional
	public Knowledge addKnowledge(Knowledge knowledge) {
		knowledge.setCreateTime(new Date());
		return knowledgeDao.createKnowledge(knowledge);
	}

	@Transactional
	public Knowledge modifyKnowledge(Knowledge knowledge) {
		return knowledgeDao.updateKnowledge(knowledge);
	}

	@Transactional
	public void deleteKnowledge(Integer id) {
		knowledgeDao.deleteKnowledgeById(id);
	}

	public Page<Knowledge> knowledgesByType(KnowledgeTypeEnum type, int page, int limit) {
		return knowledgeDao.pageKnowledgeByType(type, page, limit);
	}

	public Page<Knowledge> knowledges(int page, int limit) {
		return knowledgeDao.pageKnowledge(page, limit);
	}

}
