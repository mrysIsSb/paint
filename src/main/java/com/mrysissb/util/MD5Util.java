package com.mrysissb.util;
/** 
* @author 作者: mrysissb
* @version  
* 2018年3月6日 下午8:31:10
*/

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
/**
 * md5加密
 * @param args
 * @return
 */
	public static String encryption(String args) {
		MessageDigest md5 = null;
		byte[] b;
		StringBuffer buf;
		String result = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(args.getBytes());
			b = md5.digest();
			int x;
			buf = new StringBuffer("");
			for (int i = 0; i < b.length; i++) {
				x = b[i];
				if (x < 0)
					x += 256;
				if (x < 16)
					buf.append("0");
				buf.append(Integer.toHexString(x));
			}
			result = buf.toString();
//			System.out.println("32位加密后的字符串: " + buf.toString());// 32位的加密
//			System.out.println("16位加密后的字符串: " + buf.toString().substring(8, 24));// 16位的加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

}
