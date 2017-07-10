package com.wangbei.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	AuthenticationProvider authenticationProvider() {
		return new AuthenticationProvider();
	}

	@Bean
	AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new AuthenticationSuccessHandler();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/noauth.html").permitAll();
		http.authorizeRequests().antMatchers("/index.html").authenticated();
		http.authorizeRequests().antMatchers("/user.html").hasAuthority("ROLE_USER");
		http.authorizeRequests().antMatchers("/admin.html").hasAuthority("ROLE_ADMIN");

		http.authorizeRequests().antMatchers("/sec/**").authenticated();

		http.authorizeRequests().and().formLogin().loginPage("/login").successHandler(authenticationSuccessHandler())
				.failureUrl("/login?error").permitAll().and().logout().permitAll();
	}

}