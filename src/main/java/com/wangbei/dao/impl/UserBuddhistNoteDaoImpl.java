package com.wangbei.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.DynamicQuerySqlDao;
import com.wangbei.dao.UserBuddhistNoteDao;
import com.wangbei.dao.UserLikeDao;
import com.wangbei.dao.impl.jpa.UserBuddhistNoteRepository;
import com.wangbei.entity.UserBuddhistNote;
import com.wangbei.pojo.MethodDesc;
import com.wangbei.pojo.UserBuddhistNoteInfo;
import com.wangbei.util.enums.BuddhistNoteTypeEnum;
import com.wangbei.util.enums.UserLikeTypeEnum;

/**
 * 用户佛录 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class UserBuddhistNoteDaoImpl implements UserBuddhistNoteDao {

	@Autowired
	private UserBuddhistNoteRepository userBuddhistNoteRepository;

	@Autowired
	private DynamicQuerySqlDao sqlDao;

	@Autowired
	private UserLikeDao userLikeDao;

	@Override
	public UserBuddhistNote createUserBuddhistNote(UserBuddhistNote userBuddhistNote) {
		return userBuddhistNoteRepository.save(userBuddhistNote);
	}

	@Override
	public void deleteUserBuddhistNoteById(Integer id) {
		userBuddhistNoteRepository.delete(id);
	}

	@Override
	public UserBuddhistNote updateUserBuddhistNote(UserBuddhistNote userBuddhistNote) {
		return userBuddhistNoteRepository.save(userBuddhistNote);
	}

	@Override
	public UserBuddhistNote retrieveUserBuddhistNoteById(Integer id) {
		return userBuddhistNoteRepository.findById(id);
	}

	@Override
	public Page<UserBuddhistNote> pageUserBuddhistNote(int page, int limit) {
		return userBuddhistNoteRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<UserBuddhistNote> listUserBuddhistNote() {
		return userBuddhistNoteRepository.findAll();
	}

	@Override
	public Page<UserBuddhistNote> pagePublicUserBuddhistNoteByType(BuddhistNoteTypeEnum type, int page, int limit) {
		Pageable pageable = new PageRequest(page, limit, new Sort(new Sort.Order(Direction.DESC, "createTime")));
		return userBuddhistNoteRepository.findByTypeAndIsPublic(type, true, pageable);
	}

	@Override
	public Page<UserBuddhistNote> pageUserBuddhistNoteByType(Integer userId, BuddhistNoteTypeEnum type, int page,
			int limit) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "createTime"));
		Pageable pagable = new PageRequest(page, limit, sort);
		return userBuddhistNoteRepository.findByUserIdAndType(userId, type, pagable);
	}

	@Override
	public Page<UserBuddhistNoteInfo> sqlPageUserBuddhistNoteByType(Integer userId, BuddhistNoteTypeEnum type, int page,
			int limit) {
		String sql = String.format(
				"select t1.id, t1.buddha, t1.buddha_link, t1.create_time, t1.is_public, t1.note, t1.phone, t1.type, t1.user_id, count(t2.user_id) as userLikeCount from user_buddhist_note t1 LEFT JOIN user_like t2 on t1.id=t2.resource_id and t2.type=5 where t1.user_id=%s and t1.type=%s group by t1.id order by t1.create_time desc limit %s, %s;",
				userId, type.getIndex(), page * limit, limit);
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setId", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setBuddha", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setBuddhaLink", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setCreateTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setIsPublic", new Class<?>[] { Boolean.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setNote", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setTypeByIndex", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(8), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(9), new MethodDesc("setUserLikeCount", new Class<?>[] { Integer.class }));
		List<UserBuddhistNoteInfo> list = sqlDao.execute(UserBuddhistNoteInfo.class, sql, setMethodMap);
		if (list != null && list.size() > 0) {
			List<Integer> resourceIds = userLikeDao.findResourceIdsByUserIdAndType(userId,
					UserLikeTypeEnum.BuddhistNoteLike);
			if (resourceIds != null && resourceIds.size() > 0) {
				for (UserBuddhistNoteInfo note : list) {
					if (resourceIds.contains(note.getId())) {
						note.setCurrentUserIsLike(true);
					}
				}
			}
		}
		return new PageImpl<>(list, new PageRequest(page, limit), 0);
	}

	@Override
	public Page<UserBuddhistNoteInfo> sqlPagePublicUserBuddhistNoteByType(Integer userId, BuddhistNoteTypeEnum type, int page,
			int limit) {
		String sql = String.format(
				"select t1.id, t1.buddha, t1.buddha_link, t1.create_time, t1.is_public, t1.note, t1.phone, t1.type, t1.user_id, count(t2.user_id) as userLikeCount from user_buddhist_note t1 LEFT JOIN user_like t2 on t1.id=t2.resource_id and t2.type=5 where t1.type=%s group by t1.id order by t1.create_time desc limit %s, %s;",
				type.getIndex(), page * limit, limit);
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setId", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setBuddha", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setBuddhaLink", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setCreateTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setIsPublic", new Class<?>[] { Boolean.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setNote", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setTypeByIndex", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(8), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(9), new MethodDesc("setUserLikeCount", new Class<?>[] { Integer.class }));
		List<UserBuddhistNoteInfo> list = sqlDao.execute(UserBuddhistNoteInfo.class, sql, setMethodMap);
		if (list != null && list.size() > 0) {
			List<Integer> resourceIds = userLikeDao.findResourceIdsByUserIdAndType(userId,
					UserLikeTypeEnum.BuddhistNoteLike);
			if (resourceIds != null && resourceIds.size() > 0) {
				for (UserBuddhistNoteInfo note : list) {
					if (resourceIds.contains(note.getId())) {
						note.setCurrentUserIsLike(true);
					}
				}
			}
		}
		return new PageImpl<>(list, new PageRequest(page, limit), 0);
	}

}
