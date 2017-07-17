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
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.wangbei.security.AuthUserDetails;

public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// 获取请求中的token
		String token = ((HttpServletRequest) request).getHeader(TokenAuthenticationService.HEADER_STRING);
		if (token != null && !"".equals(token)) {
			// 获取token中的信息
			try {
				Map<String, Object> tokenInfo = TokenAuthenticationService.getTokenInfo(token);
				// 判断username是否存在，以及token是否过期
				String username = (String) tokenInfo.get("sub");
				Integer userId = (Integer) tokenInfo.get("userId");
				if (username != null && !"".equals(username)) {
					Date exp = (Date) tokenInfo.get("exp");
					if (exp != null && exp.getTime() * 1000 > System.currentTimeMillis()) {
						// 如果为正确的token，将身份验证放入上下文中
						List<GrantedAuthority> authorities = AuthorityUtils
								.commaSeparatedStringToAuthorityList((String) tokenInfo.get("authorities"));
						AuthUserDetails userDeatails = new AuthUserDetails(userId, username, null, authorities);
						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								username, null, authorities);
						authentication.setDetails(userDeatails);
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				}
			} catch (Exception ex) {
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.setStatus(HttpStatus.FORBIDDEN.value());
			}
		}

		filterChain.doFilter(request, response);
	}
}