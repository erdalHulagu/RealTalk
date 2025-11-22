package com.erdal.realTalk.user.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.mindrot.jbcrypt.BCrypt;

import com.erdal.realTalk.common.exception.ErrorMessage;
import com.erdal.realTalk.common.exception.ResourceNotFoundException;
import com.erdal.realTalk.user.kafka.UserProducer;
import com.erdal.realTalk.user.mapper.UserMapper;
import com.erdal.realTalk.user.model.User;
import com.erdal.realTalk.user.repository.UserRepository;
import com.erdal.realTalk.user.requests.UserRequest;
import com.erdal.realTalk.user.role.Role;
import com.erdal.realTalk.user.status.Status;
import com.fasterxml.jackson.databind.ObjectMapper; // JSON dönüşümü için

/**
 * UserService
 * ------------------
 * Kullanıcı işlemlerini yönetir:
 * - Yeni kullanıcı oluşturma
 * - Database kaydı (Hibernate)
 * - Kafka event yayını (user_created)
 */

@RequestScoped
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserProducer producer;

    @Inject
    private ObjectMapper objectMapper;

    public void createUser(User user) {
        if (user == null) throw new ResourceNotFoundException(ErrorMessage.BAD_REQUEST);

        // Password hash
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);

        userRepository.save(user);

        try {
            String json = objectMapper.writeValueAsString(user);
            producer.sendMessage(json);
        } catch (Exception e) {
            System.out.println("Kafka mesajı gönderilemedi");
        }
    }
}



