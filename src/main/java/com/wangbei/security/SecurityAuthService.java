package com.wangbei.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
* @author yuyidi 2017-07-14 17:20:15
* @class com.wangbei.security.SecurityAuthService
* @description 认证对象服务
*/
public class SecurityAuthService {

    public static AuthUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUserDetails authUserDetails = (AuthUserDetails) authentication.getDetails();
        return authUserDetails;
    }
}
