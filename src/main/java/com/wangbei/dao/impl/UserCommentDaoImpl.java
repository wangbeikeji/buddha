package com.wangbei.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.DynamicQuerySqlDao;
import com.wangbei.dao.UserCommentDao;
import com.wangbei.dao.impl.jpa.UserCommentRepository;
import com.wangbei.dao.impl.jpa.UserLikeRepository;
import com.wangbei.entity.UserComment;
import com.wangbei.pojo.MethodDesc;
import com.wangbei.pojo.UserCommentInfo;
import com.wangbei.util.enums.UserCommentTypeEnum;
import com.wangbei.util.enums.UserLikeTypeEnum;

/**
 * 用户评论 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class UserCommentDaoImpl implements UserCommentDao {

	@Autowired
	private UserCommentRepository userCommentRepository;

	@Autowired
	private UserLikeRepository userLikeRepository;

	@Autowired
	private DynamicQuerySqlDao sqlDao;

	@Override
	public UserComment createUserComment(UserComment userComment) {
		return userCommentRepository.save(userComment);
	}

	@Override
	public void deleteUserCommentById(Integer id) {
		userCommentRepository.delete(id);
	}

	@Override
	public UserComment updateUserComment(UserComment userComment) {
		return userCommentRepository.save(userComment);
	}

	@Override
	public UserComment retrieveUserCommentById(Integer id) {
		return userCommentRepository.findById(id);
	}

	@Override
	public Page<UserComment> pageUserComment(int page, int limit) {
		return userCommentRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<UserComment> listUserComment() {
		return userCommentRepository.findAll();
	}

	@Override
	public List<UserCommentInfo> userCommentInfoListByTypeAndResourceId(Integer currentUserId,
			UserCommentTypeEnum commentType, UserLikeTypeEnum likeType, Integer resourceId, int page, int limit) {
		String sql = String.format(
				"select t3.*, t4.name, t4.phone, t4.head_portrait_link from "
						+ "(select t1.id, t1.comment, t1.comment_time, t1.resource_id, t1.type, t1.user_id, count(t2.user_id) as user_like_count from "
						+ "user_comment t1 LEFT JOIN user_like t2 on t1.id=t2.resource_id and t2.type=%s where t1.type=%s and t1.resource_id=%s GROUP BY t1.id ORDER BY t1.comment_time desc) t3 "
						+ "LEFT JOIN user t4 on t4.id=t3.user_id limit %s, %s",
				likeType.getIndex(), commentType.getIndex(), resourceId, (page * limit), limit);

		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setId", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setComment", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setCommentTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setUserLikeCount", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(8), new MethodDesc("setPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(9), new MethodDesc("setHeadPortraitLink", new Class<?>[] { String.class }));

		List<UserCommentInfo> result = sqlDao.execute(UserCommentInfo.class, sql, setMethodMap);
		if (result != null && result.size() > 0) {
			List<Integer> resourceIds = userLikeRepository.findResourceIdsByUserIdAndType(currentUserId,
					UserLikeTypeEnum.BuddhistNoteCommentLike);
			for (UserCommentInfo info : result) {
				if (resourceIds.contains(info.getId())) {
					info.setCurrentUserIsLike(true);
				}
			}
		}
		return result;
	}

}
