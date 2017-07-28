package com.wangbei.service.wxpay.api;

public class WxPayConfig {

	// =======【基本信息设置】=====================================
	/**
	 * APPID：绑定支付的APPID（必须配置）
	 */
	public static String APPID = "wxae466a405da73444";
	/**
	 * MCHID：商户号（必须配置）
	 */
	public static String MCHID = "1486584802";
	/**
	 * KEY：商户支付密钥，参考开户邮件设置（必须配置）
	 */
	public static String KEY = "e87b51e3aeba416c98b315a8b3ae665e";
	/**
	 * APPSECRET：公众帐号secert（仅JSAPI支付的时候需要配置）
	 */
	public static String APPSECRET = "4890cf5ec3d2f8a0fdca7e85b285a8a4";

	// =======【证书路径设置】=====================================
	/**
	 * 证书路径,注意应该填写绝对路径（仅退款、撤销订单时需要）
	 */
	public static String SSLCERT_PATH = "cert/apiclient_cert.p12";
	public static String SSLCERT_PASSWORD = "1219688401";

	// =======【支付结果通知url】=====================================
	/**
	 * 支付结果通知回调url，用于商户接收支付结果
	 */
	public static String NOTIFY_URL = "https://bud.waben.com.cn/buddha/wxpay/notify";

	// =======【商户系统后台机器IP】=====================================
	/**
	 * 此参数可手动配置也可在程序中自动获取
	 */
	public static String IP = "120.25.128.251";

	// =======【代理服务器设置】===================================
	/**
	 * 默认IP和端口号分别为0.0.0.0和0，此时不开启代理（如有需要才设置）
	 */
	public static String PROXY_URL = "http://10.152.18.220:8080";

	// =======【上报信息配置】===================================
	/**
	 * 测速上报等级，0.关闭上报; 1.仅错误时上报; 2.全量上报
	 */
	public static int REPORT_LEVENL = 1;

	// =======【日志级别】===================================
	/**
	 * 日志等级，0.不输出日志；1.只输出错误信息; 2.输出错误和正常信息; 3.输出错误信息、正常信息和调试信息
	 */
	public static int LOG_LEVENL = 0;

}
