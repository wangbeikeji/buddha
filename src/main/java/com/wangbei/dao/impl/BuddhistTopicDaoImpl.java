package com.wangbei.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.BuddhistTopicDao;
import com.wangbei.dao.DynamicQuerySqlDao;
import com.wangbei.dao.impl.jpa.BuddhistTopicRepository;
import com.wangbei.entity.BuddhistTopic;
import com.wangbei.pojo.BuddhistTopicFullInfo;
import com.wangbei.pojo.MethodDesc;

/**
 * 修行主题 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class BuddhistTopicDaoImpl implements BuddhistTopicDao {

	@Autowired
	private BuddhistTopicRepository buddhistTopicRepository;

	@Autowired
	private DynamicQuerySqlDao sqlDao;

	@Override
	public BuddhistTopic createBuddhistTopic(BuddhistTopic buddhistTopic) {
		return buddhistTopicRepository.save(buddhistTopic);
	}

	@Override
	public void deleteBuddhistTopicById(Integer id) {
		buddhistTopicRepository.delete(id);
	}

	@Override
	public BuddhistTopic updateBuddhistTopic(BuddhistTopic buddhistTopic) {
		return buddhistTopicRepository.save(buddhistTopic);
	}

	@Override
	public BuddhistTopic retrieveBuddhistTopicById(Integer id) {
		return buddhistTopicRepository.findById(id);
	}

	@Override
	public Page<BuddhistTopic> pageBuddhistTopic(int page, int limit) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "sortNum"));
		return buddhistTopicRepository.findAll(new PageRequest(page, limit, sort));
	}

	@Override
	public Page<BuddhistTopic> pageBuddhistTopicByIsEnable(boolean isEnable, int page, int limit) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "sortNum"));
		return buddhistTopicRepository.findByIsEnable(isEnable, new PageRequest(page, limit, sort));
	}

	@Override
	public List<BuddhistTopic> listBuddhistTopic() {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "sortNum"));
		return buddhistTopicRepository.findAll(sort);
	}

	@Override
	public List<BuddhistTopicFullInfo> listBuddhistTopicByIsEnable(boolean isEnable, int page, int limit) {
		String sql = String.format(
				"select t1.id, t1.end_time, t1.link, t1.small_link, t1.start_time, t1.status, t1.title, t1.topic_detail, t1.topic_time, t1.is_enable, count(t2.user_id) as like_count from buddhist_topic t1 LEFT JOIN user_like t2 on t1.id=t2.resource_id and t2.type=1 where t1.is_enable=1 GROUP BY t1.id order by t1.sort_num desc LIMIT %s, %s",
				(page * limit), limit);
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setId", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setEndTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setLink", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setSmallLink", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setStartTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setStatus", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setTitle", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setTopicDetail", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(8), new MethodDesc("setTopicTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(9), new MethodDesc("setIsEnable", new Class<?>[] { Boolean.class }));
		setMethodMap.put(new Integer(10), new MethodDesc("setUserLikeCount", new Class<?>[] { Integer.class }));
		return sqlDao.execute(BuddhistTopicFullInfo.class, sql, setMethodMap);
	}

	@Override
	public List<BuddhistTopic> listBuddhistTopicByMaterialResourceId(Integer materialResourceId) {
		return buddhistTopicRepository.findByMaterialResourceId(materialResourceId);
	}

}
