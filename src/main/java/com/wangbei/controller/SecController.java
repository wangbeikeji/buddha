package com.wangbei.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需要鉴权的控制器
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/sec")
public class SecController {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/index")
	public String index() {
		return "sec index result!";
	}

}
