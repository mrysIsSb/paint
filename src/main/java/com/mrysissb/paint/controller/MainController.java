package com.mrysissb.paint.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrysissb.paint.dao.impl.PaintContentDaoImpl;
import com.mrysissb.paint.memory.OsBrowserMemory;
import com.mrysissb.paint.service.PaintService;
import com.mrysissb.paint.service.UserService;
import com.mrysissb.util.JsonUtil;

/** 
* @author 作者: mrysissb
* @version  
* 2018年3月4日 下午1:23:15
* 不需要验证登录的请求
*/
@Controller
@RequestMapping("/maincontroller")
public class MainController {

	@Resource(name="userserviceimpl")
	private UserService userservice;
	
	@Resource(name="paintserviceimpl")
	private PaintService paintservice;
	
	/**
	 * 主页
	 * @return
	 */
	@RequestMapping("/home")
	private String home() {
		return "home";
	}
	@RequestMapping("/gotopaint")
	private String gotopaint() {
		return "paint/paint";
	}
	@RequestMapping("/gotoregister")
	private String gotoregister() {
		return "user/register";
	}
	@ResponseBody
	@RequestMapping(value="/getlastpaint", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	private String getLastPaint(HttpServletRequest request) {
		return JsonUtil.objectToJsonStr(paintservice.getLastPaint());
	}
	@ResponseBody
	@RequestMapping(value="/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private String login(HttpServletRequest request) {
		return JsonUtil.objectToJsonStr(userservice.login(request));
	}
	@ResponseBody
	@RequestMapping(value="/ishaveusername",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	private String ishaveusername(HttpServletRequest request) {
		return JsonUtil.objectToJsonStr(userservice.isHaveUsername(request));
	}
	@ResponseBody
	@RequestMapping(value="/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private String register(HttpServletRequest request) {
		return JsonUtil.objectToJsonStr(userservice.register(request));
	}
}
