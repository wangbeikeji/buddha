package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.UserBuddhistNoteDao;
import com.wangbei.dao.impl.jpa.UserBuddhistNoteRepository;
import com.wangbei.entity.UserBuddhistNote;
import com.wangbei.util.enums.BuddhistNoteTypeEnum;

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

}
