package com.wangbei.awre;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.wangbei.dao.SmsDao;
import com.wangbei.util.SmsTypeEnum;

/**
 * 短信dao工厂类
 * 
 * @author luomengan
 *
 */
@Component
public class BusinessFactory implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	private static Map<SmsTypeEnum, SmsDao> smsDaoMap = new HashMap<>();;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		initSmsDaoMap();
	}

	private void initSmsDaoMap() {
		Map<String, SmsDao> map = applicationContext.getBeansOfType(SmsDao.class);
		for (Map.Entry<String, SmsDao> entry : map.entrySet()) {
			SmsDao smsDao = entry.getValue();
			smsDaoMap.put(smsDao.getSmsType(), smsDao);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends SmsDao> T getSmsDao(SmsTypeEnum smsType) {
		return (T) smsDaoMap.get(smsType);
	}
}
