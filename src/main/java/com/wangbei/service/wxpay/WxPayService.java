package com.wangbei.service.wxpay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangbei.dao.TradeDao;
import com.wangbei.entity.Trade;
import com.wangbei.exception.ServiceException;
import com.wangbei.service.TradeService;
import com.wangbei.service.wxpay.api.WxPayApi;
import com.wangbei.service.wxpay.api.WxPayConfig;
import com.wangbei.service.wxpay.api.WxPayData;
import com.wangbei.util.RandomUtil;
import com.wangbei.util.enums.PaymentTypeEnum;
import com.wangbei.util.enums.TradeTypeEnum;

@Service
public class WxPayService {

	/**
	 * 0微信公众号支付，微信APP支付
	 */
	private int payMode = 1;

	@Autowired
	private TradeService tradeService;

	@Autowired
	private TradeDao tradeDao;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public Map<String, Object> unifiedOrder(Integer userId, Integer tradeType, int meritValue, int totalFee,
			String goodsDesc, Object... otherParams) {
		Map<String, Object> result = new HashMap<String, Object>();
		// step 1 : 组装请求参数
		WxPayData payDataReq = new WxPayData();
		// -----------------配置类型的参数-------------------
		// 异步通知url
		payDataReq.addValue("notify_url", WxPayConfig.NOTIFY_URL);
		// 公众账号ID
		payDataReq.addValue("appid", WxPayConfig.APPID);
		// 商户号
		payDataReq.addValue("mch_id", WxPayConfig.MCHID);
		// 终端ip
		payDataReq.addValue("spbill_create_ip", WxPayConfig.IP);
		// 随机字符串
		payDataReq.addValue("nonce_str", RandomUtil.generateNonceStr());

		// -----------------支付数据的参数-------------------
		String tradeNo = TradeService.generateTradeNo();
		payDataReq.addValue("out_trade_no", tradeNo);
		payDataReq.addValue("body", goodsDesc);
		payDataReq.addValue("total_fee", totalFee);

		String openid = null;
		if (payMode == 0) {
			payDataReq.addValue("trade_type", "JSAPI");
			if (otherParams != null && otherParams.length >= 1) {
				openid = otherParams[0].toString();
				payDataReq.addValue("openid", openid);
			}
		} else if (payMode == 1) {
			payDataReq.addValue("trade_type", "APP");
		} else {
			throw new RuntimeException("不支持的微信支付方式!");
		}

		try {
			// step 2 : 调用第三方统一下单接口
			WxPayData payDataResp = WxPayApi.unifiedOrder(payDataReq, WxPayConfig.KEY);
			String resultCode = null;
			String resultMessage = null;
			String thirdTradeNo = null;
			if ("SUCCESS".equals(payDataResp.getValue("return_code"))) {
				if ("SUCCESS".equals(payDataResp.getValue("result_code"))) {
					resultCode = "SUCCESS";
					resultMessage = payDataResp.getValue("return_msg").toString();
					thirdTradeNo = payDataResp.getValue("prepay_id").toString();
				} else {
					resultCode = payDataResp.getValue("err_code").toString();
					resultMessage = payDataResp.getValue("err_code_des").toString();
				}
			} else {
				resultCode = "FAIL";
				resultMessage = payDataResp.getValue("return_msg").toString();
			}
			// step 3 : 处理响应结果
			if ("SUCCESS".equals(resultCode)) {
				if (payMode == 0) {
					WxPayData payParamData = new WxPayData();
					payParamData.addValue("appId", WxPayConfig.APPID);
					payParamData.addValue("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
					payParamData.addValue("nonceStr", RandomUtil.generateNonceStr());
					payParamData.addValue("package", "prepay_id=" + thirdTradeNo);
					payParamData.addValue("signType", "MD5");
					String sign = payParamData.makeSign(WxPayConfig.KEY);
					payParamData.addValue("sign", sign);

					result = payParamData.getDataValues();
				} else if (payMode == 1) {
					// 生成sign
					WxPayData payParamData = new WxPayData();
					payParamData.addValue("appid", WxPayConfig.APPID);
					payParamData.addValue("partnerid", WxPayConfig.MCHID);
					payParamData.addValue("prepayid", thirdTradeNo);
					payParamData.addValue("package", "Sign=WXPay");
					payParamData.addValue("noncestr", RandomUtil.generateNonceStr());
					payParamData.addValue("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
					String sign = payParamData.makeSign(WxPayConfig.KEY);
					payParamData.addValue("sign", sign);

					result = payParamData.getDataValues();
				}
				// step 5 : 保存交易记录
				tradeService.paymentTrade(tradeNo, thirdTradeNo, userId, TradeTypeEnum.getByIndex(tradeType),
						PaymentTypeEnum.WxPay, meritValue, totalFee / 100D);
				result.put("tradeNo", tradeNo);
			} else {
				if (payMode == 0) {
					result.put("appId", null);
					result.put("timeStamp", null);
					result.put("nonceStr", null);
					result.put("package", null);
					result.put("signType", null);
					result.put("sign", null);
				} else if (payMode == 1) {
					result.put("appid", null);
					result.put("partnerid", null);
					result.put("prepayid", null);
					result.put("package", null);
					result.put("noncestr", null);
					result.put("timestamp", null);
					result.put("sign", null);
				}
				result.put("tradeNo", null);
			}
			result.put("resultCode", resultCode);
			result.put("resultMessage", resultMessage);
		} catch (IOException e) {
			logger.error("Weixin pay UnifiedOrder post http request failed!");
			throw new RuntimeException("Weixin pay UnifiedOrder post http request failed!");
		}

		return result;
	}

	public Trade orderQuery(String tradeNo) {
		Trade trade = tradeDao.retrieveByTradeNo(tradeNo);
		if (trade == null) {
			throw new ServiceException(ServiceException.TRADENO_NOTEXIST_EXCEPTION);
		}
		String thirdTradeNo = trade.getThirdTradeNo();
		// -----------------配置类型的参数-------------------
		WxPayData queryDataReq = new WxPayData();
		// 公众账号ID
		queryDataReq.addValue("appid", WxPayConfig.APPID);
		// 商户号
		queryDataReq.addValue("mch_id", WxPayConfig.MCHID);
		// 随机字符串
		queryDataReq.addValue("nonce_str", RandomUtil.generateNonceStr());
		// -----------------查询数据的参数-------------------
		queryDataReq.addValue("transaction_id", thirdTradeNo);
		try {
			// 调用第三方查询订单接口
			WxPayData queryDataResp = WxPayApi.orderQuery(queryDataReq, WxPayConfig.KEY);
			if ("SUCCESS".equals(queryDataResp.getValue("return_code"))) {
				if ("SUCCESS".equals(queryDataResp.getValue("result_code"))) {
					// 支付成功
					return tradeService.completePaymentTrade(tradeNo);
				}
			}
		} catch (IOException e) {
			logger.error("Weixin pay orderQuery post http request failed!");
			throw new RuntimeException("Weixin pay orderQuery post http request failed!");
		}
		return trade;
	}

	public String receiveNotify(String xml) {
		WxPayData notifyResult = WxPayApi.getNotifyData(xml);
		if ("SUCCESS".equals(notifyResult.getValue("return_code").toString())) {
			String tradeNo = notifyResult.getValue("tradeNo").toString();
			tradeService.completePaymentTrade(tradeNo);
			notifyResult.getDataValues().remove("tradeNo");
		}
		return notifyResult.toXml();
	}

}
