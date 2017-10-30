package com.wangbei.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangbei.dao.DynamicQuerySqlDao;
import com.wangbei.dao.OrderDao;
import com.wangbei.dao.UserDao;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.MethodDesc;
import com.wangbei.pojo.StaChargeInfo;
import com.wangbei.pojo.StaCheckinInfo;
import com.wangbei.pojo.StaMeritConsumeInfo;
import com.wangbei.pojo.StaOrderInfo;
import com.wangbei.pojo.StaUserInfo;
import com.wangbei.util.DateTimeUtil;

/**
 * 数据统计 Service
 * 
 * @author luomengan
 *
 */
@Service
public class DataStatisticsService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DynamicQuerySqlDao dynamicQuerySqlDao;

	public Double dayAmountOfCharge(String dayStr) {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date staDate = sdfDate.parse(dayStr);
			Date nextDate = new DateTime(staDate).plusHours(24).toDate();
			Double result = orderDao.staAmountByDateStage(staDate, nextDate);
			return result == null ? 0 : result;
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public Integer dayCountOfUser(String dayStr) {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date staDate = sdfDate.parse(dayStr);
			Date nextDate = new DateTime(staDate).plusHours(24).toDate();
			Integer result = userDao.staCountOfUser(staDate, nextDate);
			return result == null ? 0 : result;
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public Map<String, Integer> staDayCountOfUser(String dayStr, int forward) {
		try {
			Map<String, Integer> result = new TreeMap<>();
			Date staDate = DateTimeUtil.getSdfDate().parse(dayStr);
			for (int i = 0; i < forward; i++) {
				if (i == 0) {
					Integer amount = dayCountOfUser(DateTimeUtil.getSdfDate().format(staDate));
					String key = DateTimeUtil.getSdfDayDate().format(staDate);
					result.put(key, amount == null ? 0 : amount);
				} else {
					Date beforeStaDate = new DateTime(staDate).minusHours(i * 24).toDate();
					Integer amount = dayCountOfUser(DateTimeUtil.getSdfDate().format(beforeStaDate));
					String key = DateTimeUtil.getSdfDayDate().format(beforeStaDate);
					result.put(key, amount == null ? 0 : amount);
				}
			}
			return result;
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public Map<String, Integer> staWeekCountOfUser(String dayStr, int forward) {
		try {
			Map<String, Integer> result = new TreeMap<>();
			Date staDate = DateTimeUtil.getSdfDate().parse(dayStr);
			List<Date> stageList = new ArrayList<>();
			for (int i = 0; i < forward; i++) {
				if (i == 0) {
					Date startDate = DateTimeUtil.getMonday(staDate);
					Date endDate = DateTimeUtil.getNextDate(staDate);
					stageList.add(endDate);
					stageList.add(startDate);
				} else {
					Date endDate = stageList.get(stageList.size() - 1);
					Date startDate = DateTimeUtil.getMonday(new Date(endDate.getTime() - 1000));
					stageList.add(endDate);
					stageList.add(startDate);
				}
			}

			for (int i = 0; i < stageList.size(); i += 2) {
				Date startDate = stageList.get(i + 1);
				Date endDate = stageList.get(i);
				Integer value = userDao.staCountOfUser(startDate, endDate);
				result.put(
						DateTimeUtil.getSdfSimpleDate().format(startDate) + "~"
								+ DateTimeUtil.getSdfSimpleDate().format(new Date(endDate.getTime() - 1000)),
						value == null ? 0 : value);
			}
			return result;
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public Map<String, Integer> staMonthCountOfUser(String dayStr, int forward) {
		try {
			Map<String, Integer> result = new TreeMap<>();
			Date staDate = DateTimeUtil.getSdfDate().parse(dayStr);
			List<Date> stageList = new ArrayList<>();
			for (int i = 0; i < forward; i++) {
				if (i == 0) {
					Date endDate = DateTimeUtil.getNextMonthDate(staDate);
					Date startDate = DateTimeUtil.getThisMonthDate(staDate);
					stageList.add(endDate);
					stageList.add(startDate);
				} else {
					Date endDate = stageList.get(stageList.size() - 1);
					Date startDate = DateTimeUtil.getThisMonthDate(new Date(endDate.getTime() - 1000));
					stageList.add(endDate);
					stageList.add(startDate);
				}
			}

			for (int i = 0; i < stageList.size(); i += 2) {
				Date startDate = stageList.get(i + 1);
				Date endDate = stageList.get(i);
				Integer value = userDao.staCountOfUser(startDate, endDate);
				result.put(DateTimeUtil.getSdfMonthDate().format(startDate), value == null ? 0 : value);
			}
			return result;
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public Map<String, Double> staDayAmountOfCharge(String dayStr, int forward) {
		try {
			Map<String, Double> result = new TreeMap<>();
			Date staDate = DateTimeUtil.getSdfDate().parse(dayStr);
			for (int i = 0; i < forward; i++) {
				if (i == 0) {
					Double amount = dayAmountOfCharge(DateTimeUtil.getSdfDate().format(staDate));
					String key = DateTimeUtil.getSdfDayDate().format(staDate);
					result.put(key, amount == null ? 0 : amount);
				} else {
					Date beforeStaDate = new DateTime(staDate).minusHours(i * 24).toDate();
					Double amount = dayAmountOfCharge(DateTimeUtil.getSdfDate().format(beforeStaDate));
					String key = DateTimeUtil.getSdfDayDate().format(beforeStaDate);
					result.put(key, amount == null ? 0 : amount);
				}
			}
			return result;
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public Map<String, Double> staWeekAmountOfCharge(String dayStr, int forward) {
		try {
			Map<String, Double> result = new TreeMap<>();
			Date staDate = DateTimeUtil.getSdfDate().parse(dayStr);
			List<Date> stageList = new ArrayList<>();
			for (int i = 0; i < forward; i++) {
				if (i == 0) {
					Date startDate = DateTimeUtil.getMonday(staDate);
					Date endDate = DateTimeUtil.getNextDate(staDate);
					stageList.add(endDate);
					stageList.add(startDate);
				} else {
					Date endDate = stageList.get(stageList.size() - 1);
					Date startDate = DateTimeUtil.getMonday(new Date(endDate.getTime() - 1000));
					stageList.add(endDate);
					stageList.add(startDate);
				}
			}

			for (int i = 0; i < stageList.size(); i += 2) {
				Date startDate = stageList.get(i + 1);
				Date endDate = stageList.get(i);
				Double value = orderDao.staAmountByDateStage(startDate, endDate);
				result.put(
						DateTimeUtil.getSdfSimpleDate().format(startDate) + "~"
								+ DateTimeUtil.getSdfSimpleDate().format(new Date(endDate.getTime() - 1000)),
						value == null ? 0 : value);
			}
			return result;
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public Map<String, Double> staMonthAmountOfCharge(String dayStr, int forward) {
		try {
			Map<String, Double> result = new TreeMap<>();
			Date staDate = DateTimeUtil.getSdfDate().parse(dayStr);
			List<Date> stageList = new ArrayList<>();
			for (int i = 0; i < forward; i++) {
				if (i == 0) {
					Date endDate = DateTimeUtil.getNextMonthDate(staDate);
					Date startDate = DateTimeUtil.getThisMonthDate(staDate);
					stageList.add(endDate);
					stageList.add(startDate);
				} else {
					Date endDate = stageList.get(stageList.size() - 1);
					Date startDate = DateTimeUtil.getThisMonthDate(new Date(endDate.getTime() - 1000));
					stageList.add(endDate);
					stageList.add(startDate);
				}
			}

			for (int i = 0; i < stageList.size(); i += 2) {
				Date startDate = stageList.get(i + 1);
				Date endDate = stageList.get(i);
				Double value = orderDao.staAmountByDateStage(startDate, endDate);
				result.put(DateTimeUtil.getSdfMonthDate().format(startDate), value == null ? 0 : value);
			}
			return result;
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.DATE_FORMAT_ERROR_EXCEPTION);
		}
	}

	public List<StaUserInfo> staUserInfo(String keyword, Integer gender, String registStartTime, String registEndTime,
			Integer registStageType, String newestLoginStartTime, String newestLoginEndTime,
			Integer newestLoginStageType) {
		try {
			String keywordCondition = "";
			if (keyword != null && !"".equals(keyword)) {
				keywordCondition = " and (t4.name like '%" + keyword + "%' or t4.phone like '%" + keyword + "%') ";
			}
			String genderCondition = "";
			if (gender != null && (1 == gender || 2 == gender)) {
				genderCondition = String.format(" and t4.gender=%s ", gender);
			}
			String registTimeCondition = "";
			if (registStageType == null || registStageType == 0) {
				// 注册时间_全部
				if (registStartTime != null && !"".equals(registStartTime)) {
					Date start = DateTimeUtil.getSdfTimestampDate().parse(registStartTime);
					registTimeCondition += String.format(" and t4.create_time>='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(start));
				}
				if (registEndTime != null && !"".equals(registEndTime)) {
					Date end = DateTimeUtil.getSdfTimestampDate().parse(registEndTime);
					registTimeCondition += String.format(" and t4.create_time<='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(end));
				}
			} else if (registStageType == 1 || registStageType == 2) {
				// 注册时间_7天之内，注册时间_1月之内
				Date end = new Date();
				Date start = registStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.getSdfTimestampDate().format(end);
				String startStr = DateTimeUtil.getSdfTimestampDate().format(start);
				registTimeCondition = String.format(" and t4.create_time>='%s' and t4.create_time<='%s' ", startStr,
						endStr);
			}
			String loginTimeCondition = "";
			if (newestLoginStageType == null || newestLoginStageType == 0) {
				// 登陆时间_全部
				if (newestLoginStartTime != null && !"".equals(newestLoginStartTime)) {
					Date start = DateTimeUtil.getSdfTimestampDate().parse(newestLoginStartTime);
					loginTimeCondition += String.format(" and t4.login_time>='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(start));
				}
				if (newestLoginEndTime != null && !"".equals(newestLoginEndTime)) {
					Date end = DateTimeUtil.getSdfTimestampDate().parse(newestLoginEndTime);
					loginTimeCondition += String.format(" and t4.login_time<='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(end));
				}
			} else if (newestLoginStageType == 1 || newestLoginStageType == 2) {
				// 登陆时间_7天之内，登陆时间_1月之内
				Date end = new Date();
				Date start = registStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.getSdfTimestampDate().format(end);
				String startStr = DateTimeUtil.getSdfTimestampDate().format(start);
				loginTimeCondition = String.format(" and t4.login_time>='%s' and t4.login_time<='%s' ", startStr,
						endStr);
			}

			String sql = String.format(
					"select t4.id, t4.name, t4.phone, t4.gender, t4.birthday, t4.address, t4.create_time as registTime, t4.login_time as loginTime, IFNULL(t5.user_charge, 0) as userCharge from (select * from (select t1.*, t2.login_time from user t1 LEFT JOIN user_login_log t2 on t1.id=t2.user_id ORDER BY t1.id asc, t2.login_time desc) t3 GROUP BY t3.id) t4 "
							+ "LEFT JOIN (select user_id, sum(total_amount) as user_charge from orders where status=2 and create_time>='1970-01-01 00:00:00' and create_time<'2200-12-31 00:00:00' group by user_id) t5 on t4.id=t5.user_id "
							+ "where 1=1 and (t4.is_test is null or t4.is_test != 1) %s %s %s %s order by t4.create_time desc;",
					keywordCondition, genderCondition, registTimeCondition, loginTimeCondition);
			Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
			setMethodMap.put(new Integer(0), new MethodDesc("setId", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(1), new MethodDesc("setName", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(2), new MethodDesc("setPhone", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(3), new MethodDesc("setGender", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(4), new MethodDesc("setBirthday", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(5), new MethodDesc("setAddress", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(6), new MethodDesc("setRegistTime", new Class<?>[] { Date.class }));
			setMethodMap.put(new Integer(7), new MethodDesc("setLoginTime", new Class<?>[] { Date.class }));
			setMethodMap.put(new Integer(8), new MethodDesc("setUserCharge", new Class<?>[] { Double.class }));

			List<StaUserInfo> result = dynamicQuerySqlDao.execute(StaUserInfo.class, sql, setMethodMap);
			return result;
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.PARSE_STRING_TO_DATE_EXCEPTION);
		}
	}

	public List<StaChargeInfo> staChargeInfo(String keyword, Integer chargeType, String registStartTime,
			String registEndTime, Integer registStageType, String chargeStartTime, String chargeEndTime,
			Integer chargeStageType, Integer chargeStatus) {
		try {
			String keywordCondition = "";
			if (keyword != null && !"".equals(keyword)) {
				keywordCondition = " and t3.phone like '%" + keyword + "%' ";
			}
			String chargeTypeCondition = "";
			if (chargeType != null && chargeType == 1) {
				chargeTypeCondition = " and t2.type=0 ";
			} else if (chargeType != null && chargeType == 2) {
				chargeTypeCondition = " and t2.type=8 ";
			} else if (chargeType != null && chargeType == 3) {
				chargeTypeCondition = " and t2.type=7 ";
			}
			String chargeStatusCondition = "";
			if (chargeStatus != null && chargeStatus == 1) {
				chargeStatusCondition = " and t1.status=2 ";
			} else if (chargeStatus != null && chargeStatus == 2) {
				chargeStatusCondition = " and t1.status=0 ";
			}
			String registTimeCondition = "";
			if (registStageType == null || registStageType == 0) {
				// 注册时间_全部
				if (registStartTime != null && !"".equals(registStartTime)) {
					Date start = DateTimeUtil.getSdfTimestampDate().parse(registStartTime);
					registTimeCondition += String.format(" and t3.create_time>='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(start));
				}
				if (registEndTime != null && !"".equals(registEndTime)) {
					Date end = DateTimeUtil.getSdfTimestampDate().parse(registEndTime);
					registTimeCondition += String.format(" and t3.create_time<='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(end));
				}
			} else if (registStageType == 1 || registStageType == 2) {
				// 注册时间_7天之内，注册时间_1月之内
				Date end = new Date();
				Date start = registStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.getSdfTimestampDate().format(end);
				String startStr = DateTimeUtil.getSdfTimestampDate().format(start);
				registTimeCondition = String.format(" and t3.create_time>='%s' and t3.create_time<='%s' ", startStr,
						endStr);
			}
			String chargeTimeCondition = "";
			if (chargeStageType == null || chargeStageType == 0) {
				// 充值时间_全部
				if (chargeStartTime != null && !"".equals(chargeStartTime)) {
					Date start = DateTimeUtil.getSdfTimestampDate().parse(chargeStartTime);
					chargeTimeCondition += String.format(" and t1.modify_time>='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(start));
				}
				if (chargeEndTime != null && !"".equals(chargeEndTime)) {
					Date end = DateTimeUtil.getSdfTimestampDate().parse(chargeEndTime);
					chargeTimeCondition += String.format(" and t1.modify_time<='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(end));
				}
			} else if (chargeStageType == 1 || chargeStageType == 2) {
				// 充值时间_7天之内，充值时间_1月之内
				Date end = new Date();
				Date start = registStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.getSdfTimestampDate().format(end);
				String startStr = DateTimeUtil.getSdfTimestampDate().format(start);
				chargeTimeCondition = String.format(" and t1.modify_time>='%s' and t1.modify_time<='%s' ", startStr,
						endStr);
			}

			String sql = String.format(
					"select t1.user_id as userId, t3.name, t3.phone, t3.gender, t3.birthday, t1.status, t3.create_time as registTime, t1.trade_no as tradeNo, "
							+ "t1.total_amount as chargeAmount, t2.type, t1.payment_type as paymentType, t2.merit_value as meritValue, t1.modify_time as chargeTime, t1.create_time as orderTime from orders t1 "
							+ "LEFT JOIN trade t2 on t1.trade_no=t2.trade_no LEFT JOIN user t3 on t1.user_id=t3.id "
							+ "where (t3.is_test is null or t3.is_test != 1) %s %s %s %s %s order by t1.modify_time desc;",
					keywordCondition, chargeTypeCondition, registTimeCondition, chargeTimeCondition,
					chargeStatusCondition);
			Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
			setMethodMap.put(new Integer(0), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(1), new MethodDesc("setName", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(2), new MethodDesc("setPhone", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(3), new MethodDesc("setGender", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(4), new MethodDesc("setBirthday", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(5), new MethodDesc("setStatus", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(6), new MethodDesc("setRegistTime", new Class<?>[] { Date.class }));
			setMethodMap.put(new Integer(7), new MethodDesc("setTradeNo", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(8), new MethodDesc("setChargeAmount", new Class<?>[] { Double.class }));
			setMethodMap.put(new Integer(9), new MethodDesc("setType", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(10), new MethodDesc("setPaymentType", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(11), new MethodDesc("setMeritValue", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(12), new MethodDesc("setChargeTime", new Class<?>[] { Date.class }));
			setMethodMap.put(new Integer(13), new MethodDesc("setOrderTime", new Class<?>[] { Date.class }));
			List<StaChargeInfo> result = dynamicQuerySqlDao.execute(StaChargeInfo.class, sql, setMethodMap);
			return result;
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.PARSE_STRING_TO_DATE_EXCEPTION);
		}
	}

	public Double staChargeTotalAmount(String keyword, Integer chargeType, String registStartTime, String registEndTime,
			Integer registStageType, String chargeStartTime, String chargeEndTime, Integer chargeStageType,
			Integer chargeStatus) {
		if (chargeStatus != null && chargeStatus == 1) {
			try {
				String keywordCondition = "";
				if (keyword != null && !"".equals(keyword)) {
					keywordCondition = " and t3.phone like '%" + keyword + "%' ";
				}
				String chargeTypeCondition = "";
				if (chargeType != null && chargeType == 1) {
					chargeTypeCondition = " and t2.type=0 ";
				} else if (chargeType != null && chargeType == 2) {
					chargeTypeCondition = " and t2.type=8 ";
				} else if (chargeType != null && chargeType == 3) {
					chargeTypeCondition = " and t2.type=7 ";
				}
				String chargeStatusCondition = "";
				if (chargeStatus != null && chargeStatus == 1) {
					chargeStatusCondition = " and t1.status=2 ";
				} else if (chargeStatus != null && chargeStatus == 2) {
					chargeStatusCondition = " and t1.status=0 ";
				}
				String registTimeCondition = "";
				if (registStageType == null || registStageType == 0) {
					// 注册时间_全部
					if (registStartTime != null && !"".equals(registStartTime)) {
						Date start = DateTimeUtil.getSdfTimestampDate().parse(registStartTime);
						registTimeCondition += String.format(" and t3.create_time>='%s' ",
								DateTimeUtil.getSdfTimestampDate().format(start));
					}
					if (registEndTime != null && !"".equals(registEndTime)) {
						Date end = DateTimeUtil.getSdfTimestampDate().parse(registEndTime);
						registTimeCondition += String.format(" and t3.create_time<='%s' ",
								DateTimeUtil.getSdfTimestampDate().format(end));
					}
				} else if (registStageType == 1 || registStageType == 2) {
					// 注册时间_7天之内，注册时间_1月之内
					Date end = new Date();
					Date start = registStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7)
							: DateTimeUtil.getMonthsAgo(end, 1);
					String endStr = DateTimeUtil.getSdfTimestampDate().format(end);
					String startStr = DateTimeUtil.getSdfTimestampDate().format(start);
					registTimeCondition = String.format(" and t3.create_time>='%s' and t3.create_time<='%s' ", startStr,
							endStr);
				}
				String chargeTimeCondition = "";
				if (chargeStageType == null || chargeStageType == 0) {
					// 充值时间_全部
					if (chargeStartTime != null && !"".equals(chargeStartTime)) {
						Date start = DateTimeUtil.getSdfTimestampDate().parse(chargeStartTime);
						chargeTimeCondition += String.format(" and t1.modify_time>='%s' ",
								DateTimeUtil.getSdfTimestampDate().format(start));
					}
					if (chargeEndTime != null && !"".equals(chargeEndTime)) {
						Date end = DateTimeUtil.getSdfTimestampDate().parse(chargeEndTime);
						chargeTimeCondition += String.format(" and t1.modify_time<='%s' ",
								DateTimeUtil.getSdfTimestampDate().format(end));
					}
				} else if (chargeStageType == 1 || chargeStageType == 2) {
					// 充值时间_7天之内，充值时间_1月之内
					Date end = new Date();
					Date start = registStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7)
							: DateTimeUtil.getMonthsAgo(end, 1);
					String endStr = DateTimeUtil.getSdfTimestampDate().format(end);
					String startStr = DateTimeUtil.getSdfTimestampDate().format(start);
					chargeTimeCondition = String.format(" and t1.modify_time>='%s' and t1.modify_time<='%s' ", startStr,
							endStr);
				}
				String sql = String.format(
						"select sum(t1.total_amount) from orders t1 "
								+ "LEFT JOIN trade t2 on t1.trade_no=t2.trade_no LEFT JOIN user t3 on t1.user_id=t3.id "
								+ "where (t3.is_test is null or t3.is_test != 1) %s %s %s %s %s order by t1.modify_time desc;",
						keywordCondition, chargeTypeCondition, registTimeCondition, chargeTimeCondition,
						chargeStatusCondition);
				return dynamicQuerySqlDao.executeComputeSql(sql);
			} catch (ParseException e) {
				throw new ServiceException(ExceptionEnum.PARSE_STRING_TO_DATE_EXCEPTION);
			}
		} else {
			return 0d;
		}
	}

	public StaOrderInfo staOrderInfo(String registStartTime, String registEndTime, Integer registStageType,
			String orderStartTime, String orderEndTime, Integer orderStageType) {
		try {
			StaOrderInfo result = new StaOrderInfo();
			// step 1 : 查询香、花、果的总功德数
			String staCountMeritValueSql = "select sum(t1.merit_value) from trade t1 LEFT JOIN user t2 on t1.user_id=t2.id where t1.status=1 and (t2.is_test is null or t2.is_test != 1) %s %s %s";
			String registTimeCondition = "";
			if (registStageType == null || registStageType == 0) {
				// 注册时间_全部
				if (registStartTime != null && !"".equals(registStartTime)) {
					Date start = DateTimeUtil.getSdfTimestampDate().parse(registStartTime);
					registTimeCondition += String.format(" and t2.create_time>='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(start));
				}
				if (registEndTime != null && !"".equals(registEndTime)) {
					Date end = DateTimeUtil.getSdfTimestampDate().parse(registEndTime);
					registTimeCondition += String.format(" and t2.create_time<='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(end));
				}
			} else if (registStageType == 1 || registStageType == 2) {
				// 注册时间_7天之内，注册时间_1月之内
				Date end = new Date();
				Date start = registStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.getSdfTimestampDate().format(end);
				String startStr = DateTimeUtil.getSdfTimestampDate().format(start);
				registTimeCondition = String.format(" and t2.create_time>='%s' and t2.create_time<='%s' ", startStr,
						endStr);
			}
			String orderTimeCondition = "";
			if (orderStageType == null || orderStageType == 0) {
				// 下单时间_全部
				if (orderStartTime != null && !"".equals(orderStartTime)) {
					Date start = DateTimeUtil.getSdfTimestampDate().parse(orderStartTime);
					orderTimeCondition += String.format(" and t1.create_time>='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(start));
				}
				if (orderEndTime != null && !"".equals(orderEndTime)) {
					Date end = DateTimeUtil.getSdfTimestampDate().parse(orderEndTime);
					orderTimeCondition += String.format(" and t1.create_time<='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(end));
				}
			} else if (orderStageType == 1 || orderStageType == 2) {
				// 下单时间_7天之内，下单时间_1月之内
				Date end = new Date();
				Date start = orderStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.getSdfTimestampDate().format(end);
				String startStr = DateTimeUtil.getSdfTimestampDate().format(start);
				orderTimeCondition = String.format(" and t1.create_time>='%s' and t1.create_time<='%s' ", startStr,
						endStr);
			}

			BigDecimal sandalwoodTotalMeritValue = dynamicQuerySqlDao.executeComputeSql(
					String.format(staCountMeritValueSql, " and t1.type=1 ", registTimeCondition, orderTimeCondition));
			result.setSandalwoodTotalMeritValue(
					sandalwoodTotalMeritValue == null ? 0 : sandalwoodTotalMeritValue.intValue());
			BigDecimal fruitTotalMeritValue = dynamicQuerySqlDao.executeComputeSql(
					String.format(staCountMeritValueSql, " and t1.type=2 ", registTimeCondition, orderTimeCondition));
			result.setFruitTotalMeritValue(fruitTotalMeritValue == null ? 0 : fruitTotalMeritValue.intValue());
			BigDecimal flowerTotalMeritValue = dynamicQuerySqlDao.executeComputeSql(
					String.format(staCountMeritValueSql, " and t1.type=3 ", registTimeCondition, orderTimeCondition));
			result.setFlowerTotalMeritValue(flowerTotalMeritValue == null ? 0 : flowerTotalMeritValue.intValue());
			BigDecimal runeTotalMeritValue = dynamicQuerySqlDao.executeComputeSql(
					String.format(staCountMeritValueSql, " and t1.type=4 ", registTimeCondition, orderTimeCondition));
			result.setRuneTotalMeritValue(runeTotalMeritValue == null ? 0 : runeTotalMeritValue.intValue());
			BigDecimal divinationTotalMeritValue = dynamicQuerySqlDao.executeComputeSql(
					String.format(staCountMeritValueSql, " and t1.type=5 ", registTimeCondition, orderTimeCondition));
			result.setDivinationTotalMeritValue(
					divinationTotalMeritValue == null ? 0 : divinationTotalMeritValue.intValue());
			return result;
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.PARSE_STRING_TO_DATE_EXCEPTION);
		}
	}

	public StaOrderInfo staOrderInfo(Integer stageType) {
		try {
			StaOrderInfo result = new StaOrderInfo();
			if (stageType == 1) {
				// 按小时统计
				int forward = 12;
				List<Date> stageList = new ArrayList<>();
				for (int i = 0; i < forward; i++) {
					if (i == 0) {
						Date endDate = new Date();
						Date startDate = DateTimeUtil.getBeforeHourDate(endDate, 0);
						stageList.add(endDate);
						stageList.add(startDate);
					} else {
						Date endDate = stageList.get(stageList.size() - 1);
						Date startDate = DateTimeUtil.getBeforeHourDate(new Date(endDate.getTime() - 1000), 0);
						stageList.add(endDate);
						stageList.add(startDate);
					}
				}
				for (int i = 0; i < stageList.size(); i += 2) {
					Date startDate = stageList.get(i + 1);
					Date endDate = stageList.get(i);
					StaOrderInfo inner = this.staOrderInfo(null, null, 0,
							DateTimeUtil.getSdfTimestampDate().format(startDate),
							DateTimeUtil.getSdfTimestampDate().format(endDate), 0);
					String xStr = DateTimeUtil.getSdfHourDate().format(startDate);
					result.getDivinationMap().put(xStr, inner.getDivinationTotalMeritValue());
					result.getRuneMap().put(xStr, inner.getRuneTotalMeritValue());
					result.getFlowerMap().put(xStr, inner.getFlowerTotalMeritValue());
					result.getFruitMap().put(xStr, inner.getFruitTotalMeritValue());
					result.getSandalwoodMap().put(xStr, inner.getSandalwoodTotalMeritValue());
				}
			} else if (stageType == 2) {
				// 按日统计
				int forward = 10;
				List<Date> stageList = new ArrayList<>();
				for (int i = 0; i < forward; i++) {
					if (i == 0) {
						Date endDate = new Date();
						Date startDate = DateTimeUtil.getSdfDate().parse(DateTimeUtil.getSdfDate().format(endDate));
						stageList.add(endDate);
						stageList.add(startDate);
					} else {
						Date endDate = stageList.get(stageList.size() - 1);
						Date startDate = new DateTime(endDate).minusHours(i * 24).toDate();
						stageList.add(endDate);
						stageList.add(startDate);
					}
				}
				for (int i = 0; i < stageList.size(); i += 2) {
					Date startDate = stageList.get(i + 1);
					Date endDate = stageList.get(i);
					StaOrderInfo inner = this.staOrderInfo(null, null, 0,
							DateTimeUtil.getSdfTimestampDate().format(startDate),
							DateTimeUtil.getSdfTimestampDate().format(endDate), 0);
					String xStr = DateTimeUtil.getSdfDayDate().format(startDate);
					result.getDivinationMap().put(xStr, inner.getDivinationTotalMeritValue());
					result.getRuneMap().put(xStr, inner.getRuneTotalMeritValue());
					result.getFlowerMap().put(xStr, inner.getFlowerTotalMeritValue());
					result.getFruitMap().put(xStr, inner.getFruitTotalMeritValue());
					result.getSandalwoodMap().put(xStr, inner.getSandalwoodTotalMeritValue());
				}
			} else if (stageType == 3) {
				// 按周统计
				int forward = 8;
				List<Date> stageList = new ArrayList<>();
				for (int i = 0; i < forward; i++) {
					if (i == 0) {
						Date endDate = new Date();
						Date startDate = DateTimeUtil.getMonday(endDate);
						stageList.add(endDate);
						stageList.add(startDate);
					} else {
						Date endDate = stageList.get(stageList.size() - 1);
						Date startDate = DateTimeUtil.getMonday(new Date(endDate.getTime() - 1000));
						stageList.add(endDate);
						stageList.add(startDate);
					}
				}
				for (int i = 0; i < stageList.size(); i += 2) {
					Date startDate = stageList.get(i + 1);
					Date endDate = stageList.get(i);
					StaOrderInfo inner = this.staOrderInfo(null, null, 0,
							DateTimeUtil.getSdfTimestampDate().format(startDate),
							DateTimeUtil.getSdfTimestampDate().format(endDate), 0);
					String xStr = DateTimeUtil.getSdfSimpleDate().format(startDate) + "~"
							+ DateTimeUtil.getSdfSimpleDate().format(new Date(endDate.getTime() - 1000));
					result.getDivinationMap().put(xStr, inner.getDivinationTotalMeritValue());
					result.getRuneMap().put(xStr, inner.getRuneTotalMeritValue());
					result.getFlowerMap().put(xStr, inner.getFlowerTotalMeritValue());
					result.getFruitMap().put(xStr, inner.getFruitTotalMeritValue());
					result.getSandalwoodMap().put(xStr, inner.getSandalwoodTotalMeritValue());
				}
			} else if (stageType == 4) {
				// 按月统计
				int forward = 8;
				List<Date> stageList = new ArrayList<>();
				for (int i = 0; i < forward; i++) {
					if (i == 0) {
						Date endDate = new Date();
						Date startDate = DateTimeUtil.getThisMonthDate(endDate);
						stageList.add(endDate);
						stageList.add(startDate);
					} else {
						Date endDate = stageList.get(stageList.size() - 1);
						Date startDate = DateTimeUtil.getThisMonthDate(new Date(endDate.getTime() - 1000));
						stageList.add(endDate);
						stageList.add(startDate);
					}
				}
				for (int i = 0; i < stageList.size(); i += 2) {
					Date startDate = stageList.get(i + 1);
					Date endDate = stageList.get(i);
					StaOrderInfo inner = this.staOrderInfo(null, null, 0,
							DateTimeUtil.getSdfTimestampDate().format(startDate),
							DateTimeUtil.getSdfTimestampDate().format(endDate), 0);
					String xStr = DateTimeUtil.getSdfMonthDate().format(startDate);
					result.getDivinationMap().put(xStr, inner.getDivinationTotalMeritValue());
					result.getRuneMap().put(xStr, inner.getRuneTotalMeritValue());
					result.getFlowerMap().put(xStr, inner.getFlowerTotalMeritValue());
					result.getFruitMap().put(xStr, inner.getFruitTotalMeritValue());
					result.getSandalwoodMap().put(xStr, inner.getSandalwoodTotalMeritValue());
				}
			}
			return result;

		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.PARSE_STRING_TO_DATE_EXCEPTION);
		}
	}

	public List<StaMeritConsumeInfo> staMeritConsumeInfo(String keyword, String tradeType, String registStartTime,
			String registEndTime, Integer registStageType, String tradeStartTime, String tradeEndTime,
			Integer tradeStageType) {
		try {
			String[] tradeTypeCondition = new String[] { "", "" };
			if (tradeType != null && !"".equals(tradeType.trim())) {
				tradeTypeCondition[0] = " and tr.type in (" + tradeType.trim() + ") ";
				tradeTypeCondition[1] = " and t1.type in (" + tradeType.trim() + ") ";
			} else {
				tradeTypeCondition[0] = " and tr.type in (1,2,3,4,5,9) ";
				tradeTypeCondition[1] = " and t1.type in (1,2,3,4,5,9) ";
			}
			String[] keywordCondition = new String[] { "", "" };
			if (keyword != null && !"".equals(keyword.trim())) {
				keywordCondition[0] = " and (u.name like '%" + keyword + "%' or u.phone like '%" + keyword + "%') ";
				keywordCondition[1] = " and (t2.name like '%" + keyword + "%' or t2.phone like '%" + keyword + "%') ";
			}
			String[] registTimeCondition = new String[] { "", "" };
			if (registStageType == null || registStageType == 0) {
				// 注册时间_全部
				if (registStartTime != null && !"".equals(registStartTime)) {
					Date start = DateTimeUtil.getSdfTimestampDate().parse(registStartTime);
					registTimeCondition[0] += String.format(" and u.create_time>='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(start));
					registTimeCondition[1] += String.format(" and t2.create_time>='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(start));
				}
				if (registEndTime != null && !"".equals(registEndTime)) {
					Date end = DateTimeUtil.getSdfTimestampDate().parse(registEndTime);
					registTimeCondition[0] += String.format(" and u.create_time<='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(end));
					registTimeCondition[1] += String.format(" and t2.create_time<='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(end));
				}
			} else if (registStageType == 1 || registStageType == 2) {
				// 注册时间_7天之内，注册时间_1月之内
				Date end = new Date();
				Date start = registStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.getSdfTimestampDate().format(end);
				String startStr = DateTimeUtil.getSdfTimestampDate().format(start);
				registTimeCondition[0] += String.format(" and u.create_time>='%s' and u.create_time<='%s' ", startStr,
						endStr);
				registTimeCondition[1] += String.format(" and t2.create_time>='%s' and t2.create_time<='%s' ", startStr,
						endStr);
			}
			String[] tradeTimeCondition = new String[] { "", "" };
			if (tradeStageType == null || tradeStageType == 0) {
				// 消耗时间_全部
				if (tradeStartTime != null && !"".equals(tradeStartTime)) {
					Date start = DateTimeUtil.getSdfTimestampDate().parse(tradeStartTime);
					tradeTimeCondition[0] += String.format(" and tr.create_time>='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(start));
					tradeTimeCondition[1] += String.format(" and t1.create_time>='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(start));
				}
				if (tradeEndTime != null && !"".equals(tradeEndTime)) {
					Date end = DateTimeUtil.getSdfTimestampDate().parse(tradeEndTime);
					tradeTimeCondition[0] += String.format(" and tr.create_time<='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(end));
					tradeTimeCondition[1] += String.format(" and t1.create_time<='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(end));
				}
			} else if (tradeStageType == 1 || tradeStageType == 2) {
				// 消耗时间_7天之内，登陆时间_1月之内
				Date end = new Date();
				Date start = tradeStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.getSdfTimestampDate().format(end);
				String startStr = DateTimeUtil.getSdfTimestampDate().format(start);
				tradeTimeCondition[0] = String.format(" and tr.create_time>='%s' and tr.create_time<='%s' ", startStr,
						endStr);
				tradeTimeCondition[1] = String.format(" and t1.create_time>='%s' and t1.create_time<='%s' ", startStr,
						endStr);
			}

			String sql = String.format(
					"select t1.user_id, t2.phone, t2.name, t2.gender, t2.birthday, t2.create_time as regist_time, t1.merit_value, t1.type, t1.create_time as consume_time, t3.consume_times from trade t1 "
							+ "LEFT JOIN user t2 on t1.user_id=t2.id "
							+ "LEFT JOIN (select tr.user_id, count(tr.user_id) as consume_times from trade tr LEFT JOIN user u on tr.user_id=u.id where tr.status=1 %s %s %s %s group by tr.user_id) t3 on t1.user_id=t3.user_id "
							+ "where t1.status=1 %s %s %s %s order by t1.create_time desc",
					tradeTypeCondition[0], keywordCondition[0], registTimeCondition[0], tradeTimeCondition[0],
					tradeTypeCondition[1], keywordCondition[1], registTimeCondition[1], tradeTimeCondition[1]);
			Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
			setMethodMap.put(new Integer(0), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(1), new MethodDesc("setPhone", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(2), new MethodDesc("setName", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(3), new MethodDesc("setGender", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(4), new MethodDesc("setBirthday", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(5), new MethodDesc("setRegistTime", new Class<?>[] { Date.class }));
			setMethodMap.put(new Integer(6), new MethodDesc("setMeritValue", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(7), new MethodDesc("setType", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(8), new MethodDesc("setConsumeTime", new Class<?>[] { Date.class }));
			setMethodMap.put(new Integer(9), new MethodDesc("setConsumeTimes", new Class<?>[] { Integer.class }));

			List<StaMeritConsumeInfo> result = dynamicQuerySqlDao.execute(StaMeritConsumeInfo.class, sql, setMethodMap);
			return result;
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.PARSE_STRING_TO_DATE_EXCEPTION);
		}
	}

	public List<StaCheckinInfo> staCheckinInfo(String keyword, Integer gender, String registStartTime,
			String registEndTime, Integer registStageType, String checkinStartTime, String checkinEndTime,
			Integer checkinStageType) {
		try {
			String[] keywordCondition = new String[] { "", "" };
			if (keyword != null && !"".equals(keyword.trim())) {
				keywordCondition[0] = " and (u.name like '%" + keyword + "%' or u.phone like '%" + keyword + "%') ";
				keywordCondition[1] = " and (t2.name like '%" + keyword + "%' or t2.phone like '%" + keyword + "%') ";
			}
			String[] genderCondition = new String[] { "", "" };
			if (gender != null && (1 == gender || 2 == gender)) {
				genderCondition[0] = String.format(" and u.gender=%s ", gender);
				genderCondition[1] = String.format(" and t2.gender=%s ", gender);
			}
			String[] registTimeCondition = new String[] { "", "" };
			if (registStageType == null || registStageType == 0) {
				// 注册时间_全部
				if (registStartTime != null && !"".equals(registStartTime)) {
					Date start = DateTimeUtil.getSdfTimestampDate().parse(registStartTime);
					registTimeCondition[0] += String.format(" and u.create_time>='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(start));
					registTimeCondition[1] += String.format(" and t2.create_time>='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(start));
				}
				if (registEndTime != null && !"".equals(registEndTime)) {
					Date end = DateTimeUtil.getSdfTimestampDate().parse(registEndTime);
					registTimeCondition[0] += String.format(" and u.create_time<='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(end));
					registTimeCondition[1] += String.format(" and t2.create_time<='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(end));
				}
			} else if (registStageType == 1 || registStageType == 2) {
				// 注册时间_7天之内，注册时间_1月之内
				Date end = new Date();
				Date start = registStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.getSdfTimestampDate().format(end);
				String startStr = DateTimeUtil.getSdfTimestampDate().format(start);
				registTimeCondition[0] += String.format(" and u.create_time>='%s' and u.create_time<='%s' ", startStr,
						endStr);
				registTimeCondition[1] += String.format(" and t2.create_time>='%s' and t2.create_time<='%s' ", startStr,
						endStr);
			}
			String[] checkinTimeCondition = new String[] { "", "" };
			if (checkinStageType == null || checkinStageType == 0) {
				// 消耗时间_全部
				if (checkinStartTime != null && !"".equals(checkinStartTime)) {
					Date start = DateTimeUtil.getSdfTimestampDate().parse(checkinStartTime);
					checkinTimeCondition[0] += String.format(" and c.checkin_time>='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(start));
					checkinTimeCondition[1] += String.format(" and t1.checkin_time>='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(start));
				}
				if (checkinEndTime != null && !"".equals(checkinEndTime)) {
					Date end = DateTimeUtil.getSdfTimestampDate().parse(checkinEndTime);
					checkinTimeCondition[0] += String.format(" and c.checkin_time<='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(end));
					checkinTimeCondition[1] += String.format(" and t1.checkin_time<='%s' ",
							DateTimeUtil.getSdfTimestampDate().format(end));
				}
			} else if (checkinStageType == 1 || checkinStageType == 2) {
				// 消耗时间_1天之内，登陆时间_1月之内
				Date end = new Date();
				Date start = checkinStageType == 1 ? DateTimeUtil.getThisDayDate(end)
						: DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.getSdfTimestampDate().format(end);
				String startStr = DateTimeUtil.getSdfTimestampDate().format(start);
				checkinTimeCondition[0] = String.format(" and c.checkin_time>='%s' and c.checkin_time<='%s' ", startStr,
						endStr);
				checkinTimeCondition[1] = String.format(" and t1.checkin_time>='%s' and t1.checkin_time<='%s' ",
						startStr, endStr);
			}

			String sql = String.format(
					"select t1.user_id, t2.phone, t2.name, t2.gender, t2.birthday, t2.create_time AS regist_time, t1.checkin_time, t3.checkin_count from checkin t1 "
							+ "LEFT JOIN user t2 on t1.user_id=t2.id "
							+ "LEFT JOIN (select c.user_id, count(c.user_id) as checkin_count from checkin c  LEFT JOIN user u on c.user_id=u.id where 1=1 %s %s %s %s group by c.user_id) t3 on t1.user_id=t3.user_id "
							+ "where 1=1 %s %s %s %s order by t1.checkin_time desc",
					keywordCondition[0], genderCondition[0], registTimeCondition[0], checkinTimeCondition[0],
					keywordCondition[1], genderCondition[1], registTimeCondition[1], checkinTimeCondition[1]);
			Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
			setMethodMap.put(new Integer(0), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(1), new MethodDesc("setPhone", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(2), new MethodDesc("setName", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(3), new MethodDesc("setGender", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(4), new MethodDesc("setBirthday", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(5), new MethodDesc("setRegistTime", new Class<?>[] { Date.class }));
			setMethodMap.put(new Integer(6), new MethodDesc("setCheckinTime", new Class<?>[] { Date.class }));
			setMethodMap.put(new Integer(7), new MethodDesc("setCheckinCount", new Class<?>[] { Integer.class }));

			List<StaCheckinInfo> result = dynamicQuerySqlDao.execute(StaCheckinInfo.class, sql, setMethodMap);
			return result;
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.PARSE_STRING_TO_DATE_EXCEPTION);
		}
	}

}
