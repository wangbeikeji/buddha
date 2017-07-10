package com.wangbei.dao.impl.http;

import org.springframework.stereotype.Component;

import com.wangbei.dao.SmsDao;
import com.wangbei.pojo.SendAuthCodeResult;
import com.wangbei.util.SmsTypeEnum;

@Component
public class SxtSmsRest extends HttpRest<SendAuthCodeResult> implements SmsDao {

	@Override
	public SmsTypeEnum getSmsType() {
		return SmsTypeEnum.Sxt;
	}

	@Override
	public SendAuthCodeResult sendAuthCode(String[] phoneNumbers) {
		String url = String.format(
				"http://61.143.63.174:8080/GateWay/Services.asmx/DirectSend?UserID=%s&Account=%s&Password=%s&Phones=%s&Content=%s&SendTime=&SendType=1&PostFixNumber=",
				"691320", "WB88888", "WB8888866", "13928952254;", "测试短信【网贝科技】");
		String result = doGetString(url);
		System.out.println(result);
		return null;
	}

}
