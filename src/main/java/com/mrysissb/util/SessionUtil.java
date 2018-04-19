package com.mrysissb.util;

import javax.servlet.http.HttpServletRequest;

/** 
* @author 作者: mrysissb
* @version  
* 2018年3月3日 下午11:09:55
*/
public class SessionUtil {

	public static void getSession(HttpServletRequest request) {
		request.getSession().getId();
	}
}
