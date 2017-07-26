package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	public Page<Knowledge> pageKnowledgeByType(KnowledgeTypeEnum type, int page, int limit) {
		Pageable pageable = new PageRequest(page, limit, new Sort(new Sort.Order(Direction.DESC, "sortNum")));
		return knowledgeRepository.findByType(type, pageable);
	}

	@Override
	public Page<Knowledge> pageKnowledge(int page, int limit) {
		Pageable pageable = new PageRequest(page, limit, new Sort(new Sort.Order(Direction.DESC, "sortNum")));
		return knowledgeRepository.findAll(pageable);
	}

	@Override
	public List<Knowledge> listKnowledgeByType(KnowledgeTypeEnum type) {
		return knowledgeRepository.findByType(type);
	}

	@Override
	public List<Knowledge> listKnowledge() {
		return knowledgeRepository.findAll();
	}

}
