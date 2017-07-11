package com.wangbei.awre;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

@Configuration
public class CustomHttpMessageConverters {

	@Bean
    public HttpMessageConverters customConverters() {
        return new HttpMessageConverters(new GsonHttpMessageConverter());
    }
}
