package com.mrysissb.paint.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mrysissb.paint.entity.Result;
import com.mrysissb.paint.enums.State;
import com.mrysissb.paint.tool.UserSessionTool;
import com.mrysissb.util.JsonUtil;
import com.mrysissb.util.RequestDataUtil;

/** 
* @author 作者: mrysissb
* @version  
* 2018年3月3日 下午11:20:31
*/
public class UserFilter implements Filter{

	private static String[] arraypath= {"/usercontroller/","/paintcontroller/"};
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("用户过滤器"+RequestDataUtil.getUrl((HttpServletRequest)request));
		HttpServletRequest hrequest=(HttpServletRequest) request;
        HttpServletResponse hresponse=(HttpServletResponse) response;
        String path=hrequest.getRequestURI();
        boolean t=false;
        for(int i=0,j=arraypath.length;i<j;i++) {
        	if(path.indexOf(arraypath[i])!=-1) {
        		t=true;
        	}
        }
        if(t) {
        	if(UserSessionTool.getInstance().validateOnCookie(hrequest)) {
        		chain.doFilter(request, response);
        	}else {
        		Result result=new Result();
        		result.setState(State.ERROR);
        		result.setMessage("请登录");
        		hresponse.getWriter().write(JsonUtil.objectToJsonStr(result));
//        		hresponse.setStatus(403);
//        		hresponse.sendError(401, "请登录");
//        		hrequest.getRequestDispatcher("/maincontroller/home").forward(request, response);
        	}
        }else {
        	chain.doFilter(request, response);
        }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
