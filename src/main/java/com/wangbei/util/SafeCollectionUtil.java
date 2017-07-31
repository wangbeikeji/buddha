package com.wangbei.util;

import com.wangbei.pojo.ValidateCode;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
* @author yuyidi 2017-07-11 13:43:04
* @class com.wangbei.util.SafeCollectionUtil
* @description 安全的集合工具类
*/
public class SafeCollectionUtil {

    private static ConcurrentHashMap<String, ValidateCode> validateCodes = new ConcurrentHashMap<>(1000);


    public static ValidateCode saveValidateCode(String phone,Integer code){
        DateTime dt = new DateTime();
        DateTime expire = dt.plusMinutes(5);
        ValidateCode vc = new ValidateCode(phone,code,expire.toDate());
        validateCodes.put(phone, vc);
        return vc;
    }

    public static ValidateCode getValidateCode(String phone){
        ValidateCode vc = validateCodes.get(phone);
        if(vc != null){
            if (vc.getExpire().getTime() - System.currentTimeMillis() > 0) {
                return vc;
            }
            return null;
        }
        return null;
    }

    public static void removeValidateCode(String phone) {
        validateCodes.remove(phone);
    }

}
