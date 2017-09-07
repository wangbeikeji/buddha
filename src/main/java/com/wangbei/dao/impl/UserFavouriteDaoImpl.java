package com.wangbei.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.UserFavouriteDao;
import com.wangbei.dao.impl.jpa.UserFavouriteRepository;
import com.wangbei.entity.UserFavourite;
import com.wangbei.util.enums.FavouriteTypeEnum;
import com.wangbei.util.enums.KnowledgeTypeEnum;

/**
 * 用户收藏 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class UserFavouriteDaoImpl implements UserFavouriteDao {

	@Autowired
	private UserFavouriteRepository userFavouriteRepository;

	@Override
	public UserFavourite createUserFavourite(UserFavourite userFavourite) {
		return userFavouriteRepository.save(userFavourite);
	}

	@Override
	public void deleteUserFavouriteById(Integer id) {
		userFavouriteRepository.delete(id);
	}

	@Override
	public UserFavourite updateUserFavourite(UserFavourite userFavourite) {
		return userFavouriteRepository.save(userFavourite);
	}

	@Override
	public UserFavourite retrieveUserFavouriteById(Integer id) {
		return userFavouriteRepository.findById(id);
	}

	@Override
	public Page<UserFavourite> pageUserFavourite(int page, int limit) {
		return userFavouriteRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<UserFavourite> listUserFavouriteInfoByType(Integer userId, FavouriteTypeEnum type, int page,
			int limit) {
		List<UserFavourite> result = new ArrayList<>();
		if (type == FavouriteTypeEnum.INFORMATION) {
			List<Object[]> objArrList = userFavouriteRepository.findKnowledgeByUserId(userId, type.getIndex(),
					KnowledgeTypeEnum.INFORMATION.getIndex(), page * limit, limit);
			toUserFavouriteList(result, objArrList);
		} else if (type == FavouriteTypeEnum.STORY) {
			List<Object[]> objArrList = userFavouriteRepository.findKnowledgeByUserId(userId, type.getIndex(),
					KnowledgeTypeEnum.STORY.getIndex(), page * limit, limit);
			toUserFavouriteList(result, objArrList);
		} else if (type == FavouriteTypeEnum.BEGINNER) {
			List<Object[]> objArrList = userFavouriteRepository.findKnowledgeByUserId(userId, type.getIndex(),
					KnowledgeTypeEnum.BEGINNER.getIndex(), page * limit, limit);
			toUserFavouriteList(result, objArrList);
		} else if (type == FavouriteTypeEnum.HEALTH) {
			List<Object[]> objArrList = userFavouriteRepository.findKnowledgeByUserId(userId, type.getIndex(),
					KnowledgeTypeEnum.HEALTH.getIndex(), page * limit, limit);
			toUserFavouriteList(result, objArrList);
		} else if (type == FavouriteTypeEnum.SUTRA) {
			List<Object[]> objArrList = userFavouriteRepository.findSutraByUserId(userId, page * limit, limit);
			toUserFavouriteList(result, objArrList);
		} else if (type == FavouriteTypeEnum.BUDDHISTMUSIC) {
			List<Object[]> objArrList = userFavouriteRepository.findBuddhistMusicByUserId(userId, page * limit, limit);
			toUserFavouriteList(result, objArrList);
		}
		return result;
	}

	private void toUserFavouriteList(List<UserFavourite> list, List<Object[]> objArrList) {
		if (objArrList != null && objArrList.size() > 0) {
			for (Object[] objArr : objArrList) {
				list.add(objArrToUserFavourite(objArr));
			}
		}
	}

	private UserFavourite objArrToUserFavourite(Object[] objArr) {
		if (objArr != null) {
			Integer id = (Integer) objArr[0];
			FavouriteTypeEnum type = FavouriteTypeEnum.getByIndex((Integer) objArr[1]);
			Date favouriteTime = new Date(((Timestamp) objArr[2]).getTime());
			Integer resourceId = (Integer) objArr[3];
			Integer userId = (Integer) objArr[4];
			String fullLink = (String) objArr[5];
			String title = (String) objArr[6];
			return new UserFavourite(id, type, favouriteTime, resourceId, userId, fullLink, title);
		}
		return null;
	}

	@Override
	public List<UserFavourite> listUserFavourite() {
		return userFavouriteRepository.findAll();
	}

	@Override
	public void deleteUserFavouriteByResourceId(Integer userId, FavouriteTypeEnum type, Integer resourceId) {
		userFavouriteRepository.deleteUserFavouriteByResourceId(userId, type, resourceId);
	}

	@Override
	public List<UserFavourite> getUserFavourites(Integer userId) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "favouriteTime"));
		return userFavouriteRepository.findByUserId(userId, sort);
	}

	@Override
	public List<UserFavourite> getUserFavouritesByType(Integer userId, FavouriteTypeEnum type) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "favouriteTime"));
		return userFavouriteRepository.findByUserIdAndType(userId, type, sort);
	}

	@Override
	public List<UserFavourite> getUserFavouritesByResourceId(Integer userId, FavouriteTypeEnum type,
			Integer resourceId) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "favouriteTime"));
		return userFavouriteRepository.findByUserIdAndTypeAndResourceId(userId, type, resourceId, sort);
	}

	@Override
	public List<Integer> getUserFavouriteResourceIdsByType(Integer userId, FavouriteTypeEnum type) {
		return userFavouriteRepository.findResourceIdsByUserIdAndType(userId, type);
	}

}
