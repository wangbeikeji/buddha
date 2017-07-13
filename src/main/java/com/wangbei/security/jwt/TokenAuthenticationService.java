package com.wangbei.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.wangbei.exception.ServiceException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	static final long EXPIRATIONTIME = 432_000_000; // 5天
	static final String SECRET = "P@ssw02d"; // JWT密码
	static final String TOKEN_PREFIX = "Bearer"; // Token前缀
	static final String HEADER_STRING = "Authorization";// 存放Token的Header Key

	/**
	 * 生成token
	 * 
	 * @param username
	 *            当前用户名
	 * @param grantedAuthStr
	 *            权限（多个权限以英文逗号分割）
	 * @return token
	 */
	public static String generateToken(String username, String grantedAuthStr) {
		return Jwts.builder().claim("authorities", grantedAuthStr).setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	public static Map<String, Object> getTokenInfo(String token) {
		try {
			// 解析 Token
			Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
			// 获取token中的信息并返回
			Map<String, Object> result = new HashMap<>();
			result.put("sub", claims.getSubject());
			result.put("authorities", claims.get("authorities"));
			result.put("exp", claims.getExpiration());
			return result;
		} catch (Exception ex) {
			throw new ServiceException(ServiceException.TOKEN_VALIDATE_EXCEPTION);
		}
	}

	static Authentication getAuthentication(HttpServletRequest request) {
		// 从Header中拿到token
		String token = request.getHeader(HEADER_STRING);

		if (token != null) {
			// 解析 Token
			Claims claims = Jwts.parser()
					// 验签
					.setSigningKey(SECRET)
					// 去掉 Bearer
					.parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();

			// 拿用户名
			String user = claims.getSubject();

			// 得到 权限（角色）
			List<GrantedAuthority> authorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));

			// 返回验证令牌
			return user != null ? new UsernamePasswordAuthenticationToken(user, null, authorities) : null;
		}
		return null;
	}
}
