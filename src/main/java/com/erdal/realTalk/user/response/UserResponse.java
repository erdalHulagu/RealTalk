package com.erdal.realTalk.user.response;

public class UserResponse  {
	
	private String message;
	
	private boolean succes;

	public UserResponse(String message, boolean succes) {
		super();
		this.message = message;
		this.succes = succes;
	}
	
	
}
