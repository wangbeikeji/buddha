package com.wangbei.awre.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author yuyidi 2017-07-08 20:40:24
 * @class com.wangbei.awre.auth.jwt.TokenAuthenicationService
 * @description token 生成与认证
 */
public class TokenAuthenicationService {
    static final long EXPIRATIONTIME = 432_000_000;     // 5天
    static final String SECRET = "P@ssw02d";            // JWT密码
    static final String TOKEN_PREFIX = "Bearer";        // Token前缀
    static final String HEADER_STRING = "Authorization";// 存放Token的Header Key

    public static String produceAuthentication(String name) {
        return Jwts.builder().claim("authorities", "ROLE_ADMIN,AUTH_WRITE")
                .setSubject(name)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))  //有效期设置
                .signWith(SignatureAlgorithm.HS512, SECRET) // 签名设置
                .compact();
    }

    public static Authentication validateAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            String user = claims.getSubject();
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims
                    .get("authorities"));
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, authorities) : null;
        }
        return null;
    }
}
