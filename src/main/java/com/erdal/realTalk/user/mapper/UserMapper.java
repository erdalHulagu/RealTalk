package com.erdal.realTalk.user.mapper;

import com.erdal.realTalk.user.model.User;
import com.erdal.realTalk.user.requests.UserRequest;

public class UserMapper {
	
	public User UserRequestToUser(UserRequest userRequest) {
		
		User user = new User();
		user.setFullName(userRequest.getFullName());
		user.setId(userRequest.getId());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		
		return user;
		
	}
	
	

}
