package com.erdal.realTalk.common.response;

public class UserResponse  {
	
	private String message;
	
	private boolean succes;

	public UserResponse(String message, boolean succes) {
		
		this.message = message;
		this.succes = succes;
	}
	
	
}
