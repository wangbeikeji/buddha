package com.wangbei.service;

import com.wangbei.dao.UserDao;
import com.wangbei.entity.User;
import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.ValidateCode;
import com.wangbei.util.SafeCollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangbei.awre.SmsDaoFactory;
import com.wangbei.dao.SmsDao;
import com.wangbei.pojo.SendAuthCodeResult;
import com.wangbei.util.enums.SmsTypeEnum;

@Service
public class SmsService {

	private SmsTypeEnum smsType = SmsTypeEnum.Ali;

	@Autowired
	private UserDao userDao;

	public SendAuthCodeResult sendAuthCode(String phoneNumber) {
		SendAuthCodeResult sacr = null;
		ValidateCode validateCode = SafeCollectionUtil.getValidateCode(phoneNumber);
		if (validateCode == null) {
			User checkUser = userDao.fetchUserByPhone(phoneNumber );
			if (checkUser != null) {
				throw new ServiceException(ServiceException.USER_REGISTER_EXIST_EXCEPTION);
			}
			SmsDao smsDao = SmsDaoFactory.getSmsDao(smsType);
			sacr = smsDao.sendAuthCode(new String[] { phoneNumber });
			if (sacr.getCode() == 1000) {
				SafeCollectionUtil.saveValidateCode(phoneNumber,Integer.valueOf(sacr.getValidationCode()));
			}
		}else{
			//已经发送验证码且未过期,则提示请勿重复发送验证码
			throw new ServiceException(ServiceException.VALIDATECODE_REPEAD_EXCEPTION);
		}
		return sacr;
	}

}
