package com.wangbei.awre.mvc;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Created by yuyidi on 2017/7/7.
 * @desc
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
				ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/httpError/403");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/httpError/404");
				container.addErrorPages(error401Page, error403Page, error404Page);
			}
		};
	}
    
}
