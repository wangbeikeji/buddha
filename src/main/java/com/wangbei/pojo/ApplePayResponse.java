package com.wangbei.pojo;

/**
 * 苹果支付验证结果
 * 
 * @author luomengan
 *
 */
public class ApplePayResponse {

	private int status;

	private String environment;

	private ApplePayReceipt receipt;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public ApplePayReceipt getReceipt() {
		return receipt;
	}

	public void setReceipt(ApplePayReceipt receipt) {
		this.receipt = receipt;
	}

}
