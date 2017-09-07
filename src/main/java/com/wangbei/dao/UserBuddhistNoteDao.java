package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.UserBuddhistNote;
import com.wangbei.util.enums.BuddhistNoteTypeEnum;

/**
 * 用户佛录 Dao
 * 
 * @author luomengan
 *
 */
public interface UserBuddhistNoteDao {

	public UserBuddhistNote createUserBuddhistNote(UserBuddhistNote userBuddhistNote);

	public void deleteUserBuddhistNoteById(Integer id);

	public UserBuddhistNote updateUserBuddhistNote(UserBuddhistNote userBuddhistNote);

	public UserBuddhistNote retrieveUserBuddhistNoteById(Integer id);

	public Page<UserBuddhistNote> pageUserBuddhistNote(int page, int limit);
	
	public List<UserBuddhistNote> listUserBuddhistNote();

	public Page<UserBuddhistNote> pageUserBuddhistNoteByType(Integer userId, BuddhistNoteTypeEnum type, int page, int limit);

	public Page<UserBuddhistNote> pagePublicUserBuddhistNoteByType(BuddhistNoteTypeEnum type, int page, int limit);

}
