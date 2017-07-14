package com.wangbei.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wangbei.dao.UserDao;
import com.wangbei.security.jwt.JWTAuthenticationFilter;
import com.wangbei.security.jwt.JWTLoginFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDao userDao;

	@Bean
	AuthenticationProvider authenticationProvider() {
		return new AuthenticationProvider();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// 登陆和注册
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/user/register").permitAll();
		http.authorizeRequests().antMatchers("/sms/**").permitAll();
		http.authorizeRequests().antMatchers("/user/login").permitAll();
		http.authorizeRequests().antMatchers("/user/resetPassword").permitAll();
		// swagger
		http.authorizeRequests().antMatchers("/swagger-ui.html").permitAll();
		http.authorizeRequests().antMatchers("/webjars/**").permitAll();
		http.authorizeRequests().antMatchers("/swagger-resources/**").permitAll();
		http.authorizeRequests().antMatchers("/v2/api-docs").permitAll();
		http.authorizeRequests().antMatchers("/configuration/**").permitAll();
		// 图片
		http.authorizeRequests().antMatchers("/picture/**").permitAll();
		// 文档
		http.authorizeRequests().antMatchers("/document/**").permitAll();
		// 部分开发接口
		http.authorizeRequests().antMatchers("/offerings/groupByType").permitAll();
		http.authorizeRequests().antMatchers("/joss/list").permitAll();
		http.authorizeRequests().antMatchers("/rune/list").permitAll();
		http.authorizeRequests().antMatchers("/knowledge/pageByType").permitAll();
		http.authorizeRequests().antMatchers("/sutra/page").permitAll();
		http.authorizeRequests().antMatchers("/sutra/list").permitAll();
		http.authorizeRequests().antMatchers("/banner/page").permitAll();
		http.authorizeRequests().antMatchers("/banner/pageByType").permitAll();
		http.authorizeRequests().antMatchers("/banner/list").permitAll();
		http.authorizeRequests().antMatchers("/banner/listByType").permitAll();
		
		http.authorizeRequests().antMatchers("/**").authenticated();
		
		// 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
		http.addFilterBefore(new JWTLoginFilter("/login", authenticationManager(), userDao),
                UsernamePasswordAuthenticationFilter.class);
		// 添加一个过滤器验证其他请求的Token是否合法
		http.addFilterBefore(new JWTAuthenticationFilter(),
				UsernamePasswordAuthenticationFilter.class);
	}

}