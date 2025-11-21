package com.erdal.realTalk.user.controller;



import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.erdal.realTalk.common.response.UserResponse;
import com.erdal.realTalk.user.model.User;
import com.erdal.realTalk.user.service.UserService;

/**
 * UserController
 * ------------------
 * REST API endpoint'lerini sağlar
 * - Kullanıcı oluşturma (POST /users)
 * - JSON input/output ile çalışır
 * - UserService ile DB ve Kafka event işlemlerini yönetir
 */




@Path("/users")
public class UserController {

    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(User user) {

        userService.createUser(user);

        return Response.status(Response.Status.CREATED)
                .entity(new UserResponse("User created", true))
                .build();
    }
}

