package com.tmt.hurricane.user.controller;

public class UserBean {
	
	private String message;
	
	public UserBean(String message) {
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "UserBean [message=" + message + "]";
	}

}
