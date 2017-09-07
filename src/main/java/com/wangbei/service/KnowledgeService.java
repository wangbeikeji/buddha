package com.wangbei.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.KnowledgeDao;
import com.wangbei.dao.UserFavouriteDao;
import com.wangbei.entity.Knowledge;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.util.enums.FavouriteTypeEnum;
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

	@Autowired
	private UserFavouriteDao userFavouriteDao;

	public Knowledge getKnowledgeInfo(Integer id) {
		Knowledge result = knowledgeDao.retrieveKnowledgeById(id);
		if (result != null) {
			checkIsFavourite(result);
		}
		return result;
	}

	@Transactional
	public Knowledge addKnowledge(Knowledge knowledge) {
		knowledge.setCreateTime(new Date());
		String link = knowledge.getLink();
		if (link != null && link.indexOf("<img src=") >= 0) {
			// 表示当前为富文件编辑器添加的，对link进行处理
			int imgIndex = link.indexOf("<img src=");
			int titleIndex = link.indexOf("title=");

			if (imgIndex > 0 && titleIndex > 0) {
				link = link.substring(imgIndex + 10, titleIndex - 2);
				knowledge.setLink(link);
				// link = link.substring(link.indexOf("buddha") + 6);
				// knowledge.setLink("http:" + link);
			}
		}
		return knowledgeDao.createKnowledge(knowledge);
	}

	@Transactional
	public Knowledge modifyKnowledge(Knowledge knowledge) {
		String link = knowledge.getLink();
		if (link != null && link.indexOf("<img src=") >= 0) {
			// 表示当前为富文件编辑器添加的，对link进行处理
			int imgIndex = link.indexOf("<img src=");
			int titleIndex = link.indexOf("title=");

			if (imgIndex > 0 && titleIndex > 0) {
				link = link.substring(imgIndex + 10, titleIndex - 2);
				knowledge.setLink(link);
				// link = link.substring(link.indexOf("buddha") + 6);
				// knowledge.setLink("http:" + link);
			}
		}
		return knowledgeDao.updateKnowledge(knowledge);
	}

	@Transactional
	public void deleteKnowledge(Integer id) {
		knowledgeDao.deleteKnowledgeById(id);
	}

	public Page<Knowledge> knowledgesByType(KnowledgeTypeEnum type, int page, int limit) {
		Page<Knowledge> result = knowledgeDao.pageKnowledgeByType(type, page, limit);
		if (result.getContent() != null && result.getContent().size() > 0) {
			checkIsFavourite(result.getContent(), type);
		}
		return result;
	}

	private void checkIsFavourite(Knowledge knowledge) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		int userId = authUserDetails == null ? 0 : authUserDetails.getUserId();
		if (userId > 0) {
			FavouriteTypeEnum favouriteType = FavouriteTypeEnum.INFORMATION;
			if (knowledge.getType() == KnowledgeTypeEnum.INFORMATION) {
				favouriteType = FavouriteTypeEnum.INFORMATION;
			} else if (knowledge.getType() == KnowledgeTypeEnum.BEGINNER) {
				favouriteType = FavouriteTypeEnum.BEGINNER;
			} else if (knowledge.getType() == KnowledgeTypeEnum.STORY) {
				favouriteType = FavouriteTypeEnum.STORY;
			} else if (knowledge.getType() == KnowledgeTypeEnum.HEALTH) {
				favouriteType = FavouriteTypeEnum.HEALTH;
			}
			List<Integer> resourceIdList = userFavouriteDao.getUserFavouriteResourceIdsByType(userId, favouriteType);
			if (resourceIdList != null && resourceIdList.size() > 0) {
				if (resourceIdList.contains(knowledge.getId())) {
					knowledge.setFavourite(true);
				}
			}
		}
	}

	private void checkIsFavourite(List<Knowledge> list, KnowledgeTypeEnum type) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		int userId = authUserDetails == null ? 0 : authUserDetails.getUserId();
		if (userId > 0) {
			FavouriteTypeEnum favouriteType = FavouriteTypeEnum.INFORMATION;
			if (type == KnowledgeTypeEnum.INFORMATION) {
				favouriteType = FavouriteTypeEnum.INFORMATION;
			} else if (type == KnowledgeTypeEnum.BEGINNER) {
				favouriteType = FavouriteTypeEnum.BEGINNER;
			} else if (type == KnowledgeTypeEnum.STORY) {
				favouriteType = FavouriteTypeEnum.STORY;
			} else if (type == KnowledgeTypeEnum.HEALTH) {
				favouriteType = FavouriteTypeEnum.HEALTH;
			}
			List<Integer> resourceIdList = userFavouriteDao.getUserFavouriteResourceIdsByType(userId, favouriteType);
			if (resourceIdList != null && resourceIdList.size() > 0) {
				for (Knowledge knowledge : list) {
					if (resourceIdList.contains(knowledge.getId())) {
						knowledge.setFavourite(true);
					}
				}
			}
		}
	}

	public Page<Knowledge> knowledges(int page, int limit) {
		return knowledgeDao.pageKnowledge(page, limit);
	}

	public List<Knowledge> listByType(KnowledgeTypeEnum type) {
		return knowledgeDao.listKnowledgeByType(type);
	}

	public List<Knowledge> list() {
		return knowledgeDao.listKnowledge();
	}

}
