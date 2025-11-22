package com.erdal.realTalk.user.mapper;

import javax.inject.Singleton;

import com.erdal.realTalk.user.model.User;
import com.erdal.realTalk.user.requests.UserRequest;

@Singleton
public class UserMapper {
	


    public User userRequestToUser(UserRequest req) {
        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        return user;
    }
}
	
	

