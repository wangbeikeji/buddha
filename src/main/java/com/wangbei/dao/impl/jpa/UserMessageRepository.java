package com.wangbei.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.wangbei.entity.UserMessage;

/**
 * 用户消息 Repository
 * 
 * @author luomengan
 *
 */
public interface UserMessageRepository extends Repository<UserMessage, Integer> {

	UserMessage save(UserMessage userMessage);

	void delete(Integer id);

	Page<UserMessage> findAll(Pageable pageable);

	List<UserMessage> findAll();

	UserMessage findById(Integer id);

	Page<UserMessage> findByUserId(Integer userId, Pageable pageRequest);

	@Modifying
	@Query(value = "delete from user_message where user_id=?1", nativeQuery = true)
	void deleteUserMessageByUserId(Integer userId);

}
