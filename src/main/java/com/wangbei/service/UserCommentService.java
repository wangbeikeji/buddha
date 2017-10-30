package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.UserCommentDao;
import com.wangbei.entity.UserComment;

/**
 * 用户评论 Service
 * 
 * @author luomengan
 *
 */
@Service
public class UserCommentService {

	@Autowired
	private UserCommentDao userCommentDao;

	public UserComment getUserCommentInfo(Integer id) {
		return userCommentDao.retrieveUserCommentById(id);
	}

	@Transactional
	public UserComment addUserComment(UserComment userComment) {
		return userCommentDao.createUserComment(userComment);
	}

	@Transactional
	public UserComment modifyUserComment(UserComment userComment) {
		return userCommentDao.updateUserComment(userComment);
	}

	@Transactional
	public void deleteUserComment(Integer id) {
		userCommentDao.deleteUserCommentById(id);
	}
	
	@Transactional
	public void deleteUserComments(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					userCommentDao.deleteUserCommentById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<UserComment> userComments(int page, int limit) {
		return userCommentDao.pageUserComment(page, limit);
	}
	
	public List<UserComment> list() {
		return userCommentDao.listUserComment();
	}

}
