package com.wangbei.security.jwt;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// 获取请求中的token
		String token = ((HttpServletRequest) request).getHeader(TokenAuthenticationService.HEADER_STRING);
		if (token != null && !"".equals(token)) {
			// 获取token中的信息
			Map<String, Object> tokenInfo = TokenAuthenticationService.getTokenInfo(token);
			// 判断username是否存在，以及token是否过期
			String useranme = (String) tokenInfo.get("sub");
			if (useranme != null && !"".equals(useranme)) {
				Date exp = (Date) tokenInfo.get("exp");
				if (exp != null && exp.getTime() * 1000 > System.currentTimeMillis()) {
					// 如果为正确的token，将身份验证放入上下文中
					List<GrantedAuthority> authorities = AuthorityUtils
							.commaSeparatedStringToAuthorityList((String) tokenInfo.get("authorities"));
					Authentication authentication = new UsernamePasswordAuthenticationToken(useranme, null,
							authorities);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}

		filterChain.doFilter(request, response);
	}
}