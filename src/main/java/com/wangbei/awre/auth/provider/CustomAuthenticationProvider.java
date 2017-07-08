package com.wangbei.awre.auth.provider;

import com.wangbei.awre.auth.Authority;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.thymeleaf.extras.springsecurity4.auth.Authorization;

import java.util.ArrayList;
import java.util.List;

/**
* @author yuyidi 2017-07-08 19:54:13
* @class com.wangbei.awre.auth.provider.CustomAuthenticationProvider
* @description 自定义实现认证器
*/
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        //TODO 认证逻辑
        if (name.equals("admin") && password.equals("123456")) {
            List<GrantedAuthority> authorization = new ArrayList<>();
            authorization.add(new Authority("ROLE_ADMIN"));
            authorization.add(new Authority("AUTH_WRITE"));
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorization);
            return auth;
        }else{
            //TODO 抛出认证用户名或密码异常
        }
        return null;
    }

    /**
    * @author yuyidi 2017-07-08 20:07:15
    * @method supports
    * @param authentication
    * @return boolean
    * @description 认证器是否提供输入类型的认证服务  用户名密码的方式
    */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
