package com.mrysissb.spring;

import java.io.File;
import java.util.Locale;

import org.springframework.web.servlet.view.InternalResourceView;

/** 
* @author 作者: mrysissb
* @version  
* 2018年4月24日 上午10:59:44
*/
public class HtmlResourceView extends InternalResourceView {

	@Override
	public boolean checkResource(Locale locale) throws Exception {
		File file = new File(this.getServletContext().getRealPath("/") + getUrl());  
	    return file.exists();// 判断该页面是否存在  
	}
}
