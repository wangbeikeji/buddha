package com.wangbei.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.BuddhistTopicDao;
import com.wangbei.dao.MaterialResourceDao;
import com.wangbei.dao.UserCommentDao;
import com.wangbei.dao.UserDao;
import com.wangbei.dao.UserLikeDao;
import com.wangbei.entity.BuddhistTopic;
import com.wangbei.entity.MaterialResource;
import com.wangbei.entity.User;
import com.wangbei.entity.UserComment;
import com.wangbei.entity.UserLike;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.BuddhistTopicFullInfo;
import com.wangbei.pojo.UserCommentInfo;
import com.wangbei.util.enums.BuddhistTopicStatusEnum;
import com.wangbei.util.enums.UserCommentTypeEnum;
import com.wangbei.util.enums.UserLikeTypeEnum;

/**
 * 修行主题 Service
 * 
 * @author luomengan
 *
 */
@Service
public class BuddhistTopicService {

	@Autowired
	private BuddhistTopicDao buddhistTopicDao;
	@Autowired
	private UserLikeDao userLikeDao;
	@Autowired
	private UserCommentDao userCommentDao;
	@Autowired
	private MaterialResourceDao materialResourceDao;
	@Autowired
	private UserDao userDao;

	public BuddhistTopic getBuddhistTopicInfo(Integer id) {
		return buddhistTopicDao.retrieveBuddhistTopicById(id);
	}

	@Transactional
	public BuddhistTopic addBuddhistTopic(BuddhistTopic buddhistTopic) {
		Integer materialResourceId = buddhistTopic.getMaterialResourceId();
		if (materialResourceId != null) {
			MaterialResource resource = materialResourceDao.retrieveMaterialResourceById(materialResourceId);
			if (resource != null) {
				buddhistTopic.setLink(resource.getLink());
				buddhistTopic.setSmallLink(resource.getSmallLink());
			}
		}
		return buddhistTopicDao.createBuddhistTopic(buddhistTopic);
	}

	@Transactional
	public BuddhistTopic modifyBuddhistTopic(BuddhistTopic buddhistTopic) {
		Integer materialResourceId = buddhistTopic.getMaterialResourceId();
		if (materialResourceId != null) {
			MaterialResource resource = materialResourceDao.retrieveMaterialResourceById(materialResourceId);
			if (resource != null) {
				buddhistTopic.setLink(resource.getLink());
				buddhistTopic.setSmallLink(resource.getSmallLink());
			}
		}
		return buddhistTopicDao.updateBuddhistTopic(buddhistTopic);
	}

	@Transactional
	public void deleteBuddhistTopic(Integer id) {
		buddhistTopicDao.deleteBuddhistTopicById(id);
	}

