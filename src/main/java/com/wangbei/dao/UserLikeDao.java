package com.wangbei.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.UserLike;
import com.wangbei.pojo.UserLikeInfo;
import com.wangbei.util.enums.UserLikeTypeEnum;

/**
 * 用户点赞 Dao
 * 
 * @author luomengan
 *
 */
public interface UserLikeDao {

	public UserLike createUserLike(UserLike userLike);

	public void deleteUserLikeById(Integer id);

	public UserLike updateUserLike(UserLike userLike);

	public UserLike retrieveUserLikeById(Integer id);

	public Page<UserLike> pageUserLike(int page, int limit);

	public List<UserLike> listUserLike();
	
	public List<Integer> findResourceIdsByUserIdAndType(Integer userId, UserLikeTypeEnum type);

	public List<UserLike> listByUserIdAndTypeAndResourceId(int userId, UserLikeTypeEnum type, Integer resourceId);

	public Integer getCountByTypeAndResourceId(UserLikeTypeEnum type, Integer resourceId);

	public List<UserLikeInfo> userLikeInfoListByTypeAndResourceId(UserLikeTypeEnum type, Integer resourceId, int page,
			int limit);

	public List<UserLike> listByUserIdAndTypeAndResourceIdAndLikeTimeGreaterThanEqualAndLikeTimeLessThan(int userId,
			UserLikeTypeEnum type, Integer checkinUserId, Date startTime, Date endTime);

	public List<Integer> listResourceIdByUserIdAndTypeAndLikeTimeGreaterThanEqualAndLikeTimeLessThan(Integer userId,
			UserLikeTypeEnum type, Date startTime, Date endTime);

}
