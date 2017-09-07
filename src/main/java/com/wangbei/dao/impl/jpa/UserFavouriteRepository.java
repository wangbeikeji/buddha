package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.UserFavourite;
import com.wangbei.util.enums.FavouriteTypeEnum;

/**
 * 用户收藏 Repository
 * 
 * @author luomengan
 *
 */
public interface UserFavouriteRepository
		extends CurdJpaRepository<UserFavourite, Integer>, Repository<UserFavourite, Integer> {

	@Modifying
	@Query("delete from UserFavourite where userId=?1 and type=?2 and resourceId=?3")
	void deleteUserFavouriteByResourceId(Integer userId, FavouriteTypeEnum type, Integer resourceId);

	List<UserFavourite> findByUserId(Integer userId, Sort sort);

	List<UserFavourite> findByUserIdAndType(Integer userId, FavouriteTypeEnum type, Sort sort);

	List<UserFavourite> findByUserIdAndTypeAndResourceId(Integer userId, FavouriteTypeEnum type, Integer resourceId,
			Sort sort);

	@Query("select resourceId from UserFavourite where userId=?1 and type=?2")
	List<Integer> findResourceIdsByUserIdAndType(Integer userId, FavouriteTypeEnum type);

	@Query(value = "select t1.id, t1.type, t1.favourite_time, t1.resource_id, t1.user_id, t2.link as link, t2.title as title from user_favourite t1 LEFT JOIN knowledge t2 on t1.resource_id=t2.id where t1.user_id=?1 and t1.type=?2 and t2.type=?3 limit ?4, ?5", nativeQuery = true)
	List<Object[]> findKnowledgeByUserId(int userId, int favouriteType, int knowledgeType, int offset, int size);

	@Query(value = "select t1.id, t1.type, t1.favourite_time, t1.resource_id, t1.user_id, t2.link, t2.title from user_favourite t1 LEFT JOIN sutra t2 on t1.resource_id=t2.id where t1.user_id=?1 and t1.type=5 limit ?2, ?3", nativeQuery = true)
	List<Object[]> findSutraByUserId(int userId, int offset, int size);

	@Query(value = "select t1.id, t1.type, t1.favourite_time, t1.resource_id, t1.user_id, t2.link, t2.name as title from user_favourite t1 LEFT JOIN buddhist_music t2 on t1.resource_id=t2.id where t1.user_id=?1 and t1.type=6 limit ?2, ?3", nativeQuery = true)
	List<Object[]> findBuddhistMusicByUserId(int userId, int offset, int size);

}
