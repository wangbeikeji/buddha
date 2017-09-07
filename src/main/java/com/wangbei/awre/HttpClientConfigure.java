package com.wangbei.awre;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;

/**
 * @author yuyidi 2017-07-06 13:06:16
 * @class com.wangbei.awre.HttpClientConfigure
 * @description
 */
@Configuration
public class HttpClientConfigure {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Bean
	public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(60,
				TimeUnit.SECONDS);
		connectionManager.setMaxTotal(1000);
		connectionManager.setDefaultMaxPerRoute(50);
		return connectionManager;
	}

	@Bean
	public HttpRequestRetryHandler httpRequestRetryHandler() {
		HttpRequestRetryHandler httpRequestRetryHandler = new DefaultHttpRequestRetryHandler(3, true);
		return httpRequestRetryHandler;
	}

	@Bean
	public HttpClientBuilder httpClientBuilder() {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager());
		httpClientBuilder.setRetryHandler(httpRequestRetryHandler());
		List<BasicHeader> headers = new ArrayList<>();
		BasicHeader userAgent = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 "
				+ "(KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36");
		BasicHeader acceptEncoding = new BasicHeader("Accept-Encoding", "gzip,deflate");
		BasicHeader acceptLanguage = new BasicHeader("Accept-Language", "zh-CN");
		headers.add(userAgent);
		headers.add(acceptEncoding);
		headers.add(acceptLanguage);
		httpClientBuilder.setDefaultHeaders(headers);
		return httpClientBuilder;
	}

	@Bean
	public HttpClient httpClient() {
		HttpClient httpClient = httpClientBuilder().build();
		return httpClient;
	}

	@Bean
	public ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				httpClient());
		clientHttpRequestFactory.setConnectTimeout(5000);
		clientHttpRequestFactory.setReadTimeout(10000);
		return clientHttpRequestFactory;
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		messageConverters.add(new ByteArrayHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new ResourceHttpMessageConverter());
		messageConverters.add(new SourceHttpMessageConverter<>());
		messageConverters.add(new AllEncompassingFormHttpMessageConverter());
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(mappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		jackson2HttpMessageConverter.setObjectMapper(objectMapper());
		List<MediaType> mediaTypes = new ArrayList<>();
		mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		mediaTypes.add(MediaType.TEXT_HTML);
		jackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);
		return jackson2HttpMessageConverter;
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializerProvider(new CustomSerializerProvider());
		return objectMapper;
	}

	public static class NullStringJsonSerializer extends JsonSerializer<Object> {

		public static final NullStringJsonSerializer INSTANCE = new NullStringJsonSerializer();

		@Override
		public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException, JsonProcessingException {
			if (value == null) {
				gen.writeString("");
			} else {
				gen.writeObject(value);
			}
		}

		@Override
		public Class<Object> handledType() {
			return Object.class;
		}
	}

	public static class CustomSerializerProvider extends DefaultSerializerProvider {

		private static final long serialVersionUID = 4316655328855093783L;

		public CustomSerializerProvider() {
			super();

		}

		public CustomSerializerProvider(SerializerProvider src, SerializationConfig config, SerializerFactory f) {
			super(src, config, f);
		}

		@Override
		public DefaultSerializerProvider createInstance(SerializationConfig config, SerializerFactory jsf) {
			return new CustomSerializerProvider(this, config, jsf);
		}

		@Override
		public JsonSerializer<Object> findNullValueSerializer(BeanProperty property) throws JsonMappingException {
			JavaType type = property.getType();
			if (type.getRawClass().equals(String.class)) {
				return NullStringJsonSerializer.INSTANCE;
			}
			return super.findNullValueSerializer(property);
		}

	}

}
