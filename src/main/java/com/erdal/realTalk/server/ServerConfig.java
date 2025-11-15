package com.erdal.realTalk.server;

import org.glassfish.jersey.server.ResourceConfig;
import com.erdal.realTalk.user.controller.UserController;
import com.erdal.realTalk.user.service.UserService;
import com.erdal.realTalk.user.repository.UserRepository;
import com.erdal.realTalk.user.mapper.UserMapper;
import com.erdal.realTalk.user.kafka.UserProducer;
import com.erdal.realTalk.common.exception.GlobalExceptionHandler;

public class ServerConfig extends ResourceConfig {
    public ServerConfig() {
        // Manuel register: Controller + Exception Handler
        register(new UserController(
                new UserService(
                        new UserRepository(),
                        new UserMapper(),
                        new UserProducer("localhost:9092","user-events")
                )
        ));

        register(GlobalExceptionHandler.class);
    }
}
