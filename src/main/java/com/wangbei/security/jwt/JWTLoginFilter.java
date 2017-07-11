package com.wangbei.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangbei.pojo.LoginParam;
import com.wangbei.pojo.Response;
import com.wangbei.util.JsonUtil;

import io.swagger.models.HttpMethod;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url, HttpMethod.POST.name()));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		// 获取登陆请求数据
		LoginParam creds = null;
		try {
			creds = new ObjectMapper().readValue(req.getInputStream(), LoginParam.class);
		} catch (Exception ex) {
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(null, null));
		}
		// 验证登陆信息
		return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		TokenAuthenticationService.addAuthentication(res, auth.getName());
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		Response<String> result = new Response<>("601", "用户名或者密码错误!");
		response.getWriter().println(JsonUtil.beanToJson(result));
	}
}
