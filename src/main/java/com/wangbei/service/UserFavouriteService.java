package com.wangbei.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.UserFavouriteDao;
import com.wangbei.entity.UserFavourite;
import com.wangbei.pojo.UserFavouriteGroupInfo;
import com.wangbei.util.enums.FavouriteTypeEnum;

/**
 * 用户收藏 Service
 * 
 * @author luomengan
 *
 */
@Service
public class UserFavouriteService {

	@Autowired
	private UserFavouriteDao userFavouriteDao;

	public UserFavourite getUserFavouriteInfo(Integer id) {
		return userFavouriteDao.retrieveUserFavouriteById(id);
	}

	@Transactional
	public UserFavourite addUserFavourite(UserFavourite userFavourite) {
		userFavourite.setFavouriteTime(new Date());
		return userFavouriteDao.createUserFavourite(userFavourite);
	}

	@Transactional
	public UserFavourite addUserFavourite(Integer userId, FavouriteTypeEnum type, int resourceId) {
		List<UserFavourite> list = userFavouriteDao.getUserFavouritesByResourceId(userId, type, resourceId);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		UserFavourite userFavourite = new UserFavourite();
		userFavourite.setFavouriteTime(new Date());
		userFavourite.setResourceId(resourceId);
		userFavourite.setType(type);
		userFavourite.setUserId(userId);
		return userFavouriteDao.createUserFavourite(userFavourite);
	}

	@Transactional
	public UserFavourite modifyUserFavourite(UserFavourite userFavourite) {
		return userFavouriteDao.updateUserFavourite(userFavourite);
	}

	@Transactional
	public void deleteUserFavourite(Integer id) {
		userFavouriteDao.deleteUserFavouriteById(id);
	}

	public Page<UserFavourite> userFavourites(int page, int limit) {
		return userFavouriteDao.pageUserFavourite(page, limit);
	}

	public List<UserFavourite> list() {
		return userFavouriteDao.listUserFavourite();
	}

	@Transactional
	public void cancelByResourceId(Integer userId, FavouriteTypeEnum type, Integer resourceId) {
		userFavouriteDao.deleteUserFavouriteByResourceId(userId, type, resourceId);
	}

	public List<UserFavouriteGroupInfo> getAllUserFavouritesLimit(int userId, int limit) {
		List<UserFavouriteGroupInfo> result = new ArrayList<>();
		FavouriteTypeEnum[] typeArr = FavouriteTypeEnum.values();
		for (FavouriteTypeEnum type : typeArr) {
			if (type == FavouriteTypeEnum.BUDDHISTMUSIC || type == FavouriteTypeEnum.OTHER) {
				continue;
			}
			List<UserFavourite> listData = userFavouriteDao.listUserFavouriteInfoByType(userId, type, 0, limit);
			if (listData != null && listData.size() > 0) {
				UserFavouriteGroupInfo groupInfo = new UserFavouriteGroupInfo();
				groupInfo.setTabName(type.getType());
				groupInfo.setList(listData);
				result.add(groupInfo);
			}
		}
		return result;
	}

	public List<UserFavourite> getUserFavourites(Integer userId) {
		return userFavouriteDao.getUserFavourites(userId);
	}

	public Page<UserFavourite> getUserFavouritesByType(Integer userId, FavouriteTypeEnum type, int page, int limit) {
		List<UserFavourite> listData = userFavouriteDao.listUserFavouriteInfoByType(userId, type, page, limit);
		Page<UserFavourite> result = new PageImpl<>(listData, new PageRequest(page, limit), 0);
		return result;
	}

}
