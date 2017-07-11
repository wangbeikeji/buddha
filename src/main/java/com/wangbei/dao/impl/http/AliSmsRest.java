package com.wangbei.dao.impl.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.aliyuncs.exceptions.ClientException;
import com.wangbei.dao.SmsDao;
import com.wangbei.pojo.SendAuthCodeResult;
import com.wangbei.sdk.ali.SendSmsClient;
import com.wangbei.sdk.ali.model.SendSmsResponse;
import com.wangbei.util.RandomUtil;
import com.wangbei.util.enums.SmsTypeEnum;

/**
 * @author yuyidi 2017-07-06 13:49:06
 * @class com.wangbei.dao.impl.http.AliSmsRest
 * @description 短信http请求实现
 */
@Component
public class AliSmsRest extends HttpRest<SendAuthCodeResult> implements SmsDao {

	private final Log logger = LogFactory.getLog(getClass());

	@Override
	public SmsTypeEnum getSmsType() {
		return SmsTypeEnum.Ali;
	}

	@Override
	public SendAuthCodeResult sendAuthCode(String[] phoneNumbers) {
		SendAuthCodeResult result = new SendAuthCodeResult();
		if (phoneNumbers != null && phoneNumbers.length > 0) {
			// 手机号
			StringBuilder strBuilder = new StringBuilder();
			for (String phoneNumber : phoneNumbers) {
				strBuilder.append(phoneNumber + ",");
			}
			strBuilder.deleteCharAt(strBuilder.length() - 1);
			// 验证码
			Map<String, String> params = new HashMap<>();
			String authCode = RandomUtil.generateRandomCode(6);
			params.put("authCode", authCode);
			// 发送短信
			try {
				logger.info("Send sms request, phoneNumbers:" + strBuilder.toString() + ", authCode:" + authCode);
				SendSmsResponse response = SendSmsClient.sendSms(strBuilder.toString(), params, null);
				logger.info("Send sms response, code:" + response.getCode() + ", message:" + response.getMessage());
				if ("OK".equals(response.getCode())) {
					result.setCode(1000);
					result.setValidationCode(authCode);
					result.setMessage("发送成功!");
				} else {
					result.setCode(1002);
					result.setMessage(response.getMessage());
				}
			} catch (ClientException e) {
				result.setCode(1002);
				result.setMessage(e.getMessage());
			}
		} else {
			result.setCode(1001);
			result.setMessage("手机号不能为空!");
		}
		return result;
	}

}
