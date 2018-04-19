package com.mrysissb.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/** 
* @author 作者: mrysissb
* @version  
* 2018年3月1日 下午7:15:00
* 用于处理请求数据
* import JsonUtil
*/
public class RequestDataUtil {

	/**
	 * 将请求数据parameter封装成对象
	 * @param clazz
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static Object encapsulationParameter(Class clazz,HttpServletRequest request) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException {
		Object object=clazz.newInstance();
//		Method[] methods= clazz.getMethods();
		Set<String> requestField=new HashSet<>();
		Enumeration e=request.getParameterNames();
		while(e.hasMoreElements()) {
			requestField.add(e.nextElement().toString());
		}
		Field[] arrayfield=clazz.getDeclaredFields();
		String fieldName=null;
		Field field=null;
		Class fieldType=null;
		for(int i=0,fl=arrayfield.length;i<fl;i++) {
			fieldName=arrayfield[i].getName();
			System.out.println(fieldName);
			System.out.println(clazz.getDeclaredField(fieldName).getType().isPrimitive());
			if(requestField.contains(fieldName)) {
				field=clazz.getDeclaredField(fieldName);
				fieldType=field.getType();
				field.setAccessible(true);
				if(fieldType.getName().equals("int")) {
					field.setInt(object, request.getParameter(fieldName)!=null ? Integer.parseInt(request.getParameter(fieldName)):0);
				}else if(fieldType.equals(Date.class)) {
					
				}else if(fieldType.equals(String.class)) {
					field.set(object, request.getParameter(fieldName));
				}   
			}
		}
		return object;
	}
	@Deprecated
	public static Object encapsulationJson(Class clazz,String jsonstr) throws InstantiationException, IllegalAccessException {
		Object object=clazz.newInstance();
		return object;
	}
	/**
	 * 获取请求ip 
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr(); 
	    }  
		return ip;
	}
	/** 
     * 获取操作系统,浏览器及浏览器版本信息 
     * @param request 
     * @return 
     */  
    public static String getOsAndBrowserInfo(HttpServletRequest request){  
        String  browserDetails  =   request.getHeader("User-Agent");  
        String  userAgent       =   browserDetails;  
        String  user            =   userAgent.toLowerCase();  
  
        String os = "";  
        String browser = "";  
  
        //=================OS Info=======================  
        if (userAgent.toLowerCase().indexOf("windows") >= 0 )  
        {  
            os = "Windows";  
        } else if(userAgent.toLowerCase().indexOf("mac") >= 0)  
        {  
            os = "Mac";  
        } else if(userAgent.toLowerCase().indexOf("x11") >= 0)  
        {  
            os = "Unix";  
        } else if(userAgent.toLowerCase().indexOf("android") >= 0)  
        {  
            os = "Android";  
        } else if(userAgent.toLowerCase().indexOf("iphone") >= 0)  
        {  
            os = "IPhone";  
        }else{  
            os = "UnKnown, More-Info: "+userAgent;  
        }  
        //===============Browser===========================  
        if (user.contains("edge"))  
        {  
            browser=(userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0]).replace("/", "-");  
        } else if (user.contains("msie"))  
        {  
            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];  
            browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];  
        } else if (user.contains("safari") && user.contains("version"))  
        {  
            browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]  
                    + "-" +(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];  
        } else if ( user.contains("opr") || user.contains("opera"))  
        {  
            if(user.contains("opera")){  
                browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]  
                        +"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];  
            }else if(user.contains("opr")){  
                browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))  
                        .replace("OPR", "Opera");  
            }  
  
        } else if (user.contains("chrome"))  
        {  
            browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");  
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  ||  
                (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) ||  
                (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )  
        {  
            browser = "Netscape-?";  
  
        } else if (user.contains("firefox"))  
        {  
            browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");  
        } else if(user.contains("rv"))  
        {  
            String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");  
            browser="IE" + IEVersion.substring(0,IEVersion.length() - 1);  
        } else  
        {  
            browser = "UnKnown, More-Info: "+userAgent;  
        }  
  
        return os +" --- "+ browser ;  
    }  
	
    /**
     * 
     * 获取os
     * @param request
     * @return
     */
    public static String getOs(HttpServletRequest request) {
    	String  browserDetails  =   request.getHeader("User-Agent");  
        String  userAgent       =   browserDetails; 
    	String os="";
    	if (userAgent.toLowerCase().indexOf("windows") >= 0 )  
        {  
            os = "Windows";  
        } else if(userAgent.toLowerCase().indexOf("mac") >= 0)  
        {  
            os = "Mac";  
        } else if(userAgent.toLowerCase().indexOf("x11") >= 0)  
        {  
            os = "Unix";  
        } else if(userAgent.toLowerCase().indexOf("android") >= 0)  
        {  
            os = "Android";  
        } else if(userAgent.toLowerCase().indexOf("iphone") >= 0)  
        {  
            os = "IPhone";  
        }else{  
            os = "UnKnown, More-Info: "+userAgent;  
        }  
    	return os;
    }
    /**
     * 获取浏览器
     * @param request
     * @return
     */
    public static String getBrowser(HttpServletRequest request) {
    	 String  browserDetails  =   request.getHeader("User-Agent");  
         String  userAgent       =   browserDetails;  
         String  user            =   userAgent.toLowerCase();  
   
         String browser = ""; 
         if (user.contains("edge"))  
         {  
             browser=(userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0]).replace("/", "-");  
         } else if (user.contains("msie"))  
         {  
             String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];  
             browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];  
         } else if (user.contains("safari") && user.contains("version"))  
         {  
             browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]  
                     + "-" +(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];  
         } else if ( user.contains("opr") || user.contains("opera"))  
         {  
             if(user.contains("opera")){  
                 browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]  
                         +"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];  
             }else if(user.contains("opr")){  
                 browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))  
                         .replace("OPR", "Opera");  
             }  
   
         } else if (user.contains("chrome"))  
         {  
             browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");  
         } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  ||  
                 (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) ||  
                 (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )  
         {  
             browser = "Netscape-?";  
   
         } else if (user.contains("firefox"))  
         {  
             browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");  
         } else if(user.contains("rv"))  
         {  
             String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");  
             browser="IE" + IEVersion.substring(0,IEVersion.length() - 1);  
         } else  
         {  
             browser = "UnKnown, More-Info: "+userAgent;  
         }
         return browser;
    }
    public static String getUrl(HttpServletRequest request) {
    	String url=request.getRequestURI();
    	if(url!=null&&!url.isEmpty()) {
    		return request.getRequestURI();
    	}else {
    		return "null";
    	}	
    }
}
