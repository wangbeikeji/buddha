package com.wangbei.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.DynamicQuerySqlDao;
import com.wangbei.dao.UserLikeDao;
import com.wangbei.dao.impl.jpa.UserLikeRepository;
import com.wangbei.entity.UserLike;
import com.wangbei.pojo.MethodDesc;
import com.wangbei.pojo.UserLikeInfo;
import com.wangbei.util.enums.UserLikeTypeEnum;

/**
 * 用户点赞 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class UserLikeDaoImpl implements UserLikeDao {

	@Autowired
	private UserLikeRepository userLikeRepository;

	@Autowired
	private DynamicQuerySqlDao sqlDao;

	@Override
	public UserLike createUserLike(UserLike userLike) {
		return userLikeRepository.save(userLike);
	}

	@Override
	public void deleteUserLikeById(Integer id) {
		userLikeRepository.delete(id);
	}

	@Override
	public UserLike updateUserLike(UserLike userLike) {
		return userLikeRepository.save(userLike);
	}

	@Override
	public UserLike retrieveUserLikeById(Integer id) {
		return userLikeRepository.findById(id);
	}

	@Override
	public Page<UserLike> pageUserLike(int page, int limit) {
		return userLikeRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<UserLike> listUserLike() {
		return userLikeRepository.findAll();
	}

	@Override
	public List<UserLike> listByUserIdAndTypeAndResourceId(int userId, UserLikeTypeEnum type, Integer resourceId) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "likeTime"));
		return userLikeRepository.findByUserIdAndTypeAndResourceId(userId, type, resourceId, sort);
	}

	@Override
	public Integer getCountByTypeAndResourceId(UserLikeTypeEnum type, Integer resourceId) {
		return userLikeRepository.getCountByTypeAndResourceId(type.getIndex(), resourceId);
	}

	@Override
	public List<UserLikeInfo> userLikeInfoListByTypeAndResourceId(UserLikeTypeEnum type, Integer resourceId, int page,
			int size) {
		String sql = String.format(
				"select t2.user_id, t3.phone, t3.head_portrait_link, t2.like_time from (select * from (select * from user_like t where 1=1 %s %s order by t.like_time desc) t1 GROUP BY t1.user_id) t2 LEFT JOIN user t3 on t2.user_id = t3.id order by t2.like_time desc %s",
				" and t.resource_id=" + resourceId + " ", " and t.type=" + type.getIndex() + " ",
				" limit " + (page * size) + "," + size);

		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setHeadPortraitLink", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setLikeTime", new Class<?>[] { Date.class }));
		return sqlDao.execute(UserLikeInfo.class, sql, setMethodMap);
	}

	@Override
	public List<UserLike> listByUserIdAndTypeAndResourceIdAndLikeTimeGreaterThanEqualAndLikeTimeLessThan(int userId,
			UserLikeTypeEnum type, Integer resourceId, Date startTime, Date endTime) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "likeTime"));
		return userLikeRepository.findByUserIdAndTypeAndResourceIdAndLikeTimeGreaterThanEqualAndLikeTimeLessThan(userId,
				type, resourceId, startTime, endTime, sort);
	}

	@Override
	public List<Integer> listResourceIdByUserIdAndTypeAndLikeTimeGreaterThanEqualAndLikeTimeLessThan(Integer userId,
			UserLikeTypeEnum type, Date startTime, Date endTime) {
		return userLikeRepository.listResourceIdByUserIdAndTypeAndLikeTimeGreaterThanEqualAndLikeTimeLessThan(userId,
				type, startTime, endTime);
	}

	@Override
	public List<Integer> findResourceIdsByUserIdAndType(Integer userId, UserLikeTypeEnum type) {
		return userLikeRepository.findResourceIdsByUserIdAndType(userId, type);
	}

}
