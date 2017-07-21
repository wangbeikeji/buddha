package com.wangbei.awre.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Created by yuyidi on 2017/7/7.
 * @desc
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	@Value("${custom.outer.resources}")
	private String outerResources;

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

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/editorupload/**")
				.addResourceLocations("file:" + outerResources + "editorupload/");
		registry.addResourceHandler("/picture/**").addResourceLocations("file:" + outerResources + "picture/");
		registry.addResourceHandler("/document/**").addResourceLocations("file:" + outerResources + "document/");
		registry.addResourceHandler("/attachment/**").addResourceLocations("file:" + outerResources + "attachment/");
		super.addResourceHandlers(registry);
	}

}
