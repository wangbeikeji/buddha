package com.wangbei.awre.mvc;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.Response;

@ControllerAdvice(basePackages = { "com.wangbei.controller" })
public class GlobalExceptionHandler {

	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public Response<? extends Object> exceptionHandler(ServiceException ex, HttpServletResponse response) {
		Response<? extends Object> result = new Response<>(null);
		result.setCode(ex.getType());
		result.setMessage(ex.getMessage());
		return result;
	}

}
