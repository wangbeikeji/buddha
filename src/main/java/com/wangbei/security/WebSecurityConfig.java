package com.wangbei.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wangbei.security.jwt.JWTAuthenticationFilter;
import com.wangbei.security.jwt.JWTLoginFilter;
import com.wangbei.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

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
		http.headers().frameOptions().disable();
		// 401或者403
		http.authorizeRequests().antMatchers("/401.html").permitAll();
		http.authorizeRequests().antMatchers("/403.html").permitAll();
		http.authorizeRequests().antMatchers("/httpError/**").permitAll();
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
		// 静态资源
		http.authorizeRequests().antMatchers("/picture/**").permitAll();
		http.authorizeRequests().antMatchers("/document/**").permitAll();
		http.authorizeRequests().antMatchers("/attachment/**").permitAll();
		http.authorizeRequests().antMatchers("/editorupload/**").permitAll();
		// 部分开放接口
		http.authorizeRequests().antMatchers("/offerings/groupByType").permitAll();
		http.authorizeRequests().antMatchers("/joss/list").permitAll();
		http.authorizeRequests().antMatchers("/rune/list").permitAll();
		http.authorizeRequests().antMatchers("/knowledge/pageByType").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/knowledge/**").permitAll();
		http.authorizeRequests().antMatchers("/sutra/page").permitAll();
		http.authorizeRequests().antMatchers("/sutra/list").permitAll();
		http.authorizeRequests().antMatchers("/banner/page").permitAll();
		http.authorizeRequests().antMatchers("/banner/pageByType").permitAll();
		http.authorizeRequests().antMatchers("/banner/list").permitAll();
		http.authorizeRequests().antMatchers("/banner/listByType").permitAll();
		http.authorizeRequests().antMatchers("/dataVersion/getCurrent").permitAll();
		http.authorizeRequests().antMatchers("/upload/**").permitAll();
		http.authorizeRequests().antMatchers("/appVersion/getCurrent").permitAll();
		http.authorizeRequests().antMatchers("/appVersion/isOnline").permitAll();
		http.authorizeRequests().antMatchers("/payment/wxNotify").permitAll();
		http.authorizeRequests().antMatchers("/user/shakeDivination").permitAll();
		// 后台管理相关接口和页面
		http.authorizeRequests().antMatchers("/admin/**").permitAll();
		http.authorizeRequests().antMatchers("/share/**").permitAll();
		http.authorizeRequests().antMatchers("/system/ping").permitAll();
		http.authorizeRequests().antMatchers("/alipay/**").permitAll();
		http.authorizeRequests().antMatchers("/alipay/callback", "/alipay/auth").permitAll();
		// 其余

		http.authorizeRequests().antMatchers("/**").authenticated();

		// 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
		http.addFilterBefore(new JWTLoginFilter("/login", authenticationManager(), userService),
				UsernamePasswordAuthenticationFilter.class);
		// 添加一个过滤器验证其他请求的Token是否合法
		http.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers("/css/**", "/image/**", "/js/**");
	}
}