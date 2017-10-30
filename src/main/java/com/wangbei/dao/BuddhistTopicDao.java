package com.wangbei.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wangbei.entity.BuddhistTopic;
import com.wangbei.pojo.BuddhistTopicFullInfo;

/**
 * 修行主题 Dao
 * 
 * @author luomengan
 *
 */
public interface BuddhistTopicDao {

	public BuddhistTopic createBuddhistTopic(BuddhistTopic buddhistTopic);

	public void deleteBuddhistTopicById(Integer id);

	public BuddhistTopic updateBuddhistTopic(BuddhistTopic buddhistTopic);

	public BuddhistTopic retrieveBuddhistTopicById(Integer id);

	public Page<BuddhistTopic> pageBuddhistTopic(int page, int limit);

	public Page<BuddhistTopic> pageBuddhistTopicByIsEnable(boolean isEnable, int page, int limit);

	public List<BuddhistTopic> listBuddhistTopic();

	public List<BuddhistTopicFullInfo> listBuddhistTopicByIsEnable(boolean isEnable, int page, int limit);

	public List<BuddhistTopic> listBuddhistTopicByMaterialResourceId(Integer materialResourceId);

}
