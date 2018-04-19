package com.mrysissb.paint.tool;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;

/** 
* @author 作者: mrysissb
* @version  
* 2018年3月3日 下午11:35:33
*/
public class UserSessionTool {

	private static UserSessionTool instance=new UserSessionTool();
	private Map<String, String> sessionMap;
	
	private UserSessionTool(){
		sessionMap=new HashMap<>();
	}
	public static UserSessionTool getInstance() {
		return instance;
	}
	/**
	 * 是否存在userid
	 * @param userid
	 * @return
	 */
	public boolean isHaveUserId(String userid) {
		return sessionMap.containsKey(userid);
	}
	/**
	 * 验证存在userid替换session没有则添加
	 * @param request
	 * @param userid
	 * @return
	 */
	public String validateAndAdd(HttpServletRequest request,String userid) {
		if(isHaveUserId(userid)) {
			sessionMap.replace(userid, request.getSession().getId());
		}else {
			sessionMap.put(userid, request.getSession().getId());
		}
		return null;
	}
	@Deprecated
	public boolean validate(HttpServletRequest request) {
		return validate(request,request.getParameter("userid"));
	}
	@Deprecated
	public boolean validate(HttpServletRequest request,String userid) {
		return request.getSession().getId().equals(sessionMap.get(userid));
	}
	/**
	 * 验证userid和session是不是相同
	 * @param request
	 * @return
	 */
	public boolean validateOnCookie(HttpServletRequest request) {
		Cookie[] cookies=request.getCookies();
		if(cookies==null) {
			return false;
		}
		for (Cookie cookie : cookies) {
			if("userid".equals(cookie.getName())) {
				return request.getSession().getId().equals(sessionMap.get(cookie.getValue()));
			}
		}
		return false;
	}
	/**
	 * 获取map大小
	 * @return
	 */
	public int getSize() {
		return sessionMap.size();
	}
	/**
	 * 从cookie中获取userid
	 * @return
	 */
	public int getUserid(HttpServletRequest request) {
		int userid=0;
		Cookie[] cookies=request.getCookies();
		if(cookies==null) {
			return userid;
		}
		for (Cookie cookie : cookies) {
			if("userid".equals(cookie.getName())) {
				userid=Integer.parseInt("null".equals(cookie.getValue())?"0":cookie.getValue());
			}
		}
		return userid;
	}
}
