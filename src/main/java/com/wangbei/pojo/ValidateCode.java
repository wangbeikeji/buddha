package com.wangbei.pojo;

import java.util.Date;

/**
* @author yuyidi 2017-07-11 13:45:07
* @class com.wangbei.pojo.ValidateCode
* @description 验证码
*/
public class ValidateCode {
    //手机号
    private String phone;
    //验证码
    private Integer code;
    //过期时间
    private Date expire;

    public ValidateCode(String phone, Integer code, Date expire) {
        this.phone = phone;
        this.code = code;
        this.expire = expire;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }
}
