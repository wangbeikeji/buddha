package com.wangbei.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	static final long EXPIRATIONTIME = 43200000000000L; // 5天
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
	public static String generateToken(Integer userId, String username, String grantedAuthStr) {
		return Jwts.builder().claim("authorities", grantedAuthStr).claim("userId", userId).setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	public static Map<String, Object> getTokenInfo(String token) throws Exception {
		// 解析 Token
		Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
		// 获取token中的信息并返回
		Map<String, Object> result = new HashMap<>();
		result.put("sub", claims.getSubject());
		result.put("userId", claims.get("userId"));
		result.put("authorities", claims.get("authorities"));
		result.put("exp", claims.getExpiration());
		return result;
	}

}
