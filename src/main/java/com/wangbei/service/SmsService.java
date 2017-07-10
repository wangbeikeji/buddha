package com.wangbei.service;

import org.springframework.stereotype.Service;

import com.wangbei.awre.BusinessFactory;
import com.wangbei.dao.SmsDao;
import com.wangbei.pojo.SendAuthCodeResult;
import com.wangbei.util.enums.SmsTypeEnum;

@Service
public class SmsService {

	private SmsTypeEnum smsType = SmsTypeEnum.Ali;

	public SendAuthCodeResult sendAuthCode(String[] phoneNumbers) {
		SmsDao smsDao = BusinessFactory.getSmsDao(smsType);
		return smsDao.sendAuthCode(phoneNumbers);
	}

}
