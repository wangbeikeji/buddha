package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.UserMessageDao;
import com.wangbei.entity.UserMessage;

/**
 * 用户消息 Service
 * 
 * @author luomengan
 *
 */
@Service
public class UserMessageService {

	@Autowired
	private UserMessageDao userMessageDao;

	public UserMessage getUserMessageInfo(Integer id) {
		return userMessageDao.retrieveUserMessageById(id);
	}

	@Transactional
	public UserMessage addUserMessage(UserMessage userMessage) {
		return userMessageDao.createUserMessage(userMessage);
	}

	@Transactional
	public UserMessage modifyUserMessage(UserMessage userMessage) {
		return userMessageDao.updateUserMessage(userMessage);
	}

	@Transactional
	public void deleteUserMessage(Integer id) {
		userMessageDao.deleteUserMessageById(id);
	}
	
	@Transactional
	public void deleteUserMessages(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					userMessageDao.deleteUserMessageById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<UserMessage> userMessages(int page, int limit) {
		return userMessageDao.pageUserMessage(page, limit);
	}
	
	public List<UserMessage> list() {
		return userMessageDao.listUserMessage();
	}

	public Page<UserMessage> currentUserPage(Integer userId, int page, int limit) {
		return userMessageDao.pageUserMessageByUserId(userId, page, limit);
	}

	@Transactional
	public void deleteUserAll(Integer userId) {
		userMessageDao.deleteUserMessageByUserId(userId);
	}

}
