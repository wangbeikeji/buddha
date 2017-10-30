package com.wangbei.dao.impl.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.UserLike;
import com.wangbei.util.enums.UserLikeTypeEnum;

/**
 * 用户点赞 Repository
 * 
 * @author luomengan
 *
 */
public interface UserLikeRepository extends Repository<UserLike, Integer> {

	UserLike save(UserLike userLike);

	void delete(Integer id);

	Page<UserLike> findAll(Pageable pageable);

	List<UserLike> findAll();

	UserLike findById(Integer id);

	List<UserLike> findByUserIdAndTypeAndResourceId(int userId, UserLikeTypeEnum type, Integer resourceId, Sort sort);
	
	@Query("select resourceId from UserLike where userId=?1 and type=?2")
	List<Integer> findResourceIdsByUserIdAndType(Integer userId, UserLikeTypeEnum type);

	@Query(value = "select count(*) from (select id from user_like t where t.type=?1 and t.resource_id=?2 group by t.user_id) t1", nativeQuery = true)
	Integer getCountByTypeAndResourceId(Integer type, Integer resourceId);

	List<UserLike> findByUserIdAndTypeAndResourceIdAndLikeTimeGreaterThanEqualAndLikeTimeLessThan(int userId,
			UserLikeTypeEnum type, Integer resourceId, Date startTime, Date endTime, Sort sort);

	@Query("select resourceId from UserLike where userId=?1 and type=?2 and likeTime>=?3 and likeTime<?4")
	List<Integer> listResourceIdByUserIdAndTypeAndLikeTimeGreaterThanEqualAndLikeTimeLessThan(Integer userId,
			UserLikeTypeEnum type, Date startTime, Date endTime);

}
