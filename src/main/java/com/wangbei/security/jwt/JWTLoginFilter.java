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

import com.wangbei.exception.ExceptionEnum;
import com.wangbei.pojo.ConsumeMeritRanking;
import com.wangbei.pojo.Response;
import com.wangbei.pojo.UserWithToken;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.service.RankingService;
import com.wangbei.service.UserService;
import com.wangbei.util.JacksonUtil;

import io.swagger.models.HttpMethod;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private UserService userService;

	private RankingService rankingService;

	public JWTLoginFilter(String url, AuthenticationManager authManager, UserService userService,
			RankingService rankingService) {
		super(new AntPathRequestMatcher(url, HttpMethod.POST.name()));
		this.userService = userService;
		this.rankingService = rankingService;
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		String isAdmin = req.getParameter("isAdmin");
		if ("true".equals(isAdmin)) {
			// 后台管理登陆
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					"admin_" + req.getParameter("username"), req.getParameter("password")));
		} else {
			// APP用户登陆
			return getAuthenticationManager()
					.authenticate(new UsernamePasswordAuthenticationToken(req.getParameter("phone"),
							req.getParameter("password") + "_extra_" + req.getParameter("newPhone")));
		}
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
		AuthUserDetails authUser = (AuthUserDetails) auth.getPrincipal();
		UsernamePasswordAuthenticationToken upAuth = (UsernamePasswordAuthenticationToken) auth;
		upAuth.setDetails(authUser);
		String token = TokenAuthenticationService.generateToken(authUser.getUserId(), auth.getName(),
				grantedAuthStr.toString());
		// step 3 : 返回token到客户端
		UserWithToken user = new UserWithToken(userService.getUserByPhone(auth.getName()));
		ConsumeMeritRanking ranking = rankingService.currentUserConsumeMeritRanking(user.getId());
		if (ranking != null) {
			user.setGrade(ranking.getGrade());
		}
		user.setName(authUser.getName());
		user.setMeritValue(userService.getUserMeritValue(user.getId()));
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
		Response<String> result = new Response<>(ExceptionEnum.USERNAME_OR_PASSWORD_ERROR_EXCEPTION.getCode(),
				ExceptionEnum.USERNAME_OR_PASSWORD_ERROR_EXCEPTION.getMessage());
		response.getWriter().println(JacksonUtil.encode(result));
	}
}
