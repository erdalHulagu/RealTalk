package com.erdal.realTalk.server;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.erdal.realTalk.common.exception.GlobalExceptionHandler;
import com.erdal.realTalk.user.controller.UserController;
import com.erdal.realTalk.user.service.UserService;
import com.erdal.realTalk.user.repository.UserRepository;
import com.erdal.realTalk.user.kafka.UserProducer;
import com.erdal.realTalk.user.mapper.UserMapper;

public class ServerConfig extends ResourceConfig {

    public ServerConfig() {

        register(UserController.class);
        register(GlobalExceptionHandler.class);

        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(UserService.class).to(UserService.class);
                bind(UserRepository.class).to(UserRepository.class);
                bind(new ObjectMapper()).to(ObjectMapper.class);
//                bind(UserMapper.class).to(UserMapper.class);
                // Kafka producer optional, yoksa null olabilir
                // bind(UserProducer.class).to(UserProducer.class);
            }
        });

        packages(
                "com.erdal.realTalk.user.controller",
                "com.erdal.realTalk.common.exception"
        );
    }
}
