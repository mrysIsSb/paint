package com.mrysissb.paint.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mrysissb.paint.entity.UserDo;
import com.mrysissb.paint.memory.OsBrowserMemory;
import com.mrysissb.paint.service.UserService;
import com.mrysissb.paint.tool.UserSessionTool;
import com.mrysissb.util.RequestDataUtil;

/** 
* @author 作者: mrysissb
* @version  
* 2018年3月11日 下午3:40:39
* 拦截器用于记录用户的操作
*/
public class UserDoInerceptor implements HandlerInterceptor {

	@Resource(name="userserviceimpl")
	private UserService userservice;
	
	/**
	 * 请求前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws java.lang.Exception {
		System.out.println("userdo拦截器"+RequestDataUtil.getUrl(request));
		UserDo userdo=new UserDo();
		userdo.setUserid(UserSessionTool.getInstance().getUserid(request));
		userdo.setUrl(RequestDataUtil.getUrl(request));
		userdo.setOs(RequestDataUtil.getOs(request));
		userdo.setBrowser(RequestDataUtil.getBrowser(request));
		userdo.setIp(RequestDataUtil.getIp(request));
		userservice.addUserDo(userdo);
		return true;
	}
	/**
	 * 请求后
	 */
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView) throws java.lang.Exception {
	}
	/**
	 * 处理后
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws java.lang.Exception {
	}
	

}
