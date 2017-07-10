package com.wangbei.pojo;

/**
 * 发送验证码结果 对象
 * 
 * @author luomengan
 *
 */
public class SendAuthCodeResult {

	/**
	 * 发送结果代码
	 */
	private int code;
	/**
	 * 发送结果提示
	 */
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
