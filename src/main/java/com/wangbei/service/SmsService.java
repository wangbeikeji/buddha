package com.wangbei.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangbei.awre.SmsDaoFactory;
import com.wangbei.dao.SmsDao;
import com.wangbei.dao.UserDao;
import com.wangbei.entity.User;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.SendAuthCodeResult;
import com.wangbei.pojo.ValidateCode;
import com.wangbei.util.SafeCollectionUtil;
import com.wangbei.util.enums.AuthCodeTypeEnum;
import com.wangbei.util.enums.SmsTypeEnum;

@Service
public class SmsService {
	
	@Autowired
	private UserDao userDao;

	private SmsTypeEnum smsType = SmsTypeEnum.Ali;

	public SendAuthCodeResult sendAuthCode(String phoneNumber) {
		SendAuthCodeResult sacr = null;
		ValidateCode validateCode = SafeCollectionUtil.getValidateCode(phoneNumber);
		Date now = new Date();
		if (validateCode == null || (now.getTime() - validateCode.getCreate().getTime()) >= 30 * 1000) {
			SmsDao smsDao = SmsDaoFactory.getSmsDao(smsType);
			sacr = smsDao.sendAuthCode(new String[] { phoneNumber });
			if (sacr.getCode() == 1000) {
				SafeCollectionUtil.saveValidateCode(phoneNumber, Integer.valueOf(sacr.getValidationCode()));
			} else if(sacr.getCode() == 1002){
				throw new ServiceException(ExceptionEnum.USER_PHONE_INVALID_EXCEPTION);
			}
		} else {
			// 已经发送验证码且未过期,则提示请勿重复发送验证码
			throw new ServiceException(ExceptionEnum.VALIDATECODE_REPEAD_EXCEPTION);
		}
		return sacr;
	}

	public SendAuthCodeResult sendAuthCode(String phone, AuthCodeTypeEnum type) {
		User user = userDao.fetchUserByPhone(phone);
		if(type == AuthCodeTypeEnum.REGISTER) {
			if(user != null) {
				throw new ServiceException(ExceptionEnum.USER_REGISTER_EXIST_EXCEPTION);
			}
		} else if(type == AuthCodeTypeEnum.RESETPASSWORD) {
			if(user == null) {
				throw new ServiceException(ExceptionEnum.USER_PHONE_NOTEXIST_EXCEPTION);
			}
		}
		return sendAuthCode(phone);
	}

}
