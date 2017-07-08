package com.wangbei.awre.auth;

import java.io.Serializable;

/**
* @author yuyidi 2017-07-08 20:23:36
* @class com.wangbei.awre.auth.AccountCredentials
* @description 账户凭证
*/
public class AccountCredentials implements Serializable{

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
