package com.wangbei.dao;

import com.wangbei.pojo.SmsParameter;
import com.wangbei.pojo.SmsResult;

/**
* @author yuyidi 2017-07-06 13:55:19
* @class com.wangbei.dao.SmsDao
* @description 短信接口
*/
public interface SmsDao {

    /**
    * @author yuyidi 2017-07-06 14:00:42
    * @method sendAuthCode
    * @param parameter
    * @return com.wangbei.pojo.SmsResult
    * @description 发送验证码
    */
    public SmsResult sendAuthCode(SmsParameter parameter);
}
