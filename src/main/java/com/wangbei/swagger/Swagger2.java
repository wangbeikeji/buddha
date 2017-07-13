package com.wangbei.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdGllcyI6IiIsInN1YiI6IjE4NTY1MTg5MjYzIiwiZXhwIjo0MTgxOTkxNjUyMX0.CxKH27r_WBF0EKdMERQhjxOm6gk3KlbTNX9hM75L01zNySPvppOJGJg6pPfM-zbZR_EqFDMUk073UyL6C5fh9w";
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		tokenPar.name("Authorization").description("token令牌").modelRef(new ModelRef("string")).parameterType("header")
				.defaultValue(token).required(false).build();
		pars.add(tokenPar.build());

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).globalOperationParameters(pars).select()
				.apis(RequestHandlerSelectors.basePackage("com.wangbei.controller")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("wangbei", "https://github.com/wangbeikeji/buddha", "yuyidi@waben.com");
		return new ApiInfoBuilder().title("Document Api").description("华人佛教API").license("Apache License Version 2.0")
				.contact(contact).version("1.0").build();
	}

}