package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.UserComment;

/**
 * 用户评论 Repository
 * 
 * @author luomengan
 *
 */
public interface UserCommentRepository extends Repository<UserComment, Integer> {

	UserComment save(UserComment userComment);

	void delete(Integer id);

	Page<UserComment> findAll(Pageable pageable);

	List<UserComment> findAll();

	UserComment findById(Integer id);

}
