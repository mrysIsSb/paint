package com.mrysissb.paint.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @author 作者: mrysissb
* @version 创建时间：2018年2月21日 下午7:33:44 
*/
public class UserLogin {

	private int id;
	private String username;
	private String password;
	private String lasttime;
	private String ip;
	private String address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(Date lasttime) {
		if(lasttime!=null) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setLenient(false);
			this.lasttime = sdf.format(lasttime);
		}
	}
	public void setLasttime(String lasttime) {
		if(lasttime!=null) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setLenient(false);
			try {
				sdf.parse(lasttime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.lasttime = sdf.format(lasttime);
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