	@Transactional
	public void deleteBuddhistTopics(String ids) {
		if (ids != null) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				if (!"".equals(id.trim())) {
					buddhistTopicDao.deleteBuddhistTopicById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<BuddhistTopicFullInfo> buddhistTopicsByIsEnable(boolean isEnable, int page, int limit) {
		List<BuddhistTopicFullInfo> list = buddhistTopicDao.listBuddhistTopicByIsEnable(isEnable, page, limit);

		if (list != null && list.size() > 0) {
			for (BuddhistTopicFullInfo topic : list) {
				Date endTime = topic.getEndTime();
				Date now = new Date();
				// 判断活动是否已结束
				if (now.getTime() >= endTime.getTime()
						&& topic.getStatus() != BuddhistTopicStatusEnum.IsOver.getIndex()) {
					BuddhistTopic dbTopic = buddhistTopicDao.retrieveBuddhistTopicById(topic.getId());
					dbTopic.setStatus(BuddhistTopicStatusEnum.IsOver);
					buddhistTopicDao.updateBuddhistTopic(dbTopic);

					topic.setStatus(BuddhistTopicStatusEnum.IsOver.getIndex());
				}
			}
		}

		return new PageImpl<>(list, new PageRequest(page, limit), 0);
	}

	public List<BuddhistTopic> list() {
		return buddhistTopicDao.listBuddhistTopic();
	}

	public Integer userLike(int userId, Integer resourceId) {
		BuddhistTopic topic = buddhistTopicDao.retrieveBuddhistTopicById(resourceId);
		if (topic == null) {
			throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION, new Object[] { "修行主题id无效!" });
		}
		if (topic.getStatus() == BuddhistTopicStatusEnum.IsOver) {
			throw new ServiceException(ExceptionEnum.BUDDHISTTOPIC_ISOVER_EXCEPTION);
		}

		List<UserLike> list = userLikeDao.listByUserIdAndTypeAndResourceId(userId, UserLikeTypeEnum.BuddhistTopicLike,
				resourceId);
		if (list != null && list.size() > 0) {
			UserLike userLike = list.get(0);
			userLike.setLikeTime(new Date());
			userLikeDao.updateUserLike(userLike);
			for (int i = list.size() - 1; i >= 1; i--) {
				userLikeDao.deleteUserLikeById(list.get(i).getId());
			}
		} else {
			UserLike userLike = new UserLike();
			userLike.setLikeTime(new Date());
			userLike.setResourceId(resourceId);
			userLike.setType(UserLikeTypeEnum.BuddhistTopicLike);
			userLike.setUserId(userId);
			userLikeDao.createUserLike(userLike);
		}

		return userLikeDao.getCountByTypeAndResourceId(UserLikeTypeEnum.BuddhistTopicLike, resourceId);
	}

	public Integer getCountLike(Integer resourceId) {
		return userLikeDao.getCountByTypeAndResourceId(UserLikeTypeEnum.BuddhistTopicLike, resourceId);
	}

	public BuddhistTopicFullInfo getBuddhistTopicFullInfo(Integer userId, Integer id, int page, int limit) {
		BuddhistTopic topic = buddhistTopicDao.retrieveBuddhistTopicById(id);
		if (topic == null) {
			throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION, new Object[] { "修行主题id无效!" });
		}

		BuddhistTopicFullInfo result = new BuddhistTopicFullInfo();
		result.setEndTime(topic.getEndTime());
		result.setId(topic.getId());
		result.setIsEnable(topic.getIsEnable());
		result.setLink(topic.getLink());
		result.setSmallLink(topic.getSmallLink());
		result.setStartTime(topic.getStartTime());
		result.setStatus(topic.getStatus().getIndex());
		result.setTitle(topic.getTitle());
		result.setTopicDetail(topic.getTopicDetail());
		result.setTopicTime(topic.getTopicTime());
		result.setUserLikeCount(getCountLike(id));
		result.setUserLikeInfoList(
				userLikeDao.userLikeInfoListByTypeAndResourceId(UserLikeTypeEnum.BuddhistTopicLike, id, page, limit));
		result.setUserCommentInfoList(userCommentDao.userCommentInfoListByTypeAndResourceId(userId,
				UserCommentTypeEnum.BuddhistTopicComment, UserLikeTypeEnum.BuddhistNoteCommentLike, id, page, limit));
		List<UserLike> currentLike = userLikeDao.listByUserIdAndTypeAndResourceId(userId,
				UserLikeTypeEnum.BuddhistTopicLike, id);
		if (currentLike != null && currentLike.size() > 0) {
			result.setCurrentUserIsLike(true);
		}
		result.setNumber(page);
		return result;
	}

	public Integer userLikeComment(Integer userId, Integer commentId) {
		UserComment comment = userCommentDao.retrieveUserCommentById(commentId);
		if (comment == null) {
			throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION, new Object[] { "修行主题评论id无效!" });
		}

		List<UserLike> list = userLikeDao.listByUserIdAndTypeAndResourceId(userId,
				UserLikeTypeEnum.BuddhistNoteCommentLike, commentId);
		if (list != null && list.size() > 0) {
			for (int i = list.size() - 1; i >= 1; i--) {
				userLikeDao.deleteUserLikeById(list.get(i).getId());
			}
			throw new ServiceException(ExceptionEnum.ALREADY_LIKE_EXCEPTION);
		} else {
			UserLike userLike = new UserLike();
			userLike.setLikeTime(new Date());
			userLike.setResourceId(commentId);
			userLike.setType(UserLikeTypeEnum.BuddhistNoteCommentLike);
			userLike.setUserId(userId);
			userLikeDao.createUserLike(userLike);
		}

		return userLikeDao.getCountByTypeAndResourceId(UserLikeTypeEnum.BuddhistNoteCommentLike, commentId);
	}

	public UserCommentInfo userComment(Integer userId, Integer resourceId, String comment) {
		BuddhistTopic topic = buddhistTopicDao.retrieveBuddhistTopicById(resourceId);
		if (topic == null) {
			throw new ServiceException(ExceptionEnum.UNKNOW_EXCEPTION, new Object[] { "修行主题id无效!" });
		}
		if (topic.getStatus() == BuddhistTopicStatusEnum.IsOver) {
			throw new ServiceException(ExceptionEnum.BUDDHISTTOPIC_ISOVER_EXCEPTION);
		}
		UserComment userComment = new UserComment();
		userComment.setComment(comment);
		userComment.setCommentTime(new Date());
		userComment.setResourceId(resourceId);
		userComment.setType(UserCommentTypeEnum.BuddhistTopicComment);
		userComment.setUserId(userId);
		userCommentDao.createUserComment(userComment);

		User user = userDao.retrieveUserById(userId);

		UserCommentInfo result = new UserCommentInfo();
		result.setComment(comment);
		result.setCommentTime(userComment.getCommentTime());
		result.setCurrentUserIsLike(false);
		result.setHeadPortraitLink(user.getHeadPortraitLink());
		result.setId(userComment.getId());
		result.setName(user.getName());
		result.setPhone(user.getPhone());
		result.setUserId(userId);
		result.setUserLikeCount(0);
		return result;
	}

}
