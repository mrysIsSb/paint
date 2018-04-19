package com.mrysissb.paint.entity;

import com.mrysissb.paint.enums.State;

public class Result {

	private State state;
	private String content;
	private String message;
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
