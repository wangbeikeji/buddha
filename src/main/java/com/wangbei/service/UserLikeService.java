package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.UserLikeDao;
import com.wangbei.entity.UserLike;

/**
 * 用户点赞 Service
 * 
 * @author luomengan
 *
 */
@Service
public class UserLikeService {

	@Autowired
	private UserLikeDao userLikeDao;

	public UserLike getUserLikeInfo(Integer id) {
		return userLikeDao.retrieveUserLikeById(id);
	}

	@Transactional
	public UserLike addUserLike(UserLike userLike) {
		return userLikeDao.createUserLike(userLike);
	}

	@Transactional
	public UserLike modifyUserLike(UserLike userLike) {
		return userLikeDao.updateUserLike(userLike);
	}

	@Transactional
	public void deleteUserLike(Integer id) {
		userLikeDao.deleteUserLikeById(id);
	}
	
	@Transactional
	public void deleteUserLikes(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					userLikeDao.deleteUserLikeById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<UserLike> userLikes(int page, int limit) {
		return userLikeDao.pageUserLike(page, limit);
	}
	
	public List<UserLike> list() {
		return userLikeDao.listUserLike();
	}

}
