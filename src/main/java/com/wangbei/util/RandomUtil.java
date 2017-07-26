package com.wangbei.util;

import java.util.UUID;

public class RandomUtil {

	/**
	 * 获取[0, max)之前的整数，右开区间（即不包括最大数）
	 * 
	 * @param max
	 *            最大数
	 * @return 随机整数
	 */
	public static int getRandomInt(int max) {
		return (int) (Math.random() * max);
	}

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

	/**
	 * 获取随机字符串 Nonce Str
	 *
	 * @return String 随机字符串
	 */
	public static String generateNonceStr() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
	}

	public static void main(String[] args) {
		// 生成15位的随机数
		String first = RandomUtil.generateRandomCode(10);
		String second = RandomUtil.generateRandomCode(5);
		String result = first + second;

		System.out.println("result:" + result);

		System.out.println(getRandomInt(10));
	}
}
