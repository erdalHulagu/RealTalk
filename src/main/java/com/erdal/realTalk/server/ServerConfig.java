package com.erdal.realTalk.server;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.erdal.realTalk.common.exception.GlobalExceptionHandler;
import com.erdal.realTalk.user.controller.UserController;
import com.erdal.realTalk.user.service.UserService;
import com.erdal.realTalk.user.repository.UserRepository;
import com.erdal.realTalk.user.kafka.UserProducer;
import com.erdal.realTalk.user.mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ServerConfig extends ResourceConfig {

    public ServerConfig() {
        // Controller ve exception handler register et
        register(UserController.class);
        register(GlobalExceptionHandler.class);

        // Mapper'ı ve diğer servisleri HK2 binder ile inject et
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(UserMapper.class).to(UserMapper.class); // sadece DI için
                bind(UserRepository.class).to(UserRepository.class);
                bind(UserService.class).to(UserService.class);
                bind(new ObjectMapper()).to(ObjectMapper.class);

                String kafkaBootstrap = System.getenv().getOrDefault("KAFKA_BOOTSTRAP_SERVERS", "localhost:9092");
                String kafkaTopic = System.getenv().getOrDefault("KAFKA_USER_TOPIC", "user-events");
                bind(new UserProducer(kafkaBootstrap, kafkaTopic)).to(UserProducer.class);
            }
        });

        // Controller paketlerini taramak yeterli
        packages(
                "com.erdal.realTalk.user.controller",
                "com.erdal.realTalk.common.exception"
        );
    }
}
