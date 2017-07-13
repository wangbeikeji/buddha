package com.wangbei.security.jwt;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.wangbei.dao.UserDao;
import com.wangbei.pojo.Response;
import com.wangbei.pojo.UserWithToken;
import com.wangbei.util.JacksonUtil;

import io.swagger.models.HttpMethod;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private UserDao userDao;
	
	public JWTLoginFilter(String url, AuthenticationManager authManager, UserDao userDao) {
		super(new AntPathRequestMatcher(url, HttpMethod.POST.name()));
		this.userDao = userDao;
		setAuthenticationManager(authManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		// 验证登陆信息
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(req.getParameter("phone"), req.getParameter("password")));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		// step 1 : 获取权限
		StringBuilder grantedAuthStr = new StringBuilder();
		Collection<? extends GrantedAuthority> grantedAuthList = auth.getAuthorities();
		for (GrantedAuthority grantedAuth : grantedAuthList) {
			grantedAuthStr.append(grantedAuth.getAuthority() + ",");
		}
		// step 2 : 获取token
		String token = TokenAuthenticationService.generateToken(auth.getName(), grantedAuthStr.toString());
		// step 3 : 返回token到客户端
		UserWithToken user = new UserWithToken(userDao.fetchUserByPhone(auth.getName()));
		user.setToken(token);
		Response<UserWithToken> result = new Response<UserWithToken>("200", user, "successful!");
		res.setContentType("application/json;charset=utf-8");
		res.setStatus(HttpServletResponse.SC_OK);
		res.getWriter().println(JacksonUtil.encode(result));
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		Response<String> result = new Response<>("601", "用户名或者密码错误!");
		response.getWriter().println(JacksonUtil.encode(result));
	}
}
