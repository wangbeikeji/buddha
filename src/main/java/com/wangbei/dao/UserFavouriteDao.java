package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.UserFavourite;
import com.wangbei.util.enums.FavouriteTypeEnum;

/**
 * 用户收藏 Dao
 * 
 * @author luomengan
 *
 */
public interface UserFavouriteDao {

	public UserFavourite createUserFavourite(UserFavourite userFavourite);

	public void deleteUserFavouriteById(Integer id);

	public UserFavourite updateUserFavourite(UserFavourite userFavourite);

	public UserFavourite retrieveUserFavouriteById(Integer id);

	public Page<UserFavourite> pageUserFavourite(int page, int limit);
	
	public List<UserFavourite> listUserFavouriteInfoByType(Integer userId, FavouriteTypeEnum type, int page, int limit);

	public List<UserFavourite> listUserFavourite();
	
	public void deleteUserFavouriteByResourceId(Integer userId, FavouriteTypeEnum type, Integer resourceId);

	public List<UserFavourite> getUserFavourites(Integer userId);
	
	public List<UserFavourite> getUserFavouritesByResourceId(Integer userId, FavouriteTypeEnum type, Integer resourceId);

	public List<UserFavourite> getUserFavouritesByType(Integer userId, FavouriteTypeEnum type);
	
	public List<Integer> getUserFavouriteResourceIdsByType(Integer userId, FavouriteTypeEnum type);

}
