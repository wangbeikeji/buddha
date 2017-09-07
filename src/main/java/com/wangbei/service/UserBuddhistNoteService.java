package com.wangbei.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.BuddhistNoteJossDao;
import com.wangbei.dao.SensitiveWordDao;
import com.wangbei.dao.UserBuddhistNoteDao;
import com.wangbei.dao.UserDao;
import com.wangbei.entity.BuddhistNoteJoss;
import com.wangbei.entity.SensitiveWord;
import com.wangbei.entity.User;
import com.wangbei.entity.UserBuddhistNote;
import com.wangbei.util.enums.BuddhistNoteTypeEnum;

/**
 * 用户佛录 Service
 * 
 * @author luomengan
 *
 */
@Service
public class UserBuddhistNoteService {

	@Autowired
	private UserBuddhistNoteDao userBuddhistNoteDao;

	@Autowired
	private BuddhistNoteJossDao buddhistNoteJossDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SensitiveWordDao sensitiveWordDao;

	public UserBuddhistNote getUserBuddhistNoteInfo(Integer id) {
		return userBuddhistNoteDao.retrieveUserBuddhistNoteById(id);
	}

	@Transactional
	public UserBuddhistNote addUserBuddhistNote(UserBuddhistNote userBuddhistNote) {
		return userBuddhistNoteDao.createUserBuddhistNote(userBuddhistNote);
	}

	@Transactional
	public UserBuddhistNote addUserBuddhistNote(Integer userId, String buddha, String note, BuddhistNoteTypeEnum type,
			boolean isPublic) {
		UserBuddhistNote userBuddhistNote = new UserBuddhistNote();
		BuddhistNoteJoss joss = buddhistNoteJossDao.retrieveBuddhistNoteJossByBuddhaName(buddha.trim());
		if (joss != null) {
			userBuddhistNote.setBuddhaLink(joss.getLink());
		}
		userBuddhistNote.setBuddha(buddha.trim());
		userBuddhistNote.setCreateTime(new Date());
		userBuddhistNote.setIsPublic(isPublic);
		userBuddhistNote.setNote(note);
		userBuddhistNote.setType(type);
		userBuddhistNote.setUserId(userId);
		User user = userDao.retrieveUserById(userId);
		userBuddhistNote.setPhone(user.getPhone());
		return userBuddhistNoteDao.createUserBuddhistNote(userBuddhistNote);
	}

	@Transactional
	public UserBuddhistNote modifyUserBuddhistNote(UserBuddhistNote userBuddhistNote) {
		return userBuddhistNoteDao.updateUserBuddhistNote(userBuddhistNote);
	}

	@Transactional
	public void deleteUserBuddhistNote(Integer id) {
		userBuddhistNoteDao.deleteUserBuddhistNoteById(id);
	}

	public Page<UserBuddhistNote> userBuddhistNotes(int page, int limit) {
		return userBuddhistNoteDao.pageUserBuddhistNote(page, limit);
	}

	public List<UserBuddhistNote> list() {
		return userBuddhistNoteDao.listUserBuddhistNote();
	}

	public Page<UserBuddhistNote> pageUserBuddhistNoteByType(Integer userId, BuddhistNoteTypeEnum type, int page,
			int limit) {
		return userBuddhistNoteDao.pageUserBuddhistNoteByType(userId, type, page, limit);
	}

	public Page<UserBuddhistNote> pagePublicUserBuddhistNoteByType(BuddhistNoteTypeEnum type, int page, int limit) {
		Page<UserBuddhistNote> result = userBuddhistNoteDao.pagePublicUserBuddhistNoteByType(type, page, limit);
		if (result.getContent() != null && result.getContent().size() > 0) {
			// 关键词过滤
			List<SensitiveWord> wordList = sensitiveWordDao.listSensitiveWordByIsEnable(true);
			Collections.sort(wordList, new SensitiveWordComparator());
			for (UserBuddhistNote note : result.getContent()) {
				note.setNote(filterSensitiveWord(note.getNote(), wordList));
			}
		}
		return result;
	}

	private String filterSensitiveWord(String note, List<SensitiveWord> wordList) {
		for (SensitiveWord word : wordList) {
			note = note.replace(word.getWord(), getSensitiveWordReplaceStr(word.getWord().length()));
		}
		return note;
	}

	private String getSensitiveWordReplaceStr(int length) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append("*");
		}
		return builder.toString();
	}

	private class SensitiveWordComparator implements Comparator<SensitiveWord> {
		@Override
		public int compare(SensitiveWord o1, SensitiveWord o2) {
			int between = o2.getWord().length() - o1.getWord().length();
			if (between == 0) {
				return o1.getWord().compareTo(o2.getWord());
			} else {
				return between;
			}
		}
	}

}
