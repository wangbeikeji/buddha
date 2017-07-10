package com.wangbei.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

	public static String md5(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes("UTF-8"));
		String result = new BigInteger(1, md.digest()).toString(16);
		if (result.length() < 32) {
			for (int i = 0; i < 32 - result.length(); i++) {
				result = "0" + result;
			}
		}
		return result;
	}

	public static String md5(String username, String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(username.getBytes());
		md.update(password.getBytes());

		String result = new BigInteger(1, md.digest()).toString(16);
		if (result.length() < 32) {
			for (int i = 0; i < 32 - result.length(); i++) {
				result = "0" + result;
			}
		}
		return result;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(Md5Util.md5("18588557093", "123456"));
		
		System.out.println(Md5Util.md5("root"));
		System.out.println(Md5Util.md5("luo"));
	}
}
