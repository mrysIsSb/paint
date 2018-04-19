package com.mrysissb.paint.entity;
/** 
* @author 作者: mrysissb
* @version  
* 2018年3月1日 下午7:07:53
*/
public class Register {
	private int id;
	private String nickname;
	private String username;
	private String password;
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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

}
