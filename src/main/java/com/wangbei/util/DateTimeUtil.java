package com.wangbei.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

public class DateTimeUtil {

	public static ThreadLocal<SimpleDateFormat> sdfDate = new ThreadLocal<>();
	public static ThreadLocal<SimpleDateFormat> sdfSimpleDate = new ThreadLocal<>();
	public static ThreadLocal<SimpleDateFormat> sdfMonthDate = new ThreadLocal<>();
	public static ThreadLocal<SimpleDateFormat> sdfHourDate = new ThreadLocal<>();
	public static ThreadLocal<SimpleDateFormat> sdfDayDate = new ThreadLocal<>();
	public static ThreadLocal<SimpleDateFormat> sdfTimestampDate = new ThreadLocal<>();

	static {
		sdfDate.set(new SimpleDateFormat("yyyy-MM-dd"));
		sdfSimpleDate.set(new SimpleDateFormat("yyyyMMdd"));
		sdfMonthDate.set(new SimpleDateFormat("yyyy年MM月"));
		sdfHourDate.set(new SimpleDateFormat("dd日HH时"));
		sdfDayDate.set(new SimpleDateFormat("yyyy年MM月dd日"));
		sdfTimestampDate.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	public static SimpleDateFormat getSdfDate() {
		SimpleDateFormat result = sdfDate.get();
		if (result == null) {
			result = new SimpleDateFormat("yyyy-MM-dd");
			sdfDate.set(result);
		}
		return result;
	}

	public static SimpleDateFormat getSdfSimpleDate() {
		SimpleDateFormat result = sdfSimpleDate.get();
		if (result == null) {
			result = new SimpleDateFormat("yyyyMMdd");
			sdfSimpleDate.set(result);
		}
		return result;
	}

	public static SimpleDateFormat getSdfMonthDate() {
		SimpleDateFormat result = sdfMonthDate.get();
		if (result == null) {
			result = new SimpleDateFormat("yyyy年MM月");
			sdfMonthDate.set(result);
		}
		return result;
	}

	public static SimpleDateFormat getSdfHourDate() {
		SimpleDateFormat result = sdfHourDate.get();
		if (result == null) {
			result = new SimpleDateFormat("dd日HH时");
			sdfHourDate.set(result);
		}
		return result;
	}

	public static SimpleDateFormat getSdfDayDate() {
		SimpleDateFormat result = sdfDayDate.get();
		if (result == null) {
			result = new SimpleDateFormat("yyyy年MM月dd日");
			sdfDayDate.set(result);
		}
		return result;
	}

	public static SimpleDateFormat getSdfTimestampDate() {
		SimpleDateFormat result = sdfTimestampDate.get();
		if (result == null) {
			result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdfTimestampDate.set(result);
		}
		return result;
	}

	/**
	 * 获取下个月的第一天
	 */
	public static Date getNextMonthDate(Date dt) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return getSdfDate().parse(getSdfDate().format(cal.getTime()));
	}

	/**
	 * 获取这个月的第一天
	 */
	public static Date getThisMonthDate(Date dt) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return getSdfDate().parse(getSdfDate().format(cal.getTime()));
	}

	/**
	 * 获取下一天
	 */
	public static Date getNextDate(Date dt) throws ParseException {
		return new DateTime(getSdfDate().parse(getSdfDate().format(dt))).plusHours(24).toDate();
	}

	/**
	 * 获取本周的星期一
	 */
	public static Date getMonday(Date dt) throws ParseException {
		Integer weekDay = getWeekOfDate(dt);
		if (weekDay == 1) {
			return getSdfDate().parse(getSdfDate().format(dt));
		} else {
			DateTime dtTime = new DateTime(dt);
			return getSdfDate().parse(getSdfDate().format(dtTime.minusHours((weekDay - 1) * 24).toDate()));
		}
	}

	/**
	 * 获取当天是星期几
	 */
	public static Integer getWeekOfDate(Date dt) {
		Integer[] weekDays = { 7, 1, 2, 3, 4, 5, 6 };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 获取7天前的日期
	 */
	public static Date getDaysAgo(Date dt, int days) throws ParseException {
		return new DateTime(getSdfDate().parse(getSdfDate().format(dt))).minusHours((days - 1) * 24).toDate();
	}

	public static Date getThisDayDate(Date dt) throws ParseException {
		return getSdfDate().parse(getSdfDate().format(dt));
	}

	/**
	 * 获取一个月前的日期
	 */
	public static Date getMonthsAgo(Date dt, int months) throws ParseException {
		Date today = getSdfDate().parse(getSdfDate().format(dt));
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - months);
		return cal.getTime();
	}

	/**
	 * 获取多少小时前的开始时间
	 */
	public static Date getBeforeHourDate(Date dt, int hours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - hours);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

}
