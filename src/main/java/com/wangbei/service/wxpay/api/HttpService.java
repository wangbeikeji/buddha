package com.wangbei.service.wxpay.api;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class HttpService {

	public static RestTemplate restTemplate = new RestTemplate();
	
	public static String sendPostXml(String url, String xml) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("text/xml;charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", "text/xml;charset=UTF-8");
		HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);
		String result = restTemplate.postForObject(url, formEntity, String.class);
		return new String(result.getBytes(StringHttpMessageConverter.DEFAULT_CHARSET), "UTF-8");
	}
}
