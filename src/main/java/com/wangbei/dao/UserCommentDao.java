package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.UserComment;
import com.wangbei.pojo.UserCommentInfo;
import com.wangbei.util.enums.UserCommentTypeEnum;
import com.wangbei.util.enums.UserLikeTypeEnum;

/**
 * 用户评论 Dao
 * 
 * @author luomengan
 *
 */
public interface UserCommentDao {

	public UserComment createUserComment(UserComment userComment);

	public void deleteUserCommentById(Integer id);

	public UserComment updateUserComment(UserComment userComment);

	public UserComment retrieveUserCommentById(Integer id);

	public Page<UserComment> pageUserComment(int page, int limit);

	public List<UserComment> listUserComment();

	public List<UserCommentInfo> userCommentInfoListByTypeAndResourceId(Integer currentUserId,
			UserCommentTypeEnum commentType, UserLikeTypeEnum likeType, Integer id, int page, int limit);

}
