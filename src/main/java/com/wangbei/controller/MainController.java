/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wangbei.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Joe Grandja
 */
// @Controller
public class MainController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@RequestMapping("/")
	public String root() {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/user/index")
	public String userIndex() {
		return "user/index";
	}

	@GetMapping("/login")
	public String login() {
		logger.info("登陆跳转");
		return "login";
	}

//	@PostMapping("/login")
//	public String postLogin(HttpServletRequest httpServletRequest) throws IOException{
//		logger.info("请求登陆");
//        AccountCredentials account = new ObjectMapper().readValue(httpServletRequest.getInputStream(),
//                AccountCredentials.class);
//        logger.info(JacksonUtil.encode(account));
//        // TODO Enable form login with Spring Security (trigger error for now)
//		return "redirect:/user/index";
//	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

	@RequestMapping("/logout")
	public String logout() {
		logger.info("登出");
		return "redirect:/login";
	}

}
