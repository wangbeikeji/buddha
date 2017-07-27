package com.wangbei.service.wxpay.api;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangbei.util.RandomUtil;

/**
 * 调用微信支付的api
 * 
 * @author luomengan
 *
 */
public class WxPayApi {
	
	private static Logger logger = LoggerFactory.getLogger(WxPayApi.class);

	/**
	 * 统一下单
	 * 
	 * @param WxPaydata
	 *            inputObj 提交给统一下单API的参数
	 * @param int
	 *            timeOut 超时时间
	 * @return 预支付订单信息
	 * @throws IOException
	 *             发送http请求发生错误
	 */
	public static WxPayData unifiedOrder(WxPayData inputObj, String appKey) throws IOException {
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		// 检测必填参数
		if (!inputObj.isContain("out_trade_no")) {
			throw new RuntimeException("缺少统一下单接口必填参数out_trade_no！");
		}
		if (!inputObj.isContain("body")) {
			throw new RuntimeException("缺少统一支付接口必填参数body！");
		}
		if (!inputObj.isContain("total_fee")) {
			throw new RuntimeException("缺少统一下单接口必填参数total_fee！");
		}
		if (!inputObj.isContain("trade_type")) {
			throw new RuntimeException("缺少统一下单接口必填参数trade_type！");
		}
		// 关联参数
		if (inputObj.getValue("trade_type").toString() == "JSAPI" && !inputObj.isContain("openid")) {
			throw new RuntimeException("统一下单接口中，缺少必填参数openid！trade_type为JSAPI时，openid为必填参数！");
		}
		if (inputObj.getValue("trade_type").toString() == "NATIVE" && !inputObj.isContain("product_id")) {
			throw new RuntimeException("统一下单接口中，缺少必填参数product_id！trade_type为JSAPI时，product_id为必填参数！");
		}
		// 异步通知url未设置，则使用配置文件中的url
		if (!inputObj.isContain("notify_url")) {
			throw new RuntimeException("统一下单接口中，缺少必填参数notify_url！");
		}

		// 签名
		inputObj.addValue("sign", inputObj.makeSign(appKey));
		String xml = inputObj.toXml();

		// 发送请求
		String response = HttpService.sendPostXml(url, xml);

		WxPayData result = new WxPayData();
		result.fromXml(response, appKey);
		return result;
	}

	/**
	 * 查询订单
	 * 
	 * @param inputObj
	 *            提交给查询订单API的参数
	 * @param timeOut
	 *            超时时间
	 * @return 返回订单查询结果
	 * @throws IOException
	 *             发送http请求发生错误
	 */
	public static WxPayData orderQuery(WxPayData inputObj, String appSecret) throws IOException {
		WxPayData result = new WxPayData();
		String url = "https://api.mch.weixin.qq.com/pay/orderquery";
		// 检测必填参数
		if (!inputObj.isContain("out_trade_no") && !inputObj.isContain("transaction_id")) {
			throw new RuntimeException("订单查询接口中，out_trade_no、transaction_id至少填一个！");
		}
		// 签名
		inputObj.addValue("sign", inputObj.makeSign(appSecret));

		String xml = inputObj.toXml();
		String response = HttpService.sendPostXml(url, xml);
		result.fromXml(response, appSecret);
		return result;
	}

	public static WxPayData closeOrder(WxPayData inputObj, String appSecret) throws IOException {
		WxPayData result = new WxPayData();
		String url = "https://api.mch.weixin.qq.com/pay/closeorder";
		// 检测必填参数
		if (!inputObj.isContain("out_trade_no")) {
			throw new RuntimeException("关闭订单接口中，out_trade_no必填！");
		}
		// 公众账号ID
		inputObj.addValue("appid", WxPayConfig.APPID);
		// 商户号
		inputObj.addValue("mch_id", WxPayConfig.MCHID);
		// 随机字符串
		inputObj.addValue("nonce_str", RandomUtil.generateNonceStr());
		// 签名
		inputObj.addValue("sign", inputObj.makeSign(appSecret));

		String xml = inputObj.toXml();
		String response = HttpService.sendPostXml(url, xml);
		result.fromXml(response, appSecret);
		return result;
	}
	
	public static WxPayData getNotifyData(String xml) {
		WxPayData result = new WxPayData();
		try {
			WxPayData data = new WxPayData();
			data.fromXml(xml);
			if (!data.checkSign(WxPayConfig.KEY)) {
				String tradeNo = data.getValue("out_trade_no").toString();
				result.addValue("tradeNo", tradeNo);
				result.addValue("return_code", "SUCCESS");
				result.addValue("return_msg", "OK");
			} else {
				result.addValue("return_code", "FAIL");
				result.addValue("return_msg", "签名失败!");
			}
		} catch (Exception e) {
			logger.error("Weixin nofity handle return failed!", e);
			result.addValue("return_code", "FAIL");
			result.addValue("return_msg", e.getMessage());
		}
		return result;
	}
}
