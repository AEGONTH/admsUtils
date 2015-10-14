package com.adms.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;


public class EncryptionUtil {

	private static EncryptionUtil instance;
	private static final String MD5 = "MD5";
	
	public static EncryptionUtil getInstance() {
		if(instance == null) {
			instance = new EncryptionUtil();
		}
		return instance;
	}
	
	public String md5(String val) throws NoSuchAlgorithmException {
		if(StringUtils.isEmpty(val)) throw new IllegalArgumentException("String for encrypt is null");
		MessageDigest md = MessageDigest.getInstance(MD5);
		md.update(val.getBytes());
		
		byte[] bytes = md.digest();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < bytes.length; i++) sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		return sb.toString();
	}
	
	public String md5(String val, String salt) throws NoSuchAlgorithmException {
		return md5(val.concat(salt));
	}
	
}
