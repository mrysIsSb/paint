package com.mrysissb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrysissb.util.RequestDataUtil;

/** 
* @author 作者: mrysissb
* @version  
* 2018年3月14日 下午6:39:42
*/
public class UrlFilter implements Filter {

	private static String[] arraypath= {"/usercontroller/","/paintcontroller/","/maincontroller/","/js/","/css/","/images/","/bootstrap-3.3.7-dist/","/favicon.ico"};
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("url过滤器"+RequestDataUtil.getUrl((HttpServletRequest)request));
		HttpServletRequest hrequest=(HttpServletRequest) request;
        HttpServletResponse hresponse=(HttpServletResponse) response;
        String path=hrequest.getRequestURI();
        boolean t=false;
        for(int i=0,j=arraypath.length;i<j;i++) {
        	if(path.indexOf(arraypath[i])==0) {
        		t=true;
        	}
        }
        if(t){
        	chain.doFilter(request, response);
        }else {
        	hrequest.getRequestDispatcher("/").forward(request, response);
        }

	}

//	请求转发：
//
//	response.getRequestDispatcher("/student_list.jsp").forward(request,response);
//
//
//	重定向：
//
//	response.sendRedirect(request.getContextPath + "/student_list.jsp")
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
