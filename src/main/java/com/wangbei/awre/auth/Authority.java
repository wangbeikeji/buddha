package com.wangbei.awre.auth;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author yuyidi 2017-07-08 19:59:49
 * @class com.wangbei.awre.auth.Authority
 * @description 授权对象
 */
public class Authority implements GrantedAuthority {
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
