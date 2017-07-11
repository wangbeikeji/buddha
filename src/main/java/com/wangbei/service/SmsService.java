package com.wangbei.service;

import com.wangbei.pojo.ValidateCode;
import com.wangbei.util.SafeCollectionUtil;
import org.springframework.stereotype.Service;

import com.wangbei.awre.BusinessFactory;
import com.wangbei.dao.SmsDao;
import com.wangbei.pojo.SendAuthCodeResult;
import com.wangbei.util.enums.SmsTypeEnum;

@Service
public class SmsService {

	private SmsTypeEnum smsType = SmsTypeEnum.Ali;

	public SendAuthCodeResult sendAuthCode(String phoneNumber) {
		SendAuthCodeResult sacr = null;
		ValidateCode validateCode = SafeCollectionUtil.getValidateCode(phoneNumber);
		if (validateCode == null) {
			SmsDao smsDao = BusinessFactory.getSmsDao(smsType);
			sacr = smsDao.sendAuthCode(new String[] { phoneNumber });
			if (sacr.getCode() == 1000) {
				SafeCollectionUtil.saveValidateCode(phoneNumber,Integer.valueOf(sacr.getValidationCode()));
			}
		}
		return sacr;
	}

}
