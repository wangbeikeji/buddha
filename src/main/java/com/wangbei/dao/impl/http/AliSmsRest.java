package com.wangbei.dao.impl.http;

import com.wangbei.dao.SmsDao;
import com.wangbei.pojo.AliSmsParameter;
import com.wangbei.pojo.AliSmsResult;
import com.wangbei.pojo.SmsParameter;
import com.wangbei.pojo.SmsResult;
import org.springframework.stereotype.Component;

/**
* @author yuyidi 2017-07-06 13:49:06
* @class com.wangbei.dao.impl.http.AliSmsRest
* @description 短信http请求实现
*/
@Component
public class AliSmsRest extends HttpRest<AliSmsParameter> implements SmsDao{

    @Override
    public SmsResult sendAuthCode(SmsParameter parameter) {
        return doPostObject("",parameter, AliSmsResult.class);
    }
}
