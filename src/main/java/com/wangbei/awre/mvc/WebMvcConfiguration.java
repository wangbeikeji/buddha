package com.wangbei.awre.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wangbei.awre.converter.DateConverter;
import com.wangbei.awre.converter.UniversalEnumConverterFactory;

/**
 * @author Created by yuyidi on 2017/7/7.
 * @desc
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Value("${custom.outer.resources}")
	private String outerResources;

	@Override
	public void addFormatters(FormatterRegistry registry) {
		super.addFormatters(registry);
		registry.addConverterFactory(new UniversalEnumConverterFactory());
		registry.addConverter(new DateConverter());
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter);
	}

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
		registry.addResourceHandler("/admin/**").addResourceLocations("file:" + outerResources + "admin/");
		super.addResourceHandlers(registry);
	}

}
