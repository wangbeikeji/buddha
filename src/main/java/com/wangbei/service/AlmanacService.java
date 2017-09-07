package com.wangbei.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.AlmanacDao;
import com.wangbei.entity.Almanac;
import com.wangbei.pojo.AlmanacInfo;
import com.wangbei.service.wxpay.api.HttpService;

/**
 * 黄历+佛历 Service
 * 
 * @author luomengan
 *
 */
@Service
public class AlmanacService {

	/**
	 * 黄历数据源地址
	 */
	private static final String almanacSourceUrl = "http://www.4qx.net/LaoHuangLi_JiRi_1.php";

	/**
	 * 获取最近年份的佛历数据_开始年份
	 */
	private int startRecentYear = 2015;

	/**
	 * 获取最近年份的佛历数据_结束年份
	 */
	private int endRecentYear = 2025;

	@Autowired
	private AlmanacDao almanacDao;

	/**
	 * 采集数据
	 */
	public void collectAlmanacData(int startYear, int endYear) {
		for (int year = startYear; year <= endYear; year++) {
			for (int month = 1; month <= 12; month++) {
				int maxDay = AlmanacService.getMaxDayByYearMonth(year, month);
				for (int day = 1; day <= maxDay; day++) {
					Almanac almanac = getAlmanac(year, month, day);
					if (almanac != null) {
						almanacDao.createAlmanac(almanac);
					}
				}
			}
		}
	}
	
	public Almanac getAlmanacByGregorianCalendar(String gregorianCalendar) {
		return almanacDao.retrieveAlmanacByGregorianCalendar(gregorianCalendar);
	}

	private Almanac getAlmanac(int year, int month, int day) {
		Almanac result = null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("year", year);
		paramMap.put("month", month);
		paramMap.put("day", day);
		try {
			String html = HttpService.sendPostParamForHtml(almanacSourceUrl, paramMap);
			int huangliIndex = html.indexOf("<div class=\"main_huangli_1\">");
			if (huangliIndex > 0) {
				String gregorianCalendar = String.valueOf(year)
						+ (String.valueOf(month).length() < 2 ? ("0" + String.valueOf(month)) : String.valueOf(month))
						+ (String.valueOf(day).length() < 2 ? ("0" + String.valueOf(day)) : String.valueOf(day));
				result = almanacDao.retrieveAlmanacByGregorianCalendar(gregorianCalendar);
				if (result == null) {
					result = new Almanac();
					result.setGregorianCalendar(gregorianCalendar);
				}
				String huangliStr = html.substring(huangliIndex);
				huangliStr = huangliStr
						.substring("<div class=\"main_huangli_1\">".length(), huangliStr.indexOf("</div>")).trim();
				String[] huangliArr = huangliStr.split("</li>");
				StringBuilder overview = new StringBuilder();
				for (int i = 0; i < huangliArr.length; i++) {
					if (huangliArr[i] != null && !"".equals(huangliArr[i].trim())) {
						overview.append(huangliArr[i].trim().substring(4) + "\n");
					}
					String inner = null;
					if (i == 0) {
						// 公历
						inner = huangliArr[i].trim().substring(9);
					} else if (i == 1) {
						// 农历
						inner = huangliArr[i].trim().substring(9);
						if (inner != null && !"".equals(inner) && inner.length() > 10) {
							String lunar = inner.substring(2, 4) + inner.substring(8, 10);
							// 根据农历日期获取佛历信息
							result.setBuddhistCalendar(BuddhistCalendarService.buddhistCalMap.get(lunar));
						}
						result.setLunarCalendar(inner);
					} else if (i == 2) {
						// 岁次
						inner = huangliArr[i].trim().substring(9);
						result.setYearName(inner);
					} else if (i == 3) {
						// 每日胎神占方
						inner = huangliArr[i].trim().substring(13);
						result.setTaishenSide(inner);
					} else if (i == 4) {
						// 五行
						inner = huangliArr[i].trim().substring(9);
						result.setFiveElements(inner);
					} else if (i == 5) {
						// 冲
						inner = huangliArr[i].trim().substring(8);
						result.setPunching(inner);
					} else if (i == 6) {
						// 彭祖百忌
						inner = huangliArr[i].trim().substring(11);
						result.setPengzuBogey(inner);
					} else if (i == 7) {
						// 吉神宜趋
						inner = huangliArr[i].trim().substring(11);
						result.setJishenShouldTrend(inner);
					} else if (i == 8) {
						// 宜
						inner = huangliArr[i].trim().substring(8);
						result.setShould(inner);
					} else if (i == 9) {
						// 凶神宜忌
						inner = huangliArr[i].trim().substring(11);
						result.setXiongshenShouldBogey(inner);
					} else if (i == 10) {
						// 忌
						inner = huangliArr[i].trim().substring(8);
						result.setBogey(inner);
					}
				}
				result.setOverview(overview.toString());
			}
		} catch (IOException e) {
			throw new RuntimeException(String.format("抓取[%s-%s-%s]数据失败，%s", year, month, day, almanacSourceUrl));
		}
		return result;
	}

	/**
	 * 获得某个月最大天数
	 *
	 * @param year
	 *            年份
	 * @param month
	 *            月份 (1-12)
	 * @return 某个月最大天数
	 */
	public static int getMaxDayByYearMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		return calendar.getActualMaximum(Calendar.DATE);
	}

	public Almanac getAlmanacInfo(Integer id) {
		return almanacDao.retrieveAlmanacById(id);
	}

	@Transactional
	public Almanac addAlmanac(Almanac almanac) {
		return almanacDao.createAlmanac(almanac);
	}

	@Transactional
	public Almanac modifyAlmanac(Almanac almanac) {
		return almanacDao.updateAlmanac(almanac);
	}

	@Transactional
	public void deleteAlmanac(Integer id) {
		almanacDao.deleteAlmanacById(id);
	}

	public Page<Almanac> almanacs(int page, int limit) {
		return almanacDao.pageAlmanac(page, limit);
	}

	public List<Almanac> list() {
		return almanacDao.listAlmanac();
	}

	public Map<String, AlmanacInfo> getRecentYearData() {
		Map<String, AlmanacInfo> result = new HashMap<>();
		List<Almanac> list = almanacDao.listAlmanacBetweenYear(startRecentYear, endRecentYear);
		if (list != null && list.size() > 0) {
			for (Almanac almanac : list) {
				result.put(almanac.getGregorianCalendar(), new AlmanacInfo(almanac));
			}
		}
		return result;
	}

}
