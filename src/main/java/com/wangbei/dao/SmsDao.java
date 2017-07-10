package com.wangbei.dao;

import com.wangbei.pojo.SendAuthCodeResult;
import com.wangbei.util.SmsTypeEnum;

/**
 * @author yuyidi 2017-07-06 13:55:19
 * @class com.wangbei.dao.SmsDao
 * @description 短信接口
 */
public interface SmsDao {

	/**
	 * 获取短信供应商类型
	 * 
	 * @return 短信供应商类型
	 */
	SmsTypeEnum getSmsType();

	/**
	 * 发送验证码
	 * 
	 * @param phoneNumbers
	 *            手机号数组
	 * @return
	 */
	public SendAuthCodeResult sendAuthCode(String[] phoneNumbers);
}
