package com.wangbei.awre.auth.filter;

import com.wangbei.awre.auth.jwt.TokenAuthenicationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
* @author yuyidi 2017-07-08 21:03:32
* @class com.wangbei.awre.auth.filter.JWTAuthenticationFilter
* @description JWT 认证token过滤器
*/
public class JWTAuthenticationFilter extends GenericFilterBean{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = TokenAuthenicationService.validateAuthentication((HttpServletRequest) request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);
    }
}
