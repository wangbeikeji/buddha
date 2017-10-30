package com.wangbei.awre;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class ControllerLogAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String requestPath = null; // 请求地址
	private Integer userId = 0; // 用户id
	private Map<String, String[]> inputParamMap = null; // 传入参数
	private Map<String, Object> outputParamMap = null; // 存放输出结果
	private long startTimeMillis = 0; // 开始时间
	private long endTimeMillis = 0; // 结束时间
	private String method = null; // http method
	private String requestNo = null;

	@Autowired
	private ObjectMapper mapper;

	@Pointcut("execution(* com.wangbei.controller..*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void controllerMethodPointcut() {
	}

	@Before("controllerMethodPointcut()")
	public void doBeforeInServiceLayer(JoinPoint joinPoint) {
		startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
		requestNo = String.valueOf(startTimeMillis);
	}

	@After("controllerMethodPointcut()")
	public void doAfterInServiceLayer(JoinPoint joinPoint) {
		endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
		this.printOptLog();
	}

	@Around("controllerMethodPointcut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		// 获取输入参数
		inputParamMap = request.getParameterMap();
		// 获取请求地址
		requestPath = request.getRequestURI();
		// 获取http method
		method = request.getMethod();
		// 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
		outputParamMap = new HashMap<String, Object>();
		// result的值就是被拦截方法的返回值
		Object result = pjp.proceed();
		outputParamMap.put("result", result);
		return result;
	}

	private void printOptLog() {
		if ("/buddha/system/ping".equals(requestPath)) {
			return;
		}
		try {
			String paramStr = mapper.writeValueAsString(inputParamMap);
			StringBuilder resultStr = new StringBuilder();
			if (outputParamMap.get("result") != null) {
				resultStr.append(mapper.writeValueAsString(outputParamMap.get("result")));
			}
			if ("GET".equals(method)) {
				if (resultStr.indexOf("{\"code\":\"200\"") == 0) {
					int messageIndex = resultStr.lastIndexOf(",\"message\"");
					if (messageIndex > 0) {
						String messge = resultStr.substring(messageIndex, resultStr.length() - 1);
						resultStr.delete(messageIndex, resultStr.length() - 1);
						resultStr.insert(13, messge);
					}
				}
				if (resultStr.length() > 200) {
					resultStr.delete(200, resultStr.length());
				}
			}

			String format = "\n请求地址：{}, 请求编号：{}, 请求用户：{}, 响应时间：{}ms, 请求方法：{} \n请求参数：{} \n响应结果：{}";
			Object[] args = new Object[] { requestPath, requestNo, userId, (endTimeMillis - startTimeMillis), method,
					paramStr, resultStr };
			logger.info(format, args);
		} catch (Exception ex) {
			logger.error("输出http请求日志发生异常!", ex);
		}
	}
}
