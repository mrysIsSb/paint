package com.mrysissb.paint.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @author 作者: mrysissb
* @version  
* 2018年3月5日 下午6:54:20
*/
public class PaintContent {

	private int id;
	private String label;
	private String content;
	private String describe;
	private int userid;
	private String state;
	private String createtime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
       
			sdf.setLenient(false);
			sdf.parse(createtime);
        } catch (ParseException e) {
        	createtime=null;
        }
		this.createtime = createtime;
	}
	public void setCreatetime(Date createtime) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createtime = sdf.format(createtime);
	}
	
}
