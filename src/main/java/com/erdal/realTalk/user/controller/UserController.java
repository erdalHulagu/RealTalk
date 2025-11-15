package com.erdal.realTalk.user.controller;



import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.erdal.realTalk.common.response.ResponseMessage;
import com.erdal.realTalk.common.response.UserResponse;
import com.erdal.realTalk.user.requests.UserRequest;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * createUser
     * ------------------
     * 1. JSON formatında UserRequest alır
     * 2. UserService.createUser() ile kullanıcıyı DB'ye kaydeder ve Kafka event gönderir
     * 3. Başarılı ise HTTP 201 CREATED + UserResponse döner
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserRequest userRequest) {

        userService.createUser(userRequest);

        UserResponse userResponse = new UserResponse(ResponseMessage.USER_CREATED, true);

        return Response.status(Response.Status.CREATED).entity(userResponse).build();
    }
}
