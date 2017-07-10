package com.wangbei.awre.auth;

import com.wangbei.awre.auth.provider.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author yuyidi 2017-07-07 11:13:25
 * @class com.wangbei.awre.auth.WebSecurityConfig
 * @description web 安全认证配置
 */
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * @param http
     * @return void
     * @author yuyidi 2017-07-07 11:07:02
     * @method configure
     * @description http验证规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("name").passwordParameter("password")
                .loginPage("/login").loginProcessingUrl("/login").failureUrl("/login-error").permitAll()
//                .and()
//                .logout().deleteCookies("remove").invalidateHttpSession(false)
//                .logoutUrl("/logout").logoutSuccessUrl("/index")

//                .and()
//                .addFilterBefore(new JWTFilter("/login", authenticationManager()),
//                        UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        ;

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/css/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("USER");
//        auth.authenticationProvider(new CustomAuthenticationProvider());
    }
}
