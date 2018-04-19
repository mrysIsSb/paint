package com.mrysissb.paint.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrysissb.paint.dao.UserDoDao;
import com.mrysissb.paint.dao.UserInfoDao;
import com.mrysissb.paint.dao.UserLogInfoDao;
import com.mrysissb.paint.dao.UserLoginDao;
import com.mrysissb.paint.entity.Register;
import com.mrysissb.paint.entity.Result;
import com.mrysissb.paint.entity.UserDo;
import com.mrysissb.paint.entity.UserInfo;
import com.mrysissb.paint.entity.UserLogInfo;
import com.mrysissb.paint.entity.UserLogin;
import com.mrysissb.paint.enums.State;
import com.mrysissb.paint.factory.HttpClientOperate;
import com.mrysissb.paint.memory.OsBrowserMemory;
import com.mrysissb.paint.service.UserService;
import com.mrysissb.paint.tool.UserSessionTool;
import com.mrysissb.util.JsonUtil;
import com.mrysissb.util.MD5Util;
import com.mrysissb.util.RequestDataUtil;

/** 
* @author 作者: mrysissb
* @version 创建时间：2018年2月21日 下午7:18:25 
* 
*/
@Service("userserviceimpl")
public class UserServiceImpl implements UserService {

	@Resource(name="userlogindaoimpl")
	private UserLoginDao userlogindao;
	
	@Resource(name="userinfodaoimpl")
	private UserInfoDao userinfodao;
	
	@Resource(name="userdodaoimpl")
	private UserDoDao userdodao;
	
	@Resource(name="httpclientoperate")
    private HttpClientOperate httpclientoperate;
	
	@Resource(name="userloginfodaoimpl")
	private UserLogInfoDao userloginfodao;
	
	@Override
	@Transactional
	public Result login(HttpServletRequest request) {
		UserLogin userlogin=new UserLogin();
		userlogin.setUsername(request.getParameter("username"));
		userlogin.setPassword(MD5Util.encryption(request.getParameter("password")));
		int id=userlogindao.login(userlogin);
		Result result=new Result();
		if(id!=0) {
			Map<String, String> map=new HashedMap();
			String ip=RequestDataUtil.getIp(request);
			map.put("ip", ip);
			map.put("key", "ZBXBZ-5DICX-JZG4F-ZLEAG-Q4LSZ-IFFV5");
			String address=null;
			try {
				System.out.println("开始获取地址");
				address= httpclientoperate.doGet("http://apis.map.qq.com/ws/location/v1/ip",map);
				System.out.println(address);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("开始修改login");
			address=address.replaceAll("\\s*|\n|\r|\t", "");
			userlogindao.updateLoginInfo(id, ip,address);
			System.out.println("修改完成");
			UserLogInfo userloginfo=new UserLogInfo();
			userloginfo.setUserid(id);
			userloginfo.setIp(ip);
			userloginfo.setAddress(address);
			userloginfodao.insertUserLogInfo(userloginfo);
			result.setState(State.SUCCESS);
			UserInfo userinfo=userinfodao.findByUserid(id);
			UserSessionTool.getInstance().validateAndAdd(request, Integer.toString(id));
//			Cookie cookie=new Cookie("userid", Integer.toString(id));
//			cookie.setMaxAge(-1);
//			response.addCookie(cookie);
			if(userinfo!=null) {
				result.setContent(JsonUtil.objectToJsonStr(userinfo));
			}
		}
		return result;
	}

	@Override
	@Transactional
	public Result isHaveUsername(HttpServletRequest request) {
		String username=request.getParameter("username");
		int id=userlogindao.findIdByUsername(username);
		Result result=new Result();
		if(id!=0) {
			result.setState(State.SUCCESS);
			result.setContent("{\"ishave\":true}");
		}else {
			result.setState(State.ERROR);
			result.setContent("{\"ishave\":false}");
		}
		return result;
	}

	@Override
	@Transactional
	public Result register(HttpServletRequest request) {
		Result result=new Result();
		Register register=null;
		try {
			register=(Register) RequestDataUtil.encapsulationParameter(Register.class, request);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		register.setPassword(MD5Util.encryption(register.getPassword()));
		if(userlogindao.findIdByUsername(register.getUsername())==0) {
			int id=userlogindao.addUser(register);
			register.setId(id);
			if(userinfodao.addUseridNickname(register)!=0) {
				result.setState(State.SUCCESS);
				result.setContent(JsonUtil.objectToJsonStr(register));
				result.setMessage("注册登录成功");
			}else {
				result.setState(State.ERROR);
				result.setMessage("注册失败");
			}
		}else {
			result.setState(State.ERROR);
			result.setMessage("账号已存在");
		}
		
		return result;
	}

	@Override
	public void addUserDo(UserDo userdo) {
		addUserDo(userdo, OsBrowserMemory.getInstance().getId(userdo.getOs(), userdo.getBrowser()));
	}

	@Override
	public void addUserDo(UserDo userdo, int osbrowserid) {
		userdodao.insert(userdo, osbrowserid);
	}

}
