package com.wangbei.util;

public class RandomUtil {

	/**
	 * 生成随机码，一次最多生成10个随机数
	 */
	public static String generateRandomCode(int length) {
		double code = Math.random();
		for (int i = 0; i < 10; i++) {
			if (code > 0.1 && code < 1) {
				break;
			} else {
				code = Math.random();
			}
		}
		int js = 1;
		for (int i = 0; i < length; i++) {
			js *= 10;
		}
		return String.valueOf(Math.round((code * js)));
	}

	public static void main(String[] args) {
		// 生成15位的随机数
		String first = RandomUtil.generateRandomCode(10);
		String second = RandomUtil.generateRandomCode(5);
		String result = first + second;

		System.out.println("result:" + result);
	}
}
