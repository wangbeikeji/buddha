package com.wangbei.awre.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;
import com.wangbei.pojo.Response;

@ControllerAdvice(basePackages = { "com.wangbei.controller" })
public class GlobalExceptionHandler {

	Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public Response<? extends Object> exceptionHandler(ServiceException ex, HttpServletResponse response) {
		Response<? extends Object> result = new Response<>(null);
		result.setCode(ex.getCode());
		result.setMessage(ex.getMessage());
		return result;
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Response<? extends Object> exceptionHandler(Exception ex, HttpServletRequest request,
			HttpServletResponse response) {
		String message = String.format(ExceptionEnum.UNKNOW_EXCEPTION.getMessage(), new Object[] { ex.getMessage() });
		logger.error(message, ex);
		Response<? extends Object> result = new Response<>(null);
		String servletPath = request.getServletPath();
		result.setCode(ExceptionEnum.UNKNOW_EXCEPTION.getMessage());
		result.setMessage(String.format("%s:%s:%s", message, servletPath, ex.getMessage()));
		return result;
	}

}
