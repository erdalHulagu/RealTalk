package com.erdal.realTalk.user.controller;

import com.erdal.realTalk.user.requests.UserRequest;
import com.erdal.realTalk.user.response.ResponseMessage;
import com.erdal.realTalk.user.response.UserResponse;
import com.erdal.realTalk.user.service.UserService;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		
		this.userService = userService;
	}
	
	public Response createUser(UserRequest userRequest) {
		
		userService.createUser(userRequest);
		
		UserResponse UserResponse=new UserResponse(ResponseMessage.USER_CREATED,true);
		
		return Response.status(Response.Status.CREATED).entity(UserResponse).build();
		
	}

}
