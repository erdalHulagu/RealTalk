package com.erdal.realTalk.user.service;

import com.erdal.realTalk.common.exception.ErrorMessage;
import com.erdal.realTalk.common.exception.ResourceNotFoundException;
import com.erdal.realTalk.user.mapper.UserMapper;
import com.erdal.realTalk.user.model.User;
import com.erdal.realTalk.user.repository.UserRepository;
import com.erdal.realTalk.user.requests.UserRequest;

public class UserService {
	
	private UserRepository userRepository;
	private UserMapper userMapper;
	
	

	public UserService(UserRepository userRepository,UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper=userMapper;
	}



	public void  createUser(UserRequest userRequest) {
		
		
		      User user= userMapper.UserRequestToUser(userRequest);
		      
		      if (user==null) { throw new ResourceNotFoundException(ErrorMessage.BAD_REQUEST);}
		    	  
		    	userRepository.save(user);
				
			
		
		
	}	
	
}
