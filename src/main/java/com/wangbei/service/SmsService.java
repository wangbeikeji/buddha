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

	public ValidateCode sendAuthCode(String phoneNumber) {
		ValidateCode reuslt = null;
		ValidateCode validateCode = SafeCollectionUtil.getValidateCode(phoneNumber);
		if (validateCode == null) {
			SmsDao smsDao = BusinessFactory.getSmsDao(smsType);
			SendAuthCodeResult sacr = smsDao.sendAuthCode(new String[] { phoneNumber });
			if (sacr.getCode() == 1000) {
				reuslt = SafeCollectionUtil.saveValidateCode(phoneNumber,Integer.valueOf(sacr.getValidationCode()));
			}
		}
		return reuslt;
	}

}
