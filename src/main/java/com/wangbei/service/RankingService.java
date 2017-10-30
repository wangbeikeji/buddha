package com.wangbei.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.wangbei.dao.DynamicQuerySqlDao;
import com.wangbei.dao.UserLikeDao;
import com.wangbei.entity.User;
import com.wangbei.entity.UserLike;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.CheckinRankingInfo;
import com.wangbei.pojo.ConsumeMeritRanking;
import com.wangbei.pojo.MethodDesc;
import com.wangbei.util.DateTimeUtil;
import com.wangbei.util.enums.UserLikeTypeEnum;

@Service
public class RankingService {

	@Autowired
	private DynamicQuerySqlDao dynamicQuerySqlDao;

	@Autowired
	private UserLikeDao userLikeDao;

	@Autowired
	private UserService userService;

	public Page<CheckinRankingInfo> dayCheckinRanking(Integer userId, int page, int limit) {
		try {
			Date now = new Date();
			Date startTime = DateTimeUtil.getSdfDate().parse(DateTimeUtil.getSdfDate().format(now));
			String startTimeStr = DateTimeUtil.getSdfTimestampDate().format(startTime);
			Date endTime = DateTimeUtil.getSdfDate()
					.parse(DateTimeUtil.getSdfDate().format(DateTimeUtil.getNextDate(now)));
			String endTimeStr = DateTimeUtil.getSdfTimestampDate().format(endTime);

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("select * from ( ");
			strBuilder.append("select @rownum \\:= @rownum + 1 AS ranking, t4.*, 1 as rankType from ( ");
			strBuilder.append("select inn.*, IFNULL(sum(tr.merit_value), 0) as consume_merit_value from ( ");
			strBuilder.append(
					"select t3.user_id, t3.phone, t3.head_portrait_link, t3.checkin_time, count(t3.like_user_id) AS like_count from ( ");
			strBuilder.append(
					"select t1.checkin_time, t1.user_id, u.phone, u.head_portrait_link, t2.user_id as like_user_id from checkin t1 ");
			strBuilder.append(
					"LEFT JOIN user_like t2 on t1.user_id=t2.resource_id and t2.like_time>= '%s' and t2.like_time<'%s' and t2.type=2 ");
			strBuilder.append("LEFT JOIN user u on t1.user_id=u.id ");
			strBuilder.append(
					"where (u.is_test is NULL or u.is_test!=1) and t1.checkin_time>= '%s' and t1.checkin_time<'%s' ");
			strBuilder.append("GROUP BY t1.user_id, t2.user_id ");
			strBuilder.append(") t3 GROUP BY t3.user_id ");
			strBuilder.append(
					") inn LEFT JOIN trade tr on inn.user_id=tr.user_id and tr.status=1 and tr.type in (1, 2, 3, 4, 5, 9) GROUP BY inn.user_id order by inn.checkin_time asc ");
			strBuilder.append(") t4, (SELECT @rownum \\:= 0) r ");
			strBuilder.append(") as t5 limit %s, %s");

			String sql = String.format(strBuilder.toString(), startTimeStr, endTimeStr, startTimeStr, endTimeStr,
					page * limit, limit);
			Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
			setMethodMap.put(new Integer(0), new MethodDesc("setRanking", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(1), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(2), new MethodDesc("setPhone", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(3), new MethodDesc("setHeadPortraitLink", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(4), new MethodDesc("setCheckinTime", new Class<?>[] { Date.class }));
			setMethodMap.put(new Integer(5), new MethodDesc("setLikeCount", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(6), new MethodDesc("setConsumeMeritValue", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(7), new MethodDesc("setRankType", new Class<?>[] { Integer.class }));

			List<CheckinRankingInfo> content = dynamicQuerySqlDao.execute(CheckinRankingInfo.class, sql, setMethodMap);
			if (content != null && content.size() > 0) {
				// 判断当前用户是否点过赞
				List<Integer> resourceIdList = userLikeDao
						.listResourceIdByUserIdAndTypeAndLikeTimeGreaterThanEqualAndLikeTimeLessThan(userId,
								UserLikeTypeEnum.DayCheckinRankingLike, startTime, endTime);
				if (resourceIdList != null && resourceIdList.size() > 0) {
					for (CheckinRankingInfo ranking : content) {
						if (resourceIdList.contains(ranking.getUserId())) {
							ranking.setCurrentUserIsLike(true);
						}
					}
				}
			}
			return new PageImpl<>(content, new PageRequest(page, limit), 0);
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public Page<CheckinRankingInfo> monthCheckinRanking(Integer userId, int page, int limit) {
		try {
			Date now = new Date();
			String startTime = DateTimeUtil.getSdfTimestampDate().format(DateTimeUtil.getThisMonthDate(now));
			String endTime = DateTimeUtil.getSdfTimestampDate().format(DateTimeUtil.getNextMonthDate(now));

			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("select * from ( ");
			strBuilder.append("select @rownum \\:= @rownum + 1 AS ranking, t4.*, 2 as rankType from ");
			strBuilder.append("(select inn.*, IFNULL(sum(tr.merit_value), 0) as consume_merit_value from ");
			strBuilder.append(
					"(select t2.user_id, u.phone, u.head_portrait_link, count(t3.user_id) as like_count, t2.checkin_count from ");
			strBuilder.append(
					"(select t1.user_id, count(id) as checkin_count from checkin t1 where t1.checkin_time>='%s' and t1.checkin_time<'%s' GROUP BY t1.user_id) t2 ");
			strBuilder.append(
					"LEFT JOIN user_like t3 on t2.user_id=t3.resource_id and t3.like_time>= '%s' and t3.like_time<'%s' and t3.type=3 ");
			strBuilder.append("LEFT JOIN user u on t2.user_id=u.id ");
			strBuilder.append(
					"where u.is_test is NULL or u.is_test!=1 GROUP BY t2.user_id order by t2.checkin_count desc ");
			strBuilder.append(
					") inn LEFT JOIN trade tr on inn.user_id=tr.user_id and tr.status=1 and tr.type in (1, 2, 3, 4, 5, 9) GROUP BY inn.user_id order by inn.checkin_count desc ");
			strBuilder.append(") t4, (SELECT @rownum \\:= 0) r ) as t5 limit %s, %s");

			String sql = String.format(strBuilder.toString(), startTime, endTime, startTime, endTime, page * limit,
					limit);
			Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
			setMethodMap.put(new Integer(0), new MethodDesc("setRanking", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(1), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(2), new MethodDesc("setPhone", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(3), new MethodDesc("setHeadPortraitLink", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(4), new MethodDesc("setLikeCount", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(5), new MethodDesc("setCheckinCount", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(6), new MethodDesc("setConsumeMeritValue", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(7), new MethodDesc("setRankType", new Class<?>[] { Integer.class }));

			List<CheckinRankingInfo> content = dynamicQuerySqlDao.execute(CheckinRankingInfo.class, sql, setMethodMap);
			if (content != null && content.size() > 0) {
				// 判断当前用户是否点过赞
				Date dayStartTime = DateTimeUtil.getSdfDate().parse(DateTimeUtil.getSdfDate().format(now));
				Date dayEndTime = DateTimeUtil.getSdfDate()
						.parse(DateTimeUtil.getSdfDate().format(DateTimeUtil.getNextDate(now)));
				List<Integer> resourceIdList = userLikeDao
						.listResourceIdByUserIdAndTypeAndLikeTimeGreaterThanEqualAndLikeTimeLessThan(userId,
								UserLikeTypeEnum.MonthCheckinRankingLike, dayStartTime, dayEndTime);
				if (resourceIdList != null && resourceIdList.size() > 0) {
					for (CheckinRankingInfo ranking : content) {
						if (resourceIdList.contains(ranking.getUserId())) {
							ranking.setCurrentUserIsLike(true);
						}
					}
				}
			}
			return new PageImpl<>(content, new PageRequest(page, limit), 0);
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public Page<CheckinRankingInfo> totalCheckinRanking(Integer userId, int page, int limit) {
		try {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("select * from ( ");
			strBuilder.append("select @rownum \\:= @rownum + 1 AS ranking, t4.*, 3 as rankType from (");
			strBuilder.append("select inn.*, IFNULL(sum(tr.merit_value), 0) as consume_merit_value from ( ");
			strBuilder.append(
					"select t2.user_id, u.phone, u.head_portrait_link, count(t3.user_id) as like_count, t2.checkin_count from ");
			strBuilder
					.append("(select t1.user_id, count(id) as checkin_count from checkin t1 GROUP BY t1.user_id) t2 ");
			strBuilder.append("LEFT JOIN user_like t3 on t2.user_id=t3.resource_id and t3.type=4 ");
			strBuilder.append("LEFT JOIN user u on t2.user_id=u.id ");
			strBuilder.append(
					"where u.is_test is NULL or u.is_test!=1 GROUP BY t2.user_id order by t2.checkin_count desc ");
			strBuilder.append(
					") inn LEFT JOIN trade tr on inn.user_id=tr.user_id and tr.status=1 and tr.type in (1, 2, 3, 4, 5, 9) GROUP BY inn.user_id order by inn.checkin_count desc ");
			strBuilder.append(") t4, (SELECT @rownum \\:= 0) r) as t5 limit %s, %s");

			String sql = String.format(strBuilder.toString(), page * limit, limit);
			Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
			setMethodMap.put(new Integer(0), new MethodDesc("setRanking", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(1), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(2), new MethodDesc("setPhone", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(3), new MethodDesc("setHeadPortraitLink", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(4), new MethodDesc("setLikeCount", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(5), new MethodDesc("setCheckinCount", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(6), new MethodDesc("setConsumeMeritValue", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(7), new MethodDesc("setRankType", new Class<?>[] { Integer.class }));

			List<CheckinRankingInfo> content = dynamicQuerySqlDao.execute(CheckinRankingInfo.class, sql, setMethodMap);
			if (content != null && content.size() > 0) {
				// 判断当前用户是否点过赞
				Date now = new Date();
				Date startTime = DateTimeUtil.getSdfDate().parse(DateTimeUtil.getSdfDate().format(now));
				Date endTime = DateTimeUtil.getSdfDate()
						.parse(DateTimeUtil.getSdfDate().format(DateTimeUtil.getNextDate(now)));
				List<Integer> resourceIdList = userLikeDao
						.listResourceIdByUserIdAndTypeAndLikeTimeGreaterThanEqualAndLikeTimeLessThan(userId,
								UserLikeTypeEnum.TotalCheckinRankingLike, startTime, endTime);
				if (resourceIdList != null && resourceIdList.size() > 0) {
					for (CheckinRankingInfo ranking : content) {
						if (resourceIdList.contains(ranking.getUserId())) {
							ranking.setCurrentUserIsLike(true);
						}
					}
				}
			}
			return new PageImpl<>(content, new PageRequest(page, limit), 0);
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public void userLike(int userId, Integer checkinUserId, UserLikeTypeEnum type) {
		try {
			Date now = new Date();
			Date startTime = DateTimeUtil.getSdfDate().parse(DateTimeUtil.getSdfDate().format(now));
			Date endTime = DateTimeUtil.getSdfDate()
					.parse(DateTimeUtil.getSdfDate().format(DateTimeUtil.getNextDate(now)));
			List<UserLike> list = userLikeDao
					.listByUserIdAndTypeAndResourceIdAndLikeTimeGreaterThanEqualAndLikeTimeLessThan(userId, type,
							checkinUserId, startTime, endTime);
			if (list != null && list.size() > 0) {
				throw new ServiceException(ExceptionEnum.ALREADY_LIKE_EXCEPTION);
			} else {
				UserLike userLike = new UserLike();
				userLike.setLikeTime(new Date());
				userLike.setResourceId(checkinUserId);
				userLike.setType(type);
				userLike.setUserId(userId);
				userLikeDao.createUserLike(userLike);
			}
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public CheckinRankingInfo currentUserCheckinRanking(Integer userId) {
		if (userId == null || userId <= 0) {
			return null;
		}
		User user = userService.getUser(userId);

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("select * from ( ");
		strBuilder.append(
				"select @rownum \\:= @rownum + 1 AS ranking, t4.* from (select t2.user_id, u.phone, u.head_portrait_link, count(t3.user_id) as like_count, t2.checkin_count from ");
		strBuilder.append("(select t1.user_id, count(id) as checkin_count from checkin t1 GROUP BY t1.user_id) t2 ");
		strBuilder.append("LEFT JOIN user_like t3 on t2.user_id=t3.resource_id and t3.type=4 ");
		strBuilder.append("LEFT JOIN user u on t2.user_id=u.id ");
		if (!(user.getIsTest() != null && user.getIsTest() == true)) {
			strBuilder.append(" where u.is_test is NULL or u.is_test!=1 ");
		}
		strBuilder.append(" GROUP BY t2.user_id order by t2.checkin_count desc) t4, (SELECT @rownum \\:= 0) r ");
		strBuilder.append(") as t5 where %s");

		String sql = String.format(strBuilder.toString(), "t5.user_id=" + userId);
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setRanking", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setHeadPortraitLink", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setLikeCount", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setCheckinCount", new Class<?>[] { Integer.class }));

		CheckinRankingInfo result = null;

		List<CheckinRankingInfo> content = dynamicQuerySqlDao.execute(CheckinRankingInfo.class, sql, setMethodMap);

		if (content != null && content.size() > 0) {
			result = content.get(0);
			result.setMeritValue(userService.getUserMeritValue(userId));
		} else {
			result = new CheckinRankingInfo();
			result.setUserId(userId);
			result.setCheckinCount(0);
			result.setLikeCount(0);
			result.setRanking(0);
			result.setMeritValue(userService.getUserMeritValue(userId));
			result.setPhone(user.getPhone());
			result.setHeadPortraitLink(user.getHeadPortraitLink());
		}
		if (user.getIsTest() != null && user.getIsTest() == true) {
			result.setRanking(0);
		}
		return result;
	}

	public Page<ConsumeMeritRanking> dayConsumeMeritRanking(int page, int limit) {
		try {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("select * from ( ");
			strBuilder.append(
					"select @rownum \\:= @rownum + 1 AS ranking, t4.*, 1 as rankType from (select t1.user_id, t2.name, t2.phone, t2.head_portrait_link, t1.consume_merit_value, t3.merit_value from (select user_id, sum(merit_value) as consume_merit_value from trade where status=1 and type in (1, 2, 3, 4, 5, 9) and create_time>='%s' and create_time<'%s' GROUP BY user_id ORDER BY consume_merit_value desc) t1 ");
			strBuilder.append("LEFT JOIN user t2 on t1.user_id=t2.id ");
			strBuilder.append("LEFT JOIN account t3 on t1.user_id=t3.user_id ");
			strBuilder.append("where t2.is_test is null or t2.is_test!=1) t4, (SELECT @rownum \\:= 0) r ");
			strBuilder.append(") as t5 limit %s, %s");

			Date now = new Date();
			Date startTime = DateTimeUtil.getSdfDate().parse(DateTimeUtil.getSdfDate().format(now));
			String startTimeStr = DateTimeUtil.getSdfTimestampDate().format(startTime);
			Date endTime = DateTimeUtil.getSdfDate()
					.parse(DateTimeUtil.getSdfDate().format(DateTimeUtil.getNextDate(now)));
			String endTimeStr = DateTimeUtil.getSdfTimestampDate().format(endTime);
			String sql = String.format(strBuilder.toString(), startTimeStr, endTimeStr, page * limit, limit);

			Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
			setMethodMap.put(new Integer(0), new MethodDesc("setRanking", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(1), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(2), new MethodDesc("setName", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(3), new MethodDesc("setPhone", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(4), new MethodDesc("setHeadPortraitLink", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(5), new MethodDesc("setConsumeMeritValue", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(6), new MethodDesc("setMeritValue", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(7), new MethodDesc("setRankType", new Class<?>[] { Integer.class }));
			List<ConsumeMeritRanking> content = dynamicQuerySqlDao.execute(ConsumeMeritRanking.class, sql,
					setMethodMap);
			return new PageImpl<>(content, new PageRequest(page, limit), 0);
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public Page<ConsumeMeritRanking> monthConsumeMeritRanking(int page, int limit) {
		try {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("select * from ( ");
			strBuilder.append(
					"select @rownum \\:= @rownum + 1 AS ranking, t4.*, 2 as rankType from (select t1.user_id, t2.name, t2.phone, t2.head_portrait_link, t1.consume_merit_value, t3.merit_value from (select user_id, sum(merit_value) as consume_merit_value from trade where status=1 and type in (1, 2, 3, 4, 5, 9) and create_time>='%s' and create_time<'%s' GROUP BY user_id ORDER BY consume_merit_value desc) t1 ");
			strBuilder.append("LEFT JOIN user t2 on t1.user_id=t2.id ");
			strBuilder.append("LEFT JOIN account t3 on t1.user_id=t3.user_id ");
			strBuilder.append("where t2.is_test is null or t2.is_test!=1) t4, (SELECT @rownum \\:= 0) r ");
			strBuilder.append(") as t5 limit %s, %s");

			Date now = new Date();
			String startTimeStr = DateTimeUtil.getSdfTimestampDate().format(DateTimeUtil.getThisMonthDate(now));
			String endTimeStr = DateTimeUtil.getSdfTimestampDate().format(DateTimeUtil.getNextMonthDate(now));
			String sql = String.format(strBuilder.toString(), startTimeStr, endTimeStr, page * limit, limit);

			Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
			setMethodMap.put(new Integer(0), new MethodDesc("setRanking", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(1), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(2), new MethodDesc("setName", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(3), new MethodDesc("setPhone", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(4), new MethodDesc("setHeadPortraitLink", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(5), new MethodDesc("setConsumeMeritValue", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(6), new MethodDesc("setMeritValue", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(7), new MethodDesc("setRankType", new Class<?>[] { Integer.class }));
			List<ConsumeMeritRanking> content = dynamicQuerySqlDao.execute(ConsumeMeritRanking.class, sql,
					setMethodMap);
			return new PageImpl<>(content, new PageRequest(page, limit), 0);
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public Page<ConsumeMeritRanking> totalConsumeMeritRanking(int page, int limit) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("select * from ( ");
		strBuilder.append(
				"select @rownum \\:= @rownum + 1 AS ranking, t4.*, 3 as rankType from (select t1.user_id, t2.name, t2.phone, t2.head_portrait_link, t1.consume_merit_value, t3.merit_value from (select user_id, sum(merit_value) as consume_merit_value from trade where status=1 and type in (1, 2, 3, 4, 5, 9) GROUP BY user_id ORDER BY consume_merit_value desc) t1 ");
		strBuilder.append("LEFT JOIN user t2 on t1.user_id=t2.id ");
		strBuilder.append("LEFT JOIN account t3 on t1.user_id=t3.user_id ");
		strBuilder.append("where t2.is_test is null or t2.is_test!=1) t4, (SELECT @rownum \\:= 0) r ");
		strBuilder.append(") as t5 limit %s, %s");
		String sql = String.format(strBuilder.toString(), page * limit, limit);
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setRanking", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setHeadPortraitLink", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setConsumeMeritValue", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setMeritValue", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setRankType", new Class<?>[] { Integer.class }));
		List<ConsumeMeritRanking> content = dynamicQuerySqlDao.execute(ConsumeMeritRanking.class, sql, setMethodMap);
		return new PageImpl<>(content, new PageRequest(page, limit), 0);
	}

	public Integer getUserConsumeMeritValue(Integer userId) {
		String sql = String.format(
				"select sum(tr.merit_value) from trade tr where tr.status=1 and tr.type in (1, 2, 3, 4, 5, 9) and user_id='%s' GROUP BY tr.user_id;",
				userId);
		BigDecimal result = dynamicQuerySqlDao.executeComputeSql(sql);
		return result == null ? 0 : result.intValue();
	}

	public ConsumeMeritRanking currentUserConsumeMeritRanking(Integer userId) {
		if (userId == null || userId <= 0) {
			return null;
		}
		User user = userService.getUser(userId);
		if (user.getIsTest() != null && user.getIsTest() == true) {
			ConsumeMeritRanking result = new ConsumeMeritRanking();
			result.setConsumeMeritValue(getUserConsumeMeritValue(userId));
			result.setHeadPortraitLink(user.getHeadPortraitLink());
			result.setMeritValue(userService.getUserMeritValue(userId));
			result.setName(user.getName());
			result.setPhone(user.getPhone());
			result.setRanking(0);
			result.setRankType(0);
			result.setUserId(userId);
			return result;
		}

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("select * from ( ");
		strBuilder.append(
				"select @rownum \\:= @rownum + 1 AS ranking, t4.* from (select t1.user_id, t2.name, t2.phone, t2.head_portrait_link, t1.consume_merit_value, t3.merit_value from (select user_id, sum(merit_value) as consume_merit_value from trade where status=1 and type in (1, 2, 3, 4, 5, 9) GROUP BY user_id ORDER BY consume_merit_value desc) t1 ");
		strBuilder.append("LEFT JOIN user t2 on t1.user_id=t2.id ");
		strBuilder.append("LEFT JOIN account t3 on t1.user_id=t3.user_id ");
		strBuilder.append("where t2.is_test is null or t2.is_test!=1) t4, (SELECT @rownum \\:= 0) r ");
		strBuilder.append(") as t5 where %s");
		String sql = String.format(strBuilder.toString(), "t5.user_id=" + userId);
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setRanking", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setHeadPortraitLink", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setConsumeMeritValue", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setMeritValue", new Class<?>[] { Integer.class }));

		ConsumeMeritRanking result = null;
		List<ConsumeMeritRanking> content = dynamicQuerySqlDao.execute(ConsumeMeritRanking.class, sql, setMethodMap);
		if (content != null && content.size() > 0) {
			result = content.get(0);
		} else {
			result = new ConsumeMeritRanking();
			result.setUserId(userId);
			result.setConsumeMeritValue(0);
			result.setHeadPortraitLink(user.getHeadPortraitLink());
			result.setMeritValue(userService.getUserMeritValue(userId));
			result.setName(user.getName());
			result.setPhone(user.getPhone());
			result.setRanking(0);
		}
		return result;
	}

}
