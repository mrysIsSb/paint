package com.mrysissb.paint.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @author 作者: mrysissb
* @version  
* 2018年3月4日 上午12:18:22
*/
public class UserLogInfo {

	private int id;
	private int userid;
	private String logintime;
	private String logouttime;
	private String ip;
	private String address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(Date logintime) {
		if(logintime!=null) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setLenient(false);
			this.logintime=sdf.format(logintime);
		}
		
		
	}
	public void setLogintime(String logintime) {
		if(logintime!=null) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setLenient(false);//用于设置Calendar是否宽松解析字符串，如果为false，则严格解析；默认为true，宽松解析
			try {
				sdf.parse(logintime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.logintime = sdf.format(logintime);
		}
		
	}
	public String getLogouttime() {
		return logouttime;
	}
	public void setLogouttime(Date logouttime) {
		if(logouttime!=null) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setLenient(false);
			this.logouttime=sdf.format(logouttime);
		}
	}
	public void setLogouttime(String logouttime) {
		if(logouttime!=null) {
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setLenient(false);
			try {
				sdf.parse(logouttime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.logouttime=sdf.format(logouttime);
		}
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
