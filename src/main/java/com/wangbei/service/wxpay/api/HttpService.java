package com.wangbei.service.wxpay.api;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
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

	public static String sendPostParamForHtml(String url, Map<String, Object> paramMap) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded;charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", "text/html;charset=UTF-8");

		LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			map.add(entry.getKey(), String.valueOf(entry.getValue()));
		}
		HttpEntity<LinkedMultiValueMap<String, String>> formEntity = new HttpEntity<LinkedMultiValueMap<String, String>>(
				map, headers);
		String result = restTemplate.postForObject(url, formEntity, String.class);
		return new String(result.getBytes(StringHttpMessageConverter.DEFAULT_CHARSET), "UTF-8");
	}

	public static String sendGetForHtml(String url) throws IOException {
		String result = restTemplate.getForObject(url, String.class);
		return new String(result.getBytes(StringHttpMessageConverter.DEFAULT_CHARSET), "GBK");
	}
}
