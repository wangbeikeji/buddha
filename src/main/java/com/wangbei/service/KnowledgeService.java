package com.wangbei.service;

import java.util.Date;
import java.util.List;

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
		String link = knowledge.getLink(); 
		if(link != null && link.indexOf("<img src=") >= 0) {
			// 表示当前为富文件编辑器添加的，对link进行处理
			int imgIndex = link.indexOf("<img src=");
			int titleIndex = link.indexOf("title=");
			
			if(imgIndex > 0 && titleIndex > 0){
				link = link.substring(imgIndex + 10, titleIndex - 2);
				link = link.substring(link.indexOf("buddha") + 6);
				knowledge.setLink(link);
			}
		}
		return knowledgeDao.createKnowledge(knowledge);
	}

	@Transactional
	public Knowledge modifyKnowledge(Knowledge knowledge) {
		String link = knowledge.getLink(); 
		if(link != null && link.indexOf("<img src=") >= 0) {
			// 表示当前为富文件编辑器添加的，对link进行处理
			int imgIndex = link.indexOf("<img src=");
			int titleIndex = link.indexOf("title=");
			
			if(imgIndex > 0 && titleIndex > 0){
				link = link.substring(imgIndex + 10, titleIndex - 2);
				link = link.substring(link.indexOf("buddha") + 6);
				knowledge.setLink(link);
			}
		}
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
	
	public List<Knowledge> list() {
		return knowledgeDao.listKnowledge();
	}

}
