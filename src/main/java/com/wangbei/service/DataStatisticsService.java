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

	public Map<String, Double> staDayAmountOfCharge(String dayStr, int forward) {
		try {
			Map<String, Double> result = new TreeMap<>();
			Date staDate = DateTimeUtil.sdfDate.parse(dayStr);
			for (int i = 0; i < forward; i++) {
				if (i == 0) {
					Double amount = dayAmountOfCharge(DateTimeUtil.sdfDate.format(staDate));
					String key = DateTimeUtil.sdfDayDate.format(staDate);
					result.put(key, amount == null ? 0 : amount);
				} else {
					Date beforeStaDate = new DateTime(staDate).minusHours(i * 24).toDate();
					Double amount = dayAmountOfCharge(DateTimeUtil.sdfDate.format(beforeStaDate));
					String key = DateTimeUtil.sdfDayDate.format(beforeStaDate);
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
			Date staDate = DateTimeUtil.sdfDate.parse(dayStr);
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
						DateTimeUtil.sdfSimpleDate.format(startDate) + "~"
								+ DateTimeUtil.sdfSimpleDate.format(new Date(endDate.getTime() - 1000)),
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
			Date staDate = DateTimeUtil.sdfDate.parse(dayStr);
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
				result.put(DateTimeUtil.sdfMonthDate.format(startDate), value == null ? 0 : value);
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
					Date start = DateTimeUtil.sdfTimestampDate.parse(registStartTime);
					registTimeCondition += String.format(" and t4.create_time>='%s' ",
							DateTimeUtil.sdfTimestampDate.format(start));
				}
				if (registEndTime != null && !"".equals(registEndTime)) {
					Date end = DateTimeUtil.sdfTimestampDate.parse(registEndTime);
					registTimeCondition += String.format(" and t4.create_time<='%s' ",
							DateTimeUtil.sdfTimestampDate.format(end));
				}
			} else if (registStageType == 1 || registStageType == 2) {
				// 注册时间_7天之内，注册时间_1月之内
				Date end = new Date();
				Date start = registStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.sdfTimestampDate.format(end);
				String startStr = DateTimeUtil.sdfTimestampDate.format(start);
				registTimeCondition = String.format(" and t4.create_time>='%s' and t4.create_time<='%s' ", startStr,
						endStr);
			}
			String loginTimeCondition = "";
			if (newestLoginStageType == null || newestLoginStageType == 0) {
				// 登陆时间_全部
				if (newestLoginStartTime != null && !"".equals(newestLoginStartTime)) {
					Date start = DateTimeUtil.sdfTimestampDate.parse(newestLoginStartTime);
					loginTimeCondition += String.format(" and t4.login_time>='%s' ",
							DateTimeUtil.sdfTimestampDate.format(start));
				}
				if (newestLoginEndTime != null && !"".equals(newestLoginEndTime)) {
					Date end = DateTimeUtil.sdfTimestampDate.parse(newestLoginEndTime);
					loginTimeCondition += String.format(" and t4.login_time<='%s' ",
							DateTimeUtil.sdfTimestampDate.format(end));
				}
			} else if (newestLoginStageType == 1 || newestLoginStageType == 2) {
				// 登陆时间_7天之内，登陆时间_1月之内
				Date end = new Date();
				Date start = registStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.sdfTimestampDate.format(end);
				String startStr = DateTimeUtil.sdfTimestampDate.format(start);
				loginTimeCondition = String.format(" and t4.login_time>='%s' and t4.login_time<='%s' ", startStr,
						endStr);
			}

			String sql = String.format(
					"select t4.id, t4.name, t4.phone, t4.gender, t4.birthday, t4.address, t4.create_time as registTime, t4.login_time as loginTime, IFNULL(t5.user_charge, 0) as userCharge from (select * from (select t1.*, t2.login_time from user t1 LEFT JOIN user_login_log t2 on t1.id=t2.user_id ORDER BY t1.id asc, t2.login_time desc) t3 GROUP BY t3.id) t4 "
							+ "LEFT JOIN (select user_id, sum(total_amount) as user_charge from orders where status=2 and create_time>='1970-01-01 00:00:00' and create_time<'2200-12-31 00:00:00' group by user_id) t5 on t4.id=t5.user_id "
							+ "where 1=1 %s %s %s %s;",
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
			Integer chargeStageType) {
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
			String registTimeCondition = "";
			if (registStageType == null || registStageType == 0) {
				// 注册时间_全部
				if (registStartTime != null && !"".equals(registStartTime)) {
					Date start = DateTimeUtil.sdfTimestampDate.parse(registStartTime);
					registTimeCondition += String.format(" and t3.create_time>='%s' ",
							DateTimeUtil.sdfTimestampDate.format(start));
				}
				if (registEndTime != null && !"".equals(registEndTime)) {
					Date end = DateTimeUtil.sdfTimestampDate.parse(registEndTime);
					registTimeCondition += String.format(" and t3.create_time<='%s' ",
							DateTimeUtil.sdfTimestampDate.format(end));
				}
			} else if (registStageType == 1 || registStageType == 2) {
				// 注册时间_7天之内，注册时间_1月之内
				Date end = new Date();
				Date start = registStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.sdfTimestampDate.format(end);
				String startStr = DateTimeUtil.sdfTimestampDate.format(start);
				registTimeCondition = String.format(" and t3.create_time>='%s' and t3.create_time<='%s' ", startStr,
						endStr);
			}
			String chargeTimeCondition = "";
			if (chargeStageType == null || chargeStageType == 0) {
				// 充值时间_全部
				if (chargeStartTime != null && !"".equals(chargeStartTime)) {
					Date start = DateTimeUtil.sdfTimestampDate.parse(chargeStartTime);
					chargeTimeCondition += String.format(" and t1.modify_time>='%s' ",
							DateTimeUtil.sdfTimestampDate.format(start));
				}
				if (chargeEndTime != null && !"".equals(chargeEndTime)) {
					Date end = DateTimeUtil.sdfTimestampDate.parse(chargeEndTime);
					chargeTimeCondition += String.format(" and t1.modify_time<='%s' ",
							DateTimeUtil.sdfTimestampDate.format(end));
				}
			} else if (chargeStageType == 1 || chargeStageType == 2) {
				// 充值时间_7天之内，充值时间_1月之内
				Date end = new Date();
				Date start = registStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.sdfTimestampDate.format(end);
				String startStr = DateTimeUtil.sdfTimestampDate.format(start);
				chargeTimeCondition = String.format(" and t1.modify_time>='%s' and t1.modify_time<='%s' ", startStr,
						endStr);
			}

			String sql = String.format(
					"select t1.user_id as userId, t3.name, t3.phone, t3.gender, t3.create_time as registTime, t1.trade_no as tradeNo, "
							+ "t1.total_amount as chargeAmount, t2.type, t1.payment_type as paymentType, t2.merit_value as meritValue, t1.modify_time as chargeTime from orders t1 "
							+ "LEFT JOIN trade t2 on t1.trade_no=t2.trade_no LEFT JOIN user t3 on t1.user_id=t3.id "
							+ "where t1.status=2 %s %s %s %s;",
					keywordCondition, chargeTypeCondition, registTimeCondition, chargeTimeCondition);
			Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
			setMethodMap.put(new Integer(0), new MethodDesc("setUserId", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(1), new MethodDesc("setName", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(2), new MethodDesc("setPhone", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(3), new MethodDesc("setGender", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(4), new MethodDesc("setRegistTime", new Class<?>[] { Date.class }));
			setMethodMap.put(new Integer(5), new MethodDesc("setTradeNo", new Class<?>[] { String.class }));
			setMethodMap.put(new Integer(6), new MethodDesc("setChargeAmount", new Class<?>[] { Double.class }));
			setMethodMap.put(new Integer(7), new MethodDesc("setType", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(8), new MethodDesc("setPaymentType", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(9), new MethodDesc("setMeritValue", new Class<?>[] { Integer.class }));
			setMethodMap.put(new Integer(10), new MethodDesc("setChargeTime", new Class<?>[] { Date.class }));
			List<StaChargeInfo> result = dynamicQuerySqlDao.execute(StaChargeInfo.class, sql, setMethodMap);
			return result;
		} catch (ParseException e) {
			throw new ServiceException(ExceptionEnum.PARSE_STRING_TO_DATE_EXCEPTION);
		}
	}

	public StaOrderInfo staOrderInfo(String registStartTime, String registEndTime, Integer registStageType,
			String orderStartTime, String orderEndTime, Integer orderStageType) {
		try {
			StaOrderInfo result = new StaOrderInfo();
			// step 1 : 查询香、花、果的总功德数
			String staCountMeritValueSql = "select sum(t1.merit_value) from trade t1 LEFT JOIN user t2 on t1.user_id=t2.id where t1.status=1 %s %s %s";
			String registTimeCondition = "";
			if (registStageType == null || registStageType == 0) {
				// 注册时间_全部
				if (registStartTime != null && !"".equals(registStartTime)) {
					Date start = DateTimeUtil.sdfTimestampDate.parse(registStartTime);
					registTimeCondition += String.format(" and t2.create_time>='%s' ",
							DateTimeUtil.sdfTimestampDate.format(start));
				}
				if (registEndTime != null && !"".equals(registEndTime)) {
					Date end = DateTimeUtil.sdfTimestampDate.parse(registEndTime);
					registTimeCondition += String.format(" and t2.create_time<='%s' ",
							DateTimeUtil.sdfTimestampDate.format(end));
				}
			} else if (registStageType == 1 || registStageType == 2) {
				// 注册时间_7天之内，注册时间_1月之内
				Date end = new Date();
				Date start = registStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.sdfTimestampDate.format(end);
				String startStr = DateTimeUtil.sdfTimestampDate.format(start);
				registTimeCondition = String.format(" and t2.create_time>='%s' and t2.create_time<='%s' ", startStr,
						endStr);
			}
			String orderTimeCondition = "";
			if (orderStageType == null || orderStageType == 0) {
				// 下单时间_全部
				if (orderStartTime != null && !"".equals(orderStartTime)) {
					Date start = DateTimeUtil.sdfTimestampDate.parse(orderStartTime);
					orderTimeCondition += String.format(" and t1.create_time>='%s' ",
							DateTimeUtil.sdfTimestampDate.format(start));
				}
				if (orderEndTime != null && !"".equals(orderEndTime)) {
					Date end = DateTimeUtil.sdfTimestampDate.parse(orderEndTime);
					orderTimeCondition += String.format(" and t1.create_time<='%s' ",
							DateTimeUtil.sdfTimestampDate.format(end));
				}
			} else if (orderStageType == 1 || orderStageType == 2) {
				// 下单时间_7天之内，下单时间_1月之内
				Date end = new Date();
				Date start = orderStageType == 1 ? DateTimeUtil.getDaysAgo(end, 7) : DateTimeUtil.getMonthsAgo(end, 1);
				String endStr = DateTimeUtil.sdfTimestampDate.format(end);
				String startStr = DateTimeUtil.sdfTimestampDate.format(start);
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
							DateTimeUtil.sdfTimestampDate.format(startDate),
							DateTimeUtil.sdfTimestampDate.format(endDate), 0);
					String xStr = DateTimeUtil.sdfHourDate.format(startDate);
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
						Date startDate = DateTimeUtil.sdfDate.parse(DateTimeUtil.sdfDate.format(endDate));
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
							DateTimeUtil.sdfTimestampDate.format(startDate),
							DateTimeUtil.sdfTimestampDate.format(endDate), 0);
					String xStr = DateTimeUtil.sdfDayDate.format(startDate);
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
							DateTimeUtil.sdfTimestampDate.format(startDate),
							DateTimeUtil.sdfTimestampDate.format(endDate), 0);
					String xStr = DateTimeUtil.sdfSimpleDate.format(startDate) + "~"
							+ DateTimeUtil.sdfSimpleDate.format(new Date(endDate.getTime() - 1000));
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
							DateTimeUtil.sdfTimestampDate.format(startDate),
							DateTimeUtil.sdfTimestampDate.format(endDate), 0);
					String xStr = DateTimeUtil.sdfMonthDate.format(startDate);
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

}
