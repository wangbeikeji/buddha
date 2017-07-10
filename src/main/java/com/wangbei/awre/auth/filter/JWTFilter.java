package com.wangbei.awre.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangbei.awre.auth.AccountCredentials;
import com.wangbei.awre.auth.jwt.TokenAuthenicationService;
import com.wangbei.exception.ExceptionMap;
import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.Response;
import com.wangbei.util.JacksonUtil;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuyidi 2017-07-08 20:59:19
 * @class com.wangbei.awre.auth.filter.JWTFilter
 * @description JWT 认证处理过滤器  过滤处理JWT成功或失败
 */
public class JWTFilter extends AbstractAuthenticationProcessingFilter {

    public JWTFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url,"POST"));
        setAuthenticationManager(authenticationManager);
    }


    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @return org.springframework.security.core.Authentication
     * @author yuyidi 2017-07-08 20:21:46
     * @method attemptAuthentication
     * @description 尝试认证  登陆时调用
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse) throws AuthenticationException, IOException, ServletException {
        AccountCredentials account = new ObjectMapper().readValue(httpServletRequest.getInputStream(),
                AccountCredentials.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(account.getName(),
                account.getPassword()));
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain
            chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        //TODO 认证成功后，响应token给客户端
        String token = TokenAuthenicationService.produceAuthentication(authResult.getName());
        if (token != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getOutputStream().println(JacksonUtil.encode(new Response<String>(token)));
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println(JacksonUtil.encode(new Response<String>(ServiceException
                .TOKEN_VALIDATE_EXCEPTION, ExceptionMap.exceptionMap.get(ServiceException.TOKEN_VALIDATE_EXCEPTION))));
    }
}
