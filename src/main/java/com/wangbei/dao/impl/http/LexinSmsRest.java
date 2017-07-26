package com.wangbei.dao.impl.http;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

import com.wangbei.dao.SmsDao;
import com.wangbei.pojo.SendAuthCodeResult;
import com.wangbei.util.Md5Util;
import com.wangbei.util.enums.SmsTypeEnum;

@Component
public class LexinSmsRest extends HttpRest<SendAuthCodeResult> implements SmsDao {

	@Override
	public SmsTypeEnum getSmsType() {
		return SmsTypeEnum.Lexin;
	}

	@Override
	public SendAuthCodeResult sendAuthCode(String[] phoneNumbers) {
		try {
			StringBuilder phoneNumbersBuilder = new StringBuilder();
			for (String phoneNumber : phoneNumbers) {
				phoneNumbersBuilder.append(phoneNumber + ",");
			}
			if (phoneNumbersBuilder.length() > 0) {
				phoneNumbersBuilder.deleteCharAt(phoneNumbersBuilder.length() - 1);
			}

			String url = String.format(
					"http://www.lx198.com/sdk/send?accName=%s&accPwd=%s&aimcodes=%s&content=%s&bizId=%s&dataType=json",
					"493671072@qq.com", Md5Util.md5("luomeng1n").toUpperCase(), phoneNumbersBuilder.toString(),
					"您的验证码为26658，有效期为10分钟！【网贝科技】", String.valueOf(System.currentTimeMillis()));

			String result = doGetString(url);
			System.out.println(result);
		} catch (Exception e) {
			logger.error("md5加密失败!");
		}
		return null;
	}

}
