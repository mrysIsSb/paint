package com.mrysissb.paint.enums;

public enum State {

	SUCCESS("成功",200),
	ERROR("失败",0);
	
	private String content;
	private int id;
	private State(String content,int id) {
		this.content=content;
		this.id=id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
} 
