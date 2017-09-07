package com.wangbei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.UserMessageDao;
import com.wangbei.dao.impl.jpa.UserMessageRepository;
import com.wangbei.entity.UserMessage;

/**
 * 用户消息 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class UserMessageDaoImpl implements UserMessageDao {

	@Autowired
	private UserMessageRepository userMessageRepository;

	@Override
	public UserMessage createUserMessage(UserMessage userMessage) {
		return userMessageRepository.save(userMessage);
	}

	@Override
	public void deleteUserMessageById(Integer id) {
		userMessageRepository.delete(id);
	}

	@Override
	public UserMessage updateUserMessage(UserMessage userMessage) {
		return userMessageRepository.save(userMessage);
	}

	@Override
	public UserMessage retrieveUserMessageById(Integer id) {
		return userMessageRepository.findById(id);
	}

	@Override
	public Page<UserMessage> pageUserMessage(int page, int limit) {
		return userMessageRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<UserMessage> listUserMessage() {
		return userMessageRepository.findAll();
	}

	@Override
	public Page<UserMessage> pageUserMessageByUserId(Integer userId, int page, int limit) {
		return userMessageRepository.findByUserId(userId, new PageRequest(page, limit));
	}

	@Override
	public void deleteUserMessageByUserId(Integer userId) {
		userMessageRepository.deleteUserMessageByUserId(userId);
	}

}
