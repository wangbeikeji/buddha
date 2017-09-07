package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.UserMessage;

/**
 * 用户消息 Dao
 * 
 * @author luomengan
 *
 */
public interface UserMessageDao {

	public UserMessage createUserMessage(UserMessage userMessage);

	public void deleteUserMessageById(Integer id);

	public UserMessage updateUserMessage(UserMessage userMessage);

	public UserMessage retrieveUserMessageById(Integer id);

	public Page<UserMessage> pageUserMessage(int page, int limit);
	
	public List<UserMessage> listUserMessage();

	public Page<UserMessage> pageUserMessageByUserId(Integer userId, int page, int limit);

	public void deleteUserMessageByUserId(Integer userId);

}
