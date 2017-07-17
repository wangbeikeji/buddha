package com.wangbei.sdk.ali;

import java.util.HashMap;
import java.util.Map;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangbei.sdk.ali.model.SendSmsRequest;
import com.wangbei.sdk.ali.model.SendSmsResponse;

public class SendSmsClient {

	// 产品名称:云通信短信API产品,开发者无需替换
	private static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	private static final String domain = "dysmsapi.aliyuncs.com";

	// 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	private static final String accessKeyId = "LTAIrqrtreYF8ghc";
	private static final String accessKeySecret = "bPymzDrGMswIfVNjiIW9s2ADCSqWpZ";
	private static final String smsSign = "网贝科技";
	// 验证码模板，参数为:authCode
	private static final String templateCode = "SMS_77160048";
	
	public static void main(String[] args) {
		Map<String, String> params = new HashMap<>();
		params.put("code", "586933");
		params.put("type", "进行短信信息");
		try {
			SendSmsResponse result = SendSmsClient.sendSms("13928952254", params, null);
			ObjectMapper objMapper = new ObjectMapper();
			System.out.println(objMapper.writeValueAsString(result));
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送短信
	 * 
	 * @return 发送结果
	 * @throws ClientException
	 *             客户端异常
	 */
	public static SendSmsResponse sendSms(String phoneNumbers, Map<String, String> params, String outId) throws ClientException {
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(phoneNumbers);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(smsSign);
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(templateCode);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		String templateParam = "{}";
		if(params != null) {
			ObjectMapper objMapper = new ObjectMapper();
			try {
				templateParam = objMapper.writeValueAsString(params);
			} catch (JsonProcessingException e) {
				throw new RuntimeException("map转json错误?");
			}
		}
		request.setTemplateParam(templateParam);
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId(outId);

		// hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

		return sendSmsResponse;
	}

}
